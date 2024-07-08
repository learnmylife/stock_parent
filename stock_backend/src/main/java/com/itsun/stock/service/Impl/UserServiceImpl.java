package com.itsun.stock.service.Impl;

import com.itsun.stock.mapper.SysUserMapper;
import com.itsun.stock.pojo.entity.SysUser;
import com.itsun.stock.service.UserService;
import com.itsun.stock.vo.req.LoginReqVo;
import com.itsun.stock.vo.resp.LoginRespVo;
import com.itsun.stock.vo.resp.R;
import com.itsun.stock.vo.resp.ResponseCode;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service("userService")
public class UserServiceImpl implements UserService {
    @Autowired
    private SysUserMapper sysUserMapper;

    @Autowired
    private PasswordEncoder passwordEncoder;
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
            return R.ok(loginRespVo);
        }else{
            return R.error(ResponseCode.USERNAME_OR_PASSWORD_ERROR);
        }


    }
}
