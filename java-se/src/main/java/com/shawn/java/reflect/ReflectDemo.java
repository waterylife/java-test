package com.shawn.java.reflect;


import java.lang.reflect.*;

/**
 * 反射用于内部类示例
 */

public class ReflectDemo {
    static class Caculator {
        private int leftOp;

        private int rightOp;

        public Caculator() {}

        public int add(int leftOp, int rightOp) {
            System.out.println(leftOp + "+" + rightOp + "=" + (leftOp + rightOp) );
            return leftOp + rightOp;
        }

        public int subtract(int leftOp, int rightOp) {
            System.out.println(leftOp + "-" + rightOp + "=" + (leftOp - rightOp) );
            return leftOp - rightOp;
        }
    }

}

class Test {
    public static void main(String[] args) {
        Class clazz = ReflectDemo.class;

        try {
            Object obj = clazz.getConstructor().newInstance();
            Class[] innerClazzArray = clazz.getDeclaredClasses();
            for(Class innerClazz : innerClazzArray) {
                Object innerObj = null;
                if(!Modifier.isStatic(innerClazz.getModifiers())) {
                    innerObj = innerClazz.getConstructor(clazz).newInstance(obj);
                } else {
                    innerObj = innerClazz.getConstructor().newInstance();
                }
                Method[] methods = innerClazz.getMethods();
                for(Method method : methods) {
                    Class[] paraTypes = method.getParameterTypes();
                    if(method.getName().equals("add")) method.invoke(innerObj, 1, 2);
                    else if(method.getName().equals("subtract")) method.invoke(innerObj, 1, 2);
                }
                Field[] fields = innerClazz.getDeclaredFields();
                for(Field field : fields) {
                    field.setAccessible(true);
                    field.get(innerObj);
                }
            }
        } catch (Exception e) {
            System.out.println(e);
        }
    }
}
