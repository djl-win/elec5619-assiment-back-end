package com.group50.exception;

/**
 * 自定义异常处理，handler中进行处理
 */
public class CustomException extends RuntimeException{

    private Integer code;

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public CustomException(Integer code, String message) {
        super(message);
        this.code = code;
    }

    public CustomException(Integer code, String message, Throwable cause){
        super(message, cause);
        this.code = code;
    }
}
