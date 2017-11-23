package com.interview;

import java.util.*;

/**
 * Created by ahadcse on 2018-01-27.
 */
public class CollectionFramework {

    public static void main(String args[]) {
        Vector<String> testVector = new Vector<>();
        vectorOperation(testVector);
    }

    public static void vectorOperation(Vector<String> testVector) {
        testVector.add("ahad");
        testVector.add("rimi");
        testVector.remove("ahad");

        Iterator<String> itr = testVector.iterator();
        while (itr.hasNext()) {
            System.out.println(itr.next());
            System.out.println(testVector.isEmpty());
        }

        List<String> list = new ArrayList<>();
        list.add("Bob");
        testVector.addAll(list);

        Vector<String> copy = (Vector<String>) testVector.clone();
        Enumeration<String> enumeration = copy.elements();
        while (enumeration.hasMoreElements()) {
            System.out.println(enumeration.nextElement());
        }
    }
}
