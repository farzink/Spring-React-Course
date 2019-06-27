package com.jmelon.onlinecourse.filter;


import com.jmelon.onlinecourse.service.AuthUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.support.SecurityContextProvider;
import org.springframework.stereotype.Component;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.regex.Pattern;


@Component
public class JWTFilter extends HandlerInterceptorAdapter {

    @Autowired
    private AuthUserService authUserService;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        try {
            var authorization = request.getHeader("Authorization");
            if (!authorization.isEmpty() && !authorization.isBlank()) {
                var token = authorization.split(Pattern.quote(" "))[1];
                var user = authUserService.getUserByToken(token);
                if(user != null) {
                    request.setAttribute("isAuthenticated", true);            //crap in
                    request.setAttribute("user", user);            //crap in
                } else {
                    request.setAttribute("isAuthenticated", false);            //crap in
                    request.setAttribute("user", null);            //crap in
                }
                //System.out.println(request.getAttribute("isAuthenticated"));
            }

            return super.preHandle(request, response, handler);
        } catch (Exception ex) {
            request.setAttribute("isAuthenticated", false);
            request.setAttribute("user", null);
            System.out.println(request.getAttribute("isAuthenticated"));
            return super.preHandle(request, response, handler);
        }
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
        //crap out
        //System.out.println("post");
        super.postHandle(request, response, handler, modelAndView);
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
        //crap done
        //System.out.println("done");
        super.afterCompletion(request, response, handler, ex);
    }
}

abstract class CustomHttpServletRequest implements HttpServletRequest {
    public int userId;
}
