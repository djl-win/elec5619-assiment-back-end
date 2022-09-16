package com.group50.handler;

import com.group50.common.AdminThread;
import com.group50.common.ResultInfo;
import com.group50.exception.CustomException;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * 拦截用户的登录操作
 */
@Component
public class SpringHandlerLogin implements HandlerInterceptor {
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        System.out.println("intercept login");

        HttpSession session = request.getSession();

        Object adminId = session.getAttribute("adminId");

        if(adminId == null){
            //拦截成功返回报错信息，或者重定向
            throw new CustomException(ResultInfo.NON_LOGIN_CODE,ResultInfo.NON_LOGIN_MSG);
        }
        //加载管理员id到线程
        AdminThread.setCurrentId((Long)adminId);
        return true;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
        HandlerInterceptor.super.postHandle(request, response, handler, modelAndView);
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
        HandlerInterceptor.super.afterCompletion(request, response, handler, ex);
    }
}
