package com.group50.handler;


import com.group50.common.Result;
import com.group50.common.ResultInfo;
import com.group50.exception.CustomException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 * 拦截自定义异常（在exception包下）
 */
@RestControllerAdvice
public class SpringHandlerException {

    /**
     * catch the unknown exception
     */
    @ExceptionHandler(Exception.class)
    public Result<String> doException(Exception ex){

        ex.printStackTrace();

        return Result.fail(ResultInfo.UNKNOWN_CODE,ResultInfo.UNKNOWN_MSG);

    }

    /**
     * catch the custom exception
     */
    @ExceptionHandler(CustomException.class)
    public Result<String> customException(CustomException ex){

        //return info to web developer
        return Result.fail("custom exception", ex.getCode(), ex.getMessage());
    }


}
