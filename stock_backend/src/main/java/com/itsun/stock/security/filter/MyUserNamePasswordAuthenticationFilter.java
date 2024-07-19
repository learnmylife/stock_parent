package com.itsun.stock.security.filter;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;
import com.itsun.stock.constant.StockConstant;
import com.itsun.stock.security.user.LoginUserDetail;
import com.itsun.stock.security.utils.JwtTokenUtil;
import com.itsun.stock.vo.req.LoginReqVo;
import com.itsun.stock.vo.resp.LoginRespVo;
import com.itsun.stock.vo.resp.R;
import com.itsun.stock.vo.resp.ResponseCode;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.http.MediaType;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.web.authentication.AbstractAuthenticationProcessingFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletInputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author by itheima
 * @Date 2022/1/24
 * @Description 自定义登录拦截过滤器
 */
public class MyUserNamePasswordAuthenticationFilter extends AbstractAuthenticationProcessingFilter {
    private RedisTemplate redisTemplate;

    public void setRedisTemplate(RedisTemplate redisTemplate) {
        this.redisTemplate = redisTemplate;
    }

    /**
     * 通过构造器传入指定的登录url地址
     * @param defaultFilterProcessesUrl 自定义url登录地址
     */
    public MyUserNamePasswordAuthenticationFilter(String defaultFilterProcessesUrl) {
        super(defaultFilterProcessesUrl);
    }

    @Override
    public Authentication attemptAuthentication(HttpServletRequest request,
                                                HttpServletResponse response) throws AuthenticationException, IOException, ServletException {

       //从request域下获取ajax提交数据
        String userName=null;
        String password=null;
        //判断提供的内容格式
        if (request.getContentType().equalsIgnoreCase(MediaType.APPLICATION_JSON_VALUE) ||
                request.getContentType().equalsIgnoreCase(MediaType.APPLICATION_JSON_UTF8_VALUE)) {
            ServletInputStream in = request.getInputStream();
            //将流对象转化成LoginReqVo
            LoginReqVo info = new ObjectMapper().readValue(in, LoginReqVo.class);
            //验证码校验
            String code = (String) redisTemplate.opsForValue().get(StockConstant.CHECK_PREFIX + info.getSessionId());
            if (StringUtils.isBlank(code)||!code.equalsIgnoreCase(info.getCode())){
                R<Object> error = R.error(ResponseCode.CHECK_CODE_TIMEOUT);
                String string = new ObjectMapper().writeValueAsString(error);
                //响应错误
                response.setCharacterEncoding("UTF-8");
                response.getWriter().write(string);
                return null;
            }
            //获取用户名和密码 我们约定，ajax登录时，用户名:userName,密码：password
            userName= info.getUsername();
            password= info.getPassword();
            if (userName==null || password==null) {
                throw new RuntimeException("用户名或者密码错误");
            }
        }
        UsernamePasswordAuthenticationToken authRequest = new UsernamePasswordAuthenticationToken(userName, password);
        //开始认证
        return this.getAuthenticationManager().authenticate(authRequest);
    }

    /**
     * 认证成功处理方法
     * @param request
     * @param response
     * @param chain
     * @param authResult
     * @throws IOException
     * @throws ServletException
     */
    @Override
    protected void successfulAuthentication(HttpServletRequest request,
                                            HttpServletResponse response,
                                            FilterChain chain,
                                            Authentication authResult) throws IOException, ServletException {
        //获取用户的明细信息，包含用户名 密码（null） 权限集合
        LoginUserDetail principal = (LoginUserDetail) authResult.getPrincipal();
        String username = principal.getUsername();
        List<GrantedAuthority> authorities = principal.getAuthorities();

        //生成token
        String token = JwtTokenUtil.createToken(username, authorities.toString());

        //组装响应对象
        LoginRespVo loginRespVo = new LoginRespVo();
        BeanUtils.copyProperties(principal,loginRespVo);
        loginRespVo.setAccessToken(token);
        String jsonStr = new Gson().toJson(R.ok(loginRespVo));

        //没有使用chain.doFilter(request,response);放行, 不会调用UserController的方法

        //响应json格式字符串
        response.setContentType(MediaType.APPLICATION_JSON_VALUE);
        response.setCharacterEncoding("UTF-8");
//        response.getWriter().write(new ObjectMapper().writeValueAsString(R.ok(loginRespVo));
        response.getWriter().write(jsonStr);
    }

    /**
     * 认证失败后的处理方法
     * @param request
     * @param response
     * @param failed
     * @throws IOException
     * @throws ServletException
     */
    @Override
    protected void unsuccessfulAuthentication(HttpServletRequest request,
                                              HttpServletResponse response,
                                              AuthenticationException failed) throws IOException, ServletException {
        //组装响应数据
        R<Object> info = R.error(ResponseCode.ERROR);
        String errorJson = new Gson().toJson(info);
        //响应数据
        response.setContentType(MediaType.APPLICATION_JSON_VALUE);
        response.setCharacterEncoding("UTF-8");
        response.getWriter().write(errorJson);
    }
}
