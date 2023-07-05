package com.example.springbootaop;

import com.example.springbootaop.annotation.Custom;
import com.example.springbootaop.vo.CustomVO;
import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

@Aspect
@Slf4j
@Component
public class CustomAdvisor {
    //@Around("execution(* com.example.springbootaop.controller.*.*(..))")
    @Around("execution(* com.example.springbootaop.controller.*.*(..))")
    public Object processCustomAnnotation(ProceedingJoinPoint proceedingJoinPoint) throws Throwable {
        MethodSignature methodSignature = (MethodSignature) proceedingJoinPoint.getSignature();
        Custom custom = methodSignature.getMethod().getAnnotation(Custom.class);
        log.info("execute custom annotation processing with annotation param = {}", custom.firstValue());
        log.info("Before invoke getSomeValue()");
        Object proceedReturnValue = proceedingJoinPoint.proceed();
        log.info("After invoke getSomeValue()");

        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.currentRequestAttributes()).getRequest();

        //CustomVO vo = (CustomVO) request.getAttribute("customVO");

        CustomVO vo = (CustomVO) proceedingJoinPoint.getArgs()[0];

        log.info("After invoke getSomeValue() = {}", vo.getFirstValue());

        return proceedReturnValue;
    }

}
