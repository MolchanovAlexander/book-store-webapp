package com.example.bookstorewebapp.service;

import lombok.extern.log4j.Log4j2;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;
import org.springframework.util.StopWatch;

@Log4j2
@Aspect
@Component
public class ProfilingAspect {
    @Pointcut("within(com.example.bookstorewebapp.service.category.impl.CategoryServiceImpl)")
    public void allMethodsFromCategoryService() {
    }

    @Around("allMethodsFromCategoryService()")
    public Object profileCategoryServiceAdvice(ProceedingJoinPoint proceedingJoinPoint) throws Throwable {
        StopWatch stopWatch = new StopWatch();
        stopWatch.start();
        Object result = proceedingJoinPoint.proceed();
        stopWatch.stop();
        log.info(String.format("Method \"%s\";%s%s ",
            proceedingJoinPoint.getSignature().getName(),
            System.lineSeparator(),
            stopWatch.prettyPrint()
        ));
        return result;
    }
}

