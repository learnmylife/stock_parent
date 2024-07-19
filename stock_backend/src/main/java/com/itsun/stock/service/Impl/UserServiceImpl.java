package com.itsun.stock.service.Impl;

import cn.hutool.captcha.CaptchaUtil;
import cn.hutool.captcha.LineCaptcha;
import com.google.common.base.Strings;
import com.itsun.stock.constant.StockConstant;
import com.itsun.stock.mapper.SysUserMapper;
import com.itsun.stock.pojo.entity.SysPermission;
import com.itsun.stock.pojo.entity.SysUser;
import com.itsun.stock.service.PremissionService;
import com.itsun.stock.service.UserService;
import com.itsun.stock.utils.IdWorker;
import com.itsun.stock.vo.req.LoginReqVo;
import com.itsun.stock.vo.resp.LoginRespVo;
import com.itsun.stock.vo.resp.PermissionRespNodeVo;
import com.itsun.stock.vo.resp.R;
import com.itsun.stock.vo.resp.ResponseCode;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.concurrent.TimeUnit;

import java.util.Map;
import java.util.stream.Collectors;

@Service("userService")
@Slf4j
public class UserServiceImpl implements UserService {
    @Autowired
    private SysUserMapper sysUserMapper;

    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    private IdWorker idWorker;
    @Autowired
    private RedisTemplate redisTemplate;
    @Autowired
    private PremissionService premissionService;
    @Override
    public SysUser findByUserName(String username) {
        return sysUserMapper.findUserByUsername(username);

    }

    @Override
    public R<LoginRespVo> login(LoginReqVo loginReqVo) {
        //是否合法
        if (loginReqVo==null || StringUtils.isBlank(loginReqVo.getUsername()) || StringUtils.isBlank(loginReqVo.getPassword())){
            return R.error(ResponseCode.DATA_ERROR.getMessage());
        }
        //验证码是否合法
        if (StringUtils.isBlank(loginReqVo.getCode()) || StringUtils.isBlank(loginReqVo.getSessionId())){
            return R.error(ResponseCode.DATA_ERROR);
        }
        String code = (String) redisTemplate.opsForValue().get(StockConstant.CHECK_PREFIX + loginReqVo.getSessionId());
        if (StringUtils.isBlank(code)){
            return R.error(ResponseCode.CHECK_CODE_TIMEOUT);
        }
        if (!code.equalsIgnoreCase(loginReqVo.getCode())){
            return R.error(ResponseCode.CHECK_CODE_ERROR);
        }

        SysUser user = sysUserMapper.findUserByUsername(loginReqVo.getUsername());
        if (user==null){
            return R.error(ResponseCode.ACCOUNT_NOT_EXISTS);
        }
        String password = user.getPassword();
        boolean matches = passwordEncoder.matches(loginReqVo.getPassword(), password);
        if (matches) {
            LoginRespVo loginRespVo = new LoginRespVo();
            //直接将user中相同的属性,赋值到新的对象中
            BeanUtils.copyProperties(user,loginRespVo);

            List<SysPermission> permissions = premissionService.getPermissionByUserId(String.valueOf(user.getId()));
            System.out.println(permissions);
            //获取树状权限菜单数据
            List<PermissionRespNodeVo> tree=premissionService.getTree(permissions, "0", true);
            //获取菜单按钮集合
            List<String> authBtnPerms = permissions.stream().filter(per -> !Strings.isNullOrEmpty(per.getCode()) && per.getType() == 3).map(per -> per.getCode()).collect(Collectors.toList());

            loginRespVo.setMenus(tree);
            loginRespVo.setPermissions(authBtnPerms);


            return R.ok(loginRespVo);
        }else{
            return R.error(ResponseCode.USERNAME_OR_PASSWORD_ERROR);
        }


    }

    @ApiOperation("获取验证码")
    @Override
    public R<Map> getCapchaCode() {
        //生成图片验证码
        //宽,长,码数,干扰线数
        LineCaptcha lineCaptcha = CaptchaUtil.createLineCaptcha(250, 40, 4, 5);
        //获取验证码
        String code = lineCaptcha.getCode();
        //获取图片
        String imageBase64 = lineCaptcha.getImageBase64();
        //sessionId
        String sessionId = String.valueOf(idWorker.nextId());
        log.info("id:{},capcha:{}",sessionId,code);
        //传入reids,设置过期时间
        //id前面加上CK,方便检测
        redisTemplate.opsForValue().set(StockConstant.CHECK_PREFIX +sessionId,code,1, TimeUnit.MINUTES);

        Map<String,String> data = new HashMap<>();
        data.put("imageData",imageBase64);
        data.put("sessionId",sessionId);
        return R.ok(data);
    }
}
