package com.interview.practice;

import java.util.*;
import java.util.function.Consumer;
import java.util.function.Predicate;
import java.util.function.Supplier;
import java.util.stream.Collector;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class LambdaAndStream {
    public static void main(String[] args) {
        int num = 1;
        TestFuncInt<Integer, String> method1 = (from) -> String.valueOf(from + num);

        String transform = method1.transform(3);

        // Accessing default methods of functional interface
        TestFuncInt testFuncInt = new TestFuncIntImpl();
        testFuncInt.makeDouble(4);
        testFuncInt.makeSquare(4);

        // Using the method of implementation class rather than interface class
        TestFuncIntImpl testFuncIntImpl = new TestFuncIntImpl();
        testFuncIntImpl.makeSquare(4, 2);

        Supplier<TestFlatMap> testClSupplier = TestFlatMap::new;
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
            System.out.println(map.get(key).firstName + " " + map.get(key).lastName);
        }

        String[] arr = new String[]{"a", "b", "c"};
        List<String> arrList = Arrays.asList(arr);

        Optional<String> reduce = arrList
                .stream()
                .sorted()
                .reduce((s1, s2) -> s1 + " " + s2);

        reduce.ifPresent(System.out::println);

        TestFlatMap testFlatMap = new TestFlatMap();
        Optional<List<String>> optional = Optional.of(testFlatMap.flatMapExample());
        System.out.println("\nFlatMap example");
        optional.ifPresent((s) -> System.out.println(testFlatMap.flatMapExample()));

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

        Stream.of("a", "b", "c", "d")
                .sorted()
                .findFirst()
                .ifPresent(s -> s.replace(s, "e"));


        System.out.format("\nIntStream\n");
        IntStream.of(1, 2, 3, 4, 5).average().ifPresent(System.out::println);
        IntStream.range(1, 4).forEachOrdered(System.out::print);

        System.out.println();

        Stream.of(1, 3, 5, 9)
                .mapToDouble(Integer::doubleValue)
                .mapToObj(n -> "d" + n)
                .forEach(System.out::println);

        Supplier<Stream<String>> streamSupplier = () -> Stream.of("a1", "b1", "c1", "d1")
                .filter(s -> s.startsWith("a"));
        streamSupplier.get().forEach(System.out::print);
        streamSupplier.get().sorted().filter(s -> s.startsWith("a")).findFirst().ifPresent(System.out::println);
        streamSupplier.get().anyMatch(s -> true);
        streamSupplier.get().noneMatch(s -> true);

        // Advanced Stream operation

        List<Person> personList = Arrays.asList(
                new Person("Abdul", "Ahad"),
                new Person("Sharmin", "Ferous"),
                new Person("Amatullah", "Ilma")
        );

        System.out.println("Collector example");
        List<Person> ilma = personList
                .stream()
                .filter(p -> p.lastName.equalsIgnoreCase("ilma"))
                .collect(Collectors.toList());

        List<Employee> employees = Arrays.asList(
                new Employee("Bob", 12000),
                new Employee("Alice", 10000),
                new Employee("Don", 15000),
                new Employee("Max", 15000)
        );

        // Group employees by a property
        Map<Float, List<Employee>> employeeBySalary = employees
                .stream()
                .collect(Collectors.groupingBy(s -> s.salary));

        // Print Employees grouped by salary in the previous statement
        employeeBySalary
                .forEach((salary, e) -> System.out.format("Salary -> %s: %s\n", salary, e));

        Double avgSalary = employees.stream().collect(Collectors.averagingDouble(e -> e.salary));

        // Count, Sum, Min, Average, Max
        DoubleSummaryStatistics summaryStatistics = employees
                .stream()
                .collect(Collectors.summarizingDouble(e -> e.salary));

        // Joining properties
        String collect = employees
                .stream()
                .filter(s -> s.salary >= 12000)
                .map(e -> e.name)
                .collect(Collectors.joining(",", "In company X, ", " are manager"));

        // Take 2 properties and make as map
        Map<String, Float> floatMap = employees
                .stream()
                .collect(Collectors.toMap(
                        e -> e.name,
                        e -> e.salary,
                        (i, j) -> Float.valueOf(i + " " + j)));

        // Custom Collector: Collector that we can make. We have to provide supplier, accumulator, combiner, finisher
        Collector<Employee, StringJoiner, String> customCollector = Collector.of(
                () -> new StringJoiner(" | "),      // supplier. Does not take anything but produce something
                (s, e) -> s.add(e.name.toUpperCase()),      // accumulator
                StringJoiner::merge,                        // combiner
                StringJoiner::toString                      // finisher
        );
        String customCollectorResult = employees.stream().collect(customCollector);

        // FlatMap example
        System.out.println("FlatMap");
        List<Foo> foosWithBars = createBars(LambdaAndStream.createFoos());
        foosWithBars
                .stream()
                .flatMap(f -> f.bars.stream()) // Accepts a function which has to return a stream of objects
                .forEach(b -> System.out.println(b.name));

        //Reduce. 3 types
        System.out.println("Reduce test: ");
        employees
                .stream()
                .reduce((e1, e2) -> e1.salary > e2.salary ? e1 : e2)
                .ifPresent(System.out::println); // All stream to a single result

        List<Integer> integers = Arrays.asList(1, 2, 3, 4, 5);
        Integer sum = integers
                .stream()
                .reduce(0, (num1, num2) -> num1 += num2);
        System.out.format("Sum = %s", sum);

        double totalSalary = employees
                .stream()
                .map(s -> s.salary)
                .reduce((float) 0.0, (e1, e2) -> e1 += e2);
        System.out.format("\nTotal salary = %s", totalSalary);

        // We can use Reduce in parallel stream. In that way, we do different operations on the diiferent
        // properties of the same object. For example,
        Double salarySummation = employees
                .parallelStream()
                .reduce((double) 0,
                        (salarySum, p) -> {
                            System.out.format("\nName: %s", p.name);  // Accumulator
                            return salarySum + p.salary;
                        },
                        Double::sum         // Combiner
                );
        System.out.format("\nTotal salary = %s", salarySummation);
    }

    static List<Foo> createFoos() {
        List<Foo> foos = new ArrayList<>();
        IntStream
                .range(1, 4)
                .forEach(f -> foos.add(new Foo("Foo" + f)));
        return foos;
    }

    static List<Foo> createBars(List<Foo> foos) {
        foos.forEach(f ->
                IntStream
                        .range(1, 4)
                        .forEach(i -> f.bars.add(new Bar(f.name + " -> " + "Bar" + i))));
        return foos;
    }
}


class Foo {
    String name;
    List<Bar> bars = new ArrayList<>();

    public Foo(String name) {
        this.name = name;
    }
}

class Bar {
    String name;

    public Bar(String name) {
        this.name = name;
    }
}

// Only 1 abstract method. Can be more than one default or static method.
@FunctionalInterface
interface TestFuncInt<From, To> {
    To transform(From f);

    // Optional
    default int makeDouble(int num) {
        return num * 2;
    }

    //Optional
    default int makeSquare(int num) {
        return num * num;
    }

    //Optional
    static double multiplyWithFloat(int num, double multiplier) {
        return num * multiplier;
    }
}

class TestFuncIntImpl implements TestFuncInt {

    @Override
    public Object transform(Object f) {
        int a = 2;
        // We can implement the default method of functional interface
        return TestFuncInt.super.makeDouble(a);
    }

    @Override
    public int makeDouble(int num) {
        double defaultMultiplier = 2.0;
        double result = TestFuncInt.multiplyWithFloat(num, defaultMultiplier);
        return (int) result;
    }

    @Override
    public int makeSquare(int num) {
        return 0;
    }

    // You can modify method arguments as well as the fields of their class, but default method.
    public int makeSquare(int num, int sqr) {
        // Yor can use implementation class method
        int res = makeSquare(sqr);
        // You can also use interface method
        return TestFuncInt.super.makeSquare(num * res);
    }
}

class TestFlatMap {
    public List<String> flatMapExample() {
        return Collections.unmodifiableList(
                IntStream
                        .range(1, 4)
                        .mapToObj(i -> new Foo("Foo" + i))
                        .peek(f -> IntStream
                                .range(1, 4)
                                .mapToObj(b -> new Bar(f.name + " -> " + "Bar" + b))
                                .forEach(f.bars::add)
                        )
                        .flatMap(f -> f.bars.stream())
                        .map(b -> b.name)
                        .collect(Collectors.toList()));
    }
}

class Employee {
    String name;
    float salary;

    public Employee(String name, float salary) {
        this.name = name;
        this.salary = salary;
    }

    @Override
    public String toString() {
        return name;
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
