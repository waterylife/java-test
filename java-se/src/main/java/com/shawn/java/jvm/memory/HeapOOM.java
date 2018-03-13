package com.shawn.java.jvm.memory;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Shawn on 2016/11/15.
 */
public class HeapOOM {
    public static void main(String[] argv) {
        List<String> oomList = new ArrayList<String>();
        while (true) {
            oomList.add(new String("Hello..........................."));
        }
    }

}
