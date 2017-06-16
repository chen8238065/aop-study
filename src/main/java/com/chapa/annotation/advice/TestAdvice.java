package com.chapa.annotation.advice;

import com.chapa.annotation.service.*;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import java.util.Date;

/**
 * Created by chapa on 17-6-6.
 */
@Aspect
@Component // @Repository,@Service,@Controller  表识 需要spring 加载到容器中 ,本质上目前没发现 他们四个有任何区别,不过语义上最好用在对应的层级上
@Scope(value=ConfigurableBeanFactory.SCOPE_SINGLETON,proxyMode= ScopedProxyMode.DEFAULT) //指定 spring容器生成改对像的方式(单列.原型) spring 生成代理的方式(基于interface的jdk dynamic proxy,基于cglib)
@Order(3) // 指定3spring aop 的植入顺序 ,必须基于代理的模式 才有效
//@DeclarePrecedence("TestAdvice,SecondAdvice")  //指定aop植入顺序 (ajc注解  需要指定使用ajc编译代码)
public class TestAdvice {

    @DeclareParents(value = "com.chapa.annotation.service.TestServiceImpl",defaultImpl =com.chapa.annotation.service.IntroduceImpl.class)
    IIntroduce testService;

    @After("execution(* com.chapa.annotation.service.*.*(..))")
    public void doAfter(JoinPoint jp) {
        System.out.println("TestAdvice annotation log Ending method: " + jp.getTarget().getClass().getName() + "." + jp.getSignature().getName());
    }

    @Around("execution(* com.chapa.annotation.service.*.*(..))")
    public Object doAround(ProceedingJoinPoint pjp) throws Throwable {
        long time = System.currentTimeMillis();
        Object retVal = pjp.proceed();
        time = System.currentTimeMillis() - time;
        System.out.println("TestAdvice annotation process time: " + time + " ms");
        return retVal;
    }

    @Before("execution(* com.chapa.annotation.service.*.*(..))")
    public void doBefore(JoinPoint jp) {
        System.out.println("TestAdvice annotation log Begining method: " + jp.getTarget().getClass().getName() + "." + jp.getSignature().getName());
    }

    @AfterThrowing(throwing="ex",pointcut="execution(* com.chapa.annotation.service.*.*(..))")
    public void doThrowing(JoinPoint jp, Throwable ex) {
        System.out.println("TestAdvice annotation method " + jp.getTarget().getClass().getName() + "." + jp.getSignature().getName() + " throw exception");
        System.out.println(ex.getMessage());
    }

    @AfterReturning(returning="retVal", pointcut="execution(* com.chapa.annotation.service.*.*(..)) && args(var,word)")
    public void args(String var, String word, Object retVal){
        System.out.println("目标方法中Strig参数为:"+var);
        System.out.println("目标方法中Strig参数为:"+word);
        System.out.println("模拟记录日志...");
    }


    @AfterReturning(returning="retVal", pointcut="execution(* com.chapa.annotation.service.*.*(..)) && args(String,String)")
    public void args( Object retVal){
        System.out.println("weave by  pointcut args  with  two String paramaters...");
    }

    @AfterReturning(returning="retVal", pointcut="execution(* com.chapa.annotation.service.*.*(..)) && !this(com.chapa.annotation.service.ITestService)")// and or 这种用法在使用aspectj方式时不支持
    public void thisI( Object retVal){
        System.out.println("weave by  pointcut  this..");
    }
}
