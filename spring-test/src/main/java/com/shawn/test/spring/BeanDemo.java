package com.shawn.test.spring;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.core.env.SystemEnvironmentPropertySource;

import java.util.List;
import java.util.Map;

/**
 * Created by Shawn on 2017/7/24.
 */
public class BeanDemo {
    private int var1;

    private String var2;

    private List<String> var3;

    private Map<String, String> var4;

    private static BeanDemo _instance;

    public static BeanDemo getInstance(int var1) {
        System.out.println("Call getInstance");
        if(_instance == null) {
            return new BeanDemo(var1);
        }
        return _instance;
    }

    public BeanDemo() {}

    public BeanDemo(int var1) {
        System.out.println("Call BeanDemo");
        this.var1 = var1;
    }

    public void init() {
        System.out.println("Call init");
    }

    public void destroy() {
        System.out.println("Call destroy");
    }

    public void doNoting(String strVal) {
        System.out.println("Do nothing");
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
        test();
    }

    private static void test() {
        ApplicationContext applicationContext = new ClassPathXmlApplicationContext("config/spring/appcontext.xml");
        BeanDemo beanDemo = (BeanDemo) applicationContext.getBean("demo1");
        System.out.println(beanDemo.getVar1());
        System.out.println(beanDemo.getVar3());
        System.out.println(beanDemo.getVar4());

        beanDemo.doNoting("nate");
    }


    public Map<String, String> getVar4() {
        return var4;
    }

    public void setVar4(Map<String, String> var4) {
        this.var4 = var4;
    }
}
