package com.jmelon.onlinecourse.aspect;


import com.jmelon.onlinecourse.controller.BaseController;
import com.jmelon.onlinecourse.controller.TodosController;
import com.jmelon.onlinecourse.model.AuthUser;
import org.apache.commons.text.CaseUtils;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.util.regex.Pattern;

@Aspect
@Component
class AuthorizeAspect {
    @Autowired
    private ApplicationContext context;
    /*@Before("@annotation(Authorize)")
    public void before(ProceedingJoinPoint point) throws Throwable{
        System.out.println("---security check-----");
    }*/

    @Around("@annotation(Authorize)")
    public Object check(final ProceedingJoinPoint point) throws Throwable {

            HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder
                    .currentRequestAttributes())
                    .getRequest();
            Object value;
        try {

            if ((boolean) request.getAttribute("isAuthenticated") == true) {
                if (point.getTarget() instanceof BaseController) {
                    var user = (AuthUser) request.getAttribute("user");
                    ((BaseController) point.getTarget()).setUser(user);
                }
            } else {
                throw new Exception();
            }
        } catch (Throwable throwable) {
            return new ResponseEntity(HttpStatus.UNAUTHORIZED);
        }
        value = point.proceed();
        return value;
    }
}