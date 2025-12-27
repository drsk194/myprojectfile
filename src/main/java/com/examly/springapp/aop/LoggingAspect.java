package com.examly.springapp.aop;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class LoggingAspect {
    @Before("execution(* com.examly.springapp.controller..*(..))")
    public void logBefore(JoinPoint jp) {
       
    }
}
