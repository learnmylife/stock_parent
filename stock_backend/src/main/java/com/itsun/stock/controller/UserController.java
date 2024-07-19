package com.itsun.stock.controller;

import com.itsun.stock.pojo.entity.SysUser;
import com.itsun.stock.service.UserService;
import com.itsun.stock.vo.req.LoginReqVo;
import com.itsun.stock.vo.resp.LoginRespVo;
import com.itsun.stock.vo.resp.R;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Base64;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api")
public class UserController {
    @Autowired
    private UserService userService;

    @ApiOperation(value = "根据用户名查询信息")
    @ApiImplicitParams({
            @ApiImplicitParam(name="name",value = "用户名",dataType = "String",required = true,type = "path")
    })
    @GetMapping("/user/{userName}")
    public SysUser getUserByName(@PathVariable("userName") String username){
        return userService.findByUserName(username);
    }
    @ApiOperation(value = "用户登录")
    @PostMapping("/login")
    public R<LoginRespVo> userLogin(@RequestBody LoginReqVo loginReqVo){

        return userService.login(loginReqVo);
    }
    @GetMapping("/captcha")
    public R<Map> getCapchaCode(){
        return userService.getCapchaCode();
    }

}
