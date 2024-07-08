package com.itsun.stock.controller;

import com.itsun.stock.pojo.entity.SysUser;
import com.itsun.stock.service.UserService;
import com.itsun.stock.vo.req.LoginReqVo;
import com.itsun.stock.vo.resp.LoginRespVo;
import com.itsun.stock.vo.resp.R;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
public class UserController {
    @Autowired
    private UserService userService;

    @GetMapping("/user/{userName}")
    public SysUser getUserByName(@PathVariable("userName") String username){
        return userService.findByUserName(username);
    }
    @PostMapping("/login")
    public R<LoginRespVo> userLogin(@RequestBody LoginReqVo loginReqVo){

        return userService.login(loginReqVo);
    }
}
