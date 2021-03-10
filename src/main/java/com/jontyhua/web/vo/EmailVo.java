package com.jontyhua.web.vo;

/**
 * @ClassName EmailVo
 * @Author: JontyHua
 * @Date: 2019/4/13
 * @Description: 用与邮箱验证传值
 */
public class EmailVo {
    private String email;

    private String code;

    public EmailVo(String email, String code) {
        this.email = email;
        this.code = code;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }
}
