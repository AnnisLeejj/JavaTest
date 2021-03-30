package com.annis.struct;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.function.Consumer;

public class ListStudy {
    public static void main(String[] args) {
        test1();
    }

    private static void test1() {
//        BaseBo baseBo = new BaseBo();
//        baseBo.setData("http://www.baidu.com");
//        baseBo.setType(MessageType.TYPE1);
//        String json = new Gson().toJson(baseBo);
//        System.out.println(json);
//
//        BaseBo bo = new Gson().fromJson(json, BaseBo.class);
//
//        System.out.println(bo.getType() == MessageType.TYPE1);//true
    }

    private void test() {
        List<String> strings = new ArrayList<>();
        strings.add("Ljj");
        strings.add("Ljj2");
        strings.add("Ljj3");
        strings.add("Ljj4");
//        Iterator<String> iterator = strings.iterator();
//        while (iterator.hasNext()) {
//            iterator.next();
//            iterator.remove();
//        }
//        System.out.println(strings);

        List list = strings;
        list.add(100f);

        List<String> strings1 = Collections.checkedList(strings, String.class);
        strings1.forEach(System.out::println);
//        strings1.add(100f);
    }
}
