package com.itsun.stock.controller;

import com.itsun.stock.service.UserService;
import com.itsun.stock.vo.req.LoginReqVo;
import com.itsun.stock.vo.resp.LoginRespVo;
import com.itsun.stock.vo.resp.PageRespVo;
import com.itsun.stock.vo.resp.R;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.Authorization;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class UserControllerExt {

    @Autowired
    private UserService userService;

    @ApiOperation(value = "分页获取所有用户")
    @PostMapping("/users")
    //权限控制
    @PreAuthorize("hasAnyAuthority('sys:user:list')")
    public R<PageRespVo> userLogin(@RequestBody LoginReqVo loginReqVo){

        return null;
    }

}
