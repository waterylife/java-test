package com.shawn.test.spring.aop;

import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;

/**
 * Created by Shawn on 2017/7/27.
 */
@Aspect
public class AspectWatcher {
    @Pointcut("execution(* com.shawn.test.spring.BeanDemo.doNoting(..)) && args(strVal)")
    public void nothing(String strVal) {

    }

    @Before("nothing(strVal)")
    public void before(String strVal) {
        System.out.println("Call before: " + strVal);
    }

    public void after(String strVal) {
        System.out.println("Call after");
    }
}
