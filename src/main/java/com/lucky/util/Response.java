/**
 * @Description TODO
 * @Author zhenxing.dong@luckincoffee.com
 * @Date 2019/8/2 11:17
 */
package com.lucky.util;

public class Response {

    public int status;
    public String message;
    public Object content;

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
