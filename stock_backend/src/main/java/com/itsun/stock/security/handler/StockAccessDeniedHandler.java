package com.itsun.stock.security.handler;

import com.google.gson.Gson;
import com.itsun.stock.vo.resp.R;
import com.itsun.stock.vo.resp.ResponseCode;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.web.access.AccessDeniedHandler;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;

/**
 * @author by itheima
 * @Date 2022/1/24
 * @Description 自定义权限拒绝后的处理器
 */
@Slf4j
public class StockAccessDeniedHandler implements AccessDeniedHandler {
    @Override
    public void handle(HttpServletRequest request,
                       HttpServletResponse response,
                       AccessDeniedException e) throws IOException, ServletException {
        //组装响应的数据
        log.warn("无权限");
        R<Object> error = R.error(ResponseCode.NOT_PERMISSION);
        String jsonStr = new Gson().toJson(error);
        //设置响应格式
        response.setContentType(MediaType.APPLICATION_JSON_VALUE);
        response.setCharacterEncoding("UTF-8");
        response.getWriter().write(jsonStr);

    }
}
