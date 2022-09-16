package com.group50.handler;

import com.group50.common.Result;
import org.springframework.core.MethodParameter;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.method.support.HandlerMethodReturnValueHandler;
import org.springframework.web.method.support.ModelAndViewContainer;

/**
 * 处理未封装的返回结果，不使用Result也可直接返回
 */
public class SpringHandlerReturnValue implements HandlerMethodReturnValueHandler {

    private final HandlerMethodReturnValueHandler returnValueHandler;

    public SpringHandlerReturnValue(HandlerMethodReturnValueHandler returnValueHandler) {
        this.returnValueHandler = returnValueHandler;
    }

    @Override
    public boolean supportsReturnType(MethodParameter returnType) {
        return this.returnValueHandler.supportsReturnType(returnType);
    }

    @Override
    public void handleReturnValue(Object returnValue, MethodParameter returnType, ModelAndViewContainer mavContainer, NativeWebRequest webRequest) throws Exception {
        //judge if the return type is result or not
        if(returnValue instanceof Result){
            this.returnValueHandler.handleReturnValue(returnValue,returnType,mavContainer,webRequest);
            return;
        }
        this.returnValueHandler.handleReturnValue(Result.success(returnValue),returnType,mavContainer,webRequest);
    }
}
