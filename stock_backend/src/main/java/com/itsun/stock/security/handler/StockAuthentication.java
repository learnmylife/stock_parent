package com.itsun.stock.security.handler;

import com.google.gson.Gson;
import com.itsun.stock.vo.resp.R;
import com.itsun.stock.vo.resp.ResponseCode;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.security.web.access.AccessDeniedHandler;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author by itheima
 * @Date 2022/1/24
 * @Description 自定义权限拒绝后的处理器
 */
@Slf4j
public class StockAuthentication implements AuthenticationEntryPoint {

    @Override
    public void commence(HttpServletRequest request, HttpServletResponse response, AuthenticationException e) throws IOException, ServletException {
        log.warn("匿名无权限: {}",e.getMessage());
        R<Object> error = R.error(ResponseCode.ANONMOUSE_NOT_PERMISSION);
        String jsonStr = new Gson().toJson(error);
        //设置响应格式
        response.setContentType(MediaType.APPLICATION_JSON_VALUE);
        response.setCharacterEncoding("UTF-8");
        response.getWriter().write(jsonStr);
    }
}
