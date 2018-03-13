package com.shawn.test.spring;

import org.springframework.stereotype.Component;

/**
 * Created by Shawn on 2017/7/26.
 */
@Component
public class BeanDemo3 {
    private String var1 = "hello";

    public BeanDemo3() {
        System.out.println("Call BeanDemo3");
    }

    public String getVar1() {
        return var1;
    }

    public void setVar1(String var1) {
        this.var1 = var1;
    }
}
