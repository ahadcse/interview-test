package com.interview.practice;

import org.apache.tomcat.util.digester.ArrayStack;

import java.lang.reflect.Array;
import java.util.*;
import java.util.function.Consumer;
import java.util.function.Predicate;// TODO: Look collection framework
import java.util.function.Supplier;

public class Rough {
    public static void main(String[] args) {
        int num = 1;
        TestFuncInt<Integer, String> method1 = (from) -> String.valueOf(from + num);

        String transform = method1.transform(3);

        Supplier<TestCl> testClSupplier = TestCl::new;
        testClSupplier.get();
        Consumer<Person> consumer = (s) -> System.out.println(s.firstName);
        consumer.accept(new Person("Sharmin", "Ferdous"));

        Comparator<Person> comparator = (c1, c2) -> c1.firstName.compareTo(c2.firstName);

        Predicate<String> predicate = (s) -> s.length() > 0;

        Person c1 = new Person("Sharmin", "Ferdous");
        Person c2 = new Person("Abdul", "Ahad");
        int compare = comparator.compare(c1, c2);

        Map<String, Person> map = new HashMap<>();
        map.put("test", new Person("Sharmin", "Ferdous"));

        Set<String> strings = map.keySet();

        map.entrySet().forEach(System.out::println);

        strings
                .stream()
                .sorted()
                .filter(s -> s.length() > 0)
                .forEach(System.out::println);

        for (String key : map.keySet()) {
            // System.out.println(map.get(key).firstName + " " + map.get(key).lastName);
        }

        String[] arr = new String[]{"a", "b", "c"};
        List<String> arrList = Arrays.asList(arr);

        Optional<String> reduce = arrList
                .stream()
                .sorted()
                .reduce((s1, s2) -> s1 + " " + s2);

        reduce.ifPresent(System.out::println);

        TestCl testCl = new TestCl();
        Optional<String> optional = Optional.of(testCl.func1());
        optional.ifPresent((s) -> System.out.println(testCl.func1()));

        // Java Stream practice

        List<String> streamList = Arrays.asList("1", "2", "3", "4", "5");

        System.out.println("Stream area");

        List<Integer> vector = new ArrayList<>();
        streamList
                .stream()
                .sorted()
                .filter(s -> s.length() > 0)
                .map(Integer::new)
                .forEach(vector::add);

        for (Object obj : vector) {
            System.out.print(obj.toString() + " ");
        }
    }
}

class TestCl {
    public String func1() {
        return "test";
    }
}

class Person {
    String firstName;
    String lastName;

    Person(String firstName, String lastName) {
        this.firstName = firstName;
        this.lastName = lastName;
    }
}
