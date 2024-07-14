package com.itsun.stock.vo.req;


import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
@ApiModel
public class LoginReqVo {
    /**
     * 用户名
     */
    @ApiModelProperty(value = "用户名",required = true)
    private String username;
    /**
     * 密码
     */
    @ApiModelProperty(value = "密码",required = true)
    private String password;
    /**
     * 验证码
     */
    @ApiModelProperty("验证码")
    private String code;
    @ApiModelProperty("sessionId")
    private String sessionId;

    public String getSessionId() {
        return sessionId;
    }

    @Override
    public String toString() {
        return "LoginReqVo{" +
                "username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", code='" + code + '\'' +
                ", sessionId='" + sessionId + '\'' +
                '}';
    }

    public void setSessionId(String sessionId) {
        this.sessionId = sessionId;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }
}
