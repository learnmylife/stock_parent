package com.itsun.stock.service;

import com.itsun.stock.pojo.entity.SysUser;
import com.itsun.stock.vo.req.LoginReqVo;
import com.itsun.stock.vo.resp.LoginRespVo;
import com.itsun.stock.vo.resp.R;

import java.util.Map;

public interface UserService {
    SysUser findByUserName(String username);

    R<LoginRespVo> login(LoginReqVo loginReqVo);

    R<Map> getCapchaCode();
}
