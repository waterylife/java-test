package com.shawn.java.reflect;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 * Created by Shawn on 2017/7/20.
 */
public class DynamicProxyDemo {
    public static void main(String[] args) {
        final Caculator caculator = new CaculatorImp();
        Caculator caculatorProxy = (Caculator) Proxy.newProxyInstance(DynamicProxyDemo.class.getClassLoader(),
                new Class[] {Caculator.class},
                new InvocationHandler() {
                    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
                        System.out.println("befor invoke" + method.getName());
                        return method.invoke(caculator, args);
                    }
                });

        caculatorProxy.add(1, 2);

        Caculator.class.getInterfaces();
    }
}


interface Caculator {
    public int add(int leftOp, int rightOp);
    public int subtract(int leftOp, int rightOp);
}


class CaculatorImp implements Caculator {
    private int leftOp;

    private int rightOp;

    public CaculatorImp() {}

    public int add(int leftOp, int rightOp) {
        System.out.println(leftOp + "+" + rightOp + "=" + (leftOp + rightOp) );
        return leftOp + rightOp;
    }

    public int subtract(int leftOp, int rightOp) {
        System.out.println(leftOp + "-" + rightOp + "=" + (leftOp - rightOp) );
        return leftOp - rightOp;
    }
}