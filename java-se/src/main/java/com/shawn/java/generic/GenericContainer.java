package com.shawn.java.generic;

import java.util.*;

/**
 * Created by Shawn on 2017/7/16.
 */
public class GenericContainer<T extends Number> {
    private T obj;

    private List<?> list;

    public GenericContainer() {
    }

    public GenericContainer(T t) {
        this.obj = t;
    }

    public void setObj(T t) {
        this.obj = t;
    }

    public T getObj() {
        return obj;
    }

    public static <N extends Number> double add(N a, N b) {
        return a.doubleValue() + b.doubleValue();
    }


    public static void main(String[] args) {
        GenericContainer<Integer> genericContainer = new GenericContainer<Integer>();
        genericContainer.setObj(1);
//        genericContainer.setObj("a");

        GenericContainer rawContainer = new GenericContainer();
        rawContainer.setObj(1);
//        rawContainer.setObj("1");
        GenericContainer.add(1, 2);

        List list = new ArrayList();
        list.add("abc");
        list.add(1);
        for (int i = 0; i < list.size(); i++) {
            System.out.println(list.get(i));
            System.out.println(list.get(i) instanceof String);
            System.out.println(list.get(i) instanceof Integer);
        }


        GenericContainer<? extends Integer> genericContainer1 = new GenericContainer<Integer>();
//        genericContainer1.setObj(new Integer(1));
        genericContainer1.getObj();


        GenericContainer<Number> genericContainer2;
        GenericContainer<Integer> genericContainer3 = new GenericContainer<Integer>();
//        System.out.println(genericContainer3 );

        Collection<Integer> list1 = new ArrayList<Integer>();
        Collection<?> list2;
        list2 = list1;
    }
}
