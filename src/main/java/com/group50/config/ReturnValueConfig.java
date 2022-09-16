package com.group50.config;

import com.group50.handler.SpringHandlerReturnValue;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.method.support.HandlerMethodReturnValueHandler;
import org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerAdapter;
import org.springframework.web.servlet.mvc.method.annotation.RequestResponseBodyMethodProcessor;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

/**
 * 处理未封装的返回结果（handler中写出，这里进行注册）
 */
@Configuration
public class ReturnValueConfig implements InitializingBean {
    @Resource
    RequestMappingHandlerAdapter requestMappingHandlerAdapter;

    @Override
    public void afterPropertiesSet() throws Exception {
        List<HandlerMethodReturnValueHandler> originHandlers = requestMappingHandlerAdapter.getReturnValueHandlers();
        List<HandlerMethodReturnValueHandler> newHandlers = new ArrayList<>(originHandlers.size());
        for (HandlerMethodReturnValueHandler originHandler : originHandlers) {
            if (originHandler instanceof RequestResponseBodyMethodProcessor) {
                newHandlers.add(new SpringHandlerReturnValue(originHandler));
            }else{
                newHandlers.add(originHandler);
            }
        }
        requestMappingHandlerAdapter.setReturnValueHandlers(newHandlers);
    }

}
