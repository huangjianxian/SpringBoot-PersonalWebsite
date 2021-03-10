package com.jontyhua.web.vo;

/**
 * @ClassName Response
 * @Author: JONTY HUA
 * @Date: 2019/3/27 22:19
 * @Description: 响应值对象，消息封装
 */
public class Response {

    private boolean success;    //响应处理布尔值
    private String message;     //相应处理的消息
    private Object body;        //响应处理返回的内容

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Object getBody() {
        return body;
    }

    public void setBody(Object body) {
        this.body = body;
    }

    public Response(boolean success, String message) {
        this.success = success;
        this.message = message;
    }

    public Response(boolean success, String message, Object body) {
        this.success = success;
        this.message = message;
        this.body = body;
    }
}
