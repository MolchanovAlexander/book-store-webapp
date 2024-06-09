package com.example.bookstorewebapp.service;

import java.util.Arrays;
import lombok.extern.log4j.Log4j2;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

@Log4j2
@Aspect
@Component
public class CategoryServiceLoggingAspect {
    @Pointcut("execution(* com.example.bookstorewebapp.service.category.CategoryService.findAll(..))")
    private void findAllCategoryServiceMethod() {
    }

    @Before("findAllCategoryServiceMethod()")
    public void beforeCategoryServiceFindAllMethodAdvice() {
        log.info("Method CategoryService.findAll was called");
    }

    @After("findAllCategoryServiceMethod()")
    public void afterCategoryServiceFindAllMethodAdvice() {
        log.info("Method CategoryService.findAll finished its execution.");
    }

    @Before("execution(public * com.example.bookstorewebapp.service..*(..))")
public void beforeServiceMethodAdvice(JoinPoint joinPoint) {
    log.info(String.format(
        "Method %s.%s was called. Arguments: %s",
        joinPoint.getSignature().getDeclaringTypeName(),
        joinPoint.getSignature().getName(),
        Arrays.toString(joinPoint.getArgs())
    ));
}
}

