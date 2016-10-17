package com.melinkr.common.exception;

/**
 * 米联科统统一异常类
 * Created by miller on 2016/9/7.
 */
public class CommonException extends RuntimeException{

    private static final long serialVersionUID = -3680358922519100068L;
    private int code;//异常码
    private String message;//异常描述信息
    public CommonException(){
        super();
    }
    public CommonException(int code){
        super();
        this.code = code;
    }
    public CommonException(int code,String message){
        super(message);
        this.code = code;
        System.err.println("code:"+code+" message:"+message);
    }
    public CommonException(int code,String message, Throwable cause){
        super(message, cause);
        this.code = code;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    @Override
    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
