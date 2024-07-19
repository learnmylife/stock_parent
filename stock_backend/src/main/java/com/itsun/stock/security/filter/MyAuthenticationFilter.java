package com.itsun.stock.security.filter;

import com.google.gson.Gson;
import com.itsun.stock.security.utils.JwtTokenUtil;
import com.itsun.stock.vo.resp.R;
import com.itsun.stock.vo.resp.ResponseCode;
import io.jsonwebtoken.Claims;
import org.springframework.http.MediaType;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;

/**
 * @author by itheima
 * @Date 2022/1/24
 * @Description 自定义权限校验的过滤器
 */
public class MyAuthenticationFilter extends OncePerRequestFilter {
    @Override
    protected void doFilterInternal(HttpServletRequest request,
                                    HttpServletResponse response,
                                    FilterChain filterChain) throws ServletException, IOException {
        //从请求头中获取token信息
        String token = request.getHeader(JwtTokenUtil.TOKEN_HEADER);
        //解析
        if (token==null) {
            //发行，当前的操作可能是登录功能 TODO 从request域获取访问url，然后与开发的url地址进行校验
            //开放的地址：register api/login ,如果当前地址在开放地址内，直接放行，否则，直接返回错误的json字符串
            filterChain.doFilter(request,response);
            return;
        }
        Claims claims = JwtTokenUtil.checkJWT(token);
        if (claims==null){
            R<Object> error = R.error(ResponseCode.INVALID_TOKEN);
            String errorJson = new Gson().toJson(error);
            response.setContentType(MediaType.APPLICATION_JSON_VALUE);
            response.setCharacterEncoding("UTF-8");
            response.getWriter().write(errorJson);
            return;
        }
        //解析token
        String username = JwtTokenUtil.getUsername(token);
        //权限字符串以逗号间隔
        String userRole = JwtTokenUtil.getUserRole(token);
        List<GrantedAuthority> grantedAuthorities = AuthorityUtils.commaSeparatedStringToAuthorityList(userRole);
        //组装token对象
        UsernamePasswordAuthenticationToken tokenObject
                = new UsernamePasswordAuthenticationToken(username, null, grantedAuthorities);
        //security框架为我们提供了一个对应当前线程的上下文对象,其他的security过滤器都可以过去该token对象
        SecurityContextHolder.getContext().setAuthentication(tokenObject);
        //放行
        filterChain.doFilter(request,response);
    }
}
