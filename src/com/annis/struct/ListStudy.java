package com.annis.struct;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class ListStudy {
    public static void main(String[] args) {
        List<String> strings = new ArrayList<>();
        strings.add("Ljj");
        strings.add("Ljj2");
        strings.add("Ljj3");
        strings.add("Ljj4");
        Iterator<String> iterator = strings.iterator();
        while (iterator.hasNext()) {
            iterator.next();
            iterator.remove();
        }
        System.out.println(strings);
    }
}
