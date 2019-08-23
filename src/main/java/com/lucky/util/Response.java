package com.lucky.util;
/**
 * @Description 操作状态类，记录某些操作如删除的执行状态
 *
 * @Author zhenxing.dong
 * @Date 2019/8/2 11:17
 */
public class Response {

    /**
     * 操作执行状态
     */
    public int status;
    /**
     * 返回信息
     */
    public String message;
    /**
     * 包含内容
     */
    public Object content;

    /**
     * 返回信息对象
     *
     * @param status 操作执行状态
     * @param message 返回信息
     * @param content 包含内容
     */
    public Response(int status, String message, Object content) {
        this.status = status;
        this.message = message;
        this.content = content;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Object getContent() {
        return content;
    }

    public void setContent(Object content) {
        this.content = content;
    }
}
