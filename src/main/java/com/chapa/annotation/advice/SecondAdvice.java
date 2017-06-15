package com.chapa.annotation.advice;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

/**
 * Created by chapa on 17-6-14.
 */
@Aspect
@Component
@Order(2)
public class SecondAdvice {
    @After("execution(* com.chapa.annotation.service.*.*(..))")
    public void doAfter(JoinPoint jp) {
        System.out.println("SecondAdvice log Ending method: " + jp.getTarget().getClass().getName() + "." + jp.getSignature().getName());
    }

    @Around("execution(* com.chapa.annotation.service.*.*(..))")
    public Object doAround(ProceedingJoinPoint pjp) throws Throwable {
        long time = System.currentTimeMillis();
        Object retVal = pjp.proceed();
        time = System.currentTimeMillis() - time;
        System.out.println("SecondAdvice process time: " + time + " ms");
        return retVal;
    }

    @Before("execution(* com.chapa.annotation.service.*.*(..))")
    public void doBefore(JoinPoint jp) {
        System.out.println("SecondAdvice log Begining method: " + jp.getTarget().getClass().getName() + "." + jp.getSignature().getName());
    }

    @AfterThrowing(throwing="ex",pointcut="execution(* com.chapa.annotation.service.*.*(..))")
    public void doThrowing(JoinPoint jp, Throwable ex) {
        System.out.println("SecondAdvice method " + jp.getTarget().getClass().getName() + "." + jp.getSignature().getName() + " throw exception");
        System.out.println(ex.getMessage());
    }
}
