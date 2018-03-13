package com.shawn.test.spring;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.DependsOn;
import org.springframework.context.annotation.Lazy;
import org.springframework.context.annotation.Scope;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;
import java.util.Objects;

/**
 * Created by Shawn on 2017/7/26.
 */
@Component
/*@Scope(BeanDefinition.SCOPE_PROTOTYPE)*/
@DependsOn("beanDemo3")
@Lazy(false)
public class BeanDemo2 {
    private int var1;

    private String var2;

    private List<String> var3;

    private Map<String, String> var4;

    @Autowired
    private BeanDemo3 beanDemo3;

    public BeanDemo2() {
        System.out.println("Call BeanDemo2");
    }

    public BeanDemo2(int var1) {
        System.out.println("Call BeanDemo2 with arg");
        this.var1 = var1;
    }

    public void init() {
        System.out.println("Call init");
    }

    public void destroy() {
        System.out.println("Call destroy");
    }

    public int getVar1() {
        return var1;
    }

    public void setVar1(int var1) {
        this.var1 = var1;
    }

    public String getVar2() {
        return var2;
    }

    public void setVar2(String var2) {
        this.var2 = var2;
    }

    public List<String> getVar3() {
        return var3;
    }

    public void setVar3(List<String> var3) {
        this.var3 = var3;
    }

    public static void main(String[] args) {
        ApplicationContext applicationContext = new ClassPathXmlApplicationContext("config/spring/appcontext2.xml");
        BeanDemo2 beanDemo2 = (BeanDemo2) applicationContext.getBean("beanDemo2");
        System.out.println(beanDemo2.getVar1());
        System.out.println(beanDemo2.getVar3());
        System.out.println(beanDemo2.getVar4());
        BeanDemo3 beanDemo3 = beanDemo2.getBeanDemo3();
        System.out.println(beanDemo3.getVar1());


        //Spring单例测试
        ApplicationContext applicationContext1 = new ClassPathXmlApplicationContext("config/spring/appcontext2.xml");
        System.out.println(Objects.equals(applicationContext.getBean("beanDemo2"), beanDemo2));
    }

    public Map<String, String> getVar4() {
        return var4;
    }

    public void setVar4(Map<String, String> var4) {
        this.var4 = var4;
    }

    public BeanDemo3 getBeanDemo3() {
        return beanDemo3;
    }

    public void setBeanDemo3(BeanDemo3 beanDemo3) {
        this.beanDemo3 = beanDemo3;
    }
}
