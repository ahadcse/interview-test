package com.interview;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

/**
 * Created by ahadcse on 2018-01-27.
 */
public class ObjectSorting {

    public static void main(String args[]) {
        List<Employee> employees = new ArrayList<>();
        employees.add(new Employee("Ahad", 5000.0f));
        employees.add(new Employee("Rimi", 6000.0f));
        employees.add(new Employee("Bob", 4000.0f));

        Collections.sort(employees, new SortByName());
        System.out.println("Sorted by name: ");
        for (Employee employee : employees) {
            System.out.println(employee);
        }

        Collections.sort(employees, new SortBySalary());
        System.out.println("Sorted by salary: ");
        for (Employee employee : employees) {
            System.out.println(employee);
        }
    }
}

class SortByName implements Comparator<Employee> {
    @Override
    public int compare(Employee e1, Employee e2) {
        if(e1.getName().compareTo(e2.getName()) > 0) {
            return  1;
        }
        else {
            return -1;
        }
    }
}

class SortBySalary implements Comparator<Employee> {
    @Override
    public int compare(Employee e1, Employee e2) {
        if(e1.getSalary() > e2.getSalary()) {
            return  1;
        }
        else {
            return -1;
        }
    }
}

class Employee {

    private String name;
    private float salary;

    public Employee(String name, float salary) {
        this.name = name;
        this.salary = salary;
    }

    public String getName() {
        return name;
    }

    public float getSalary() {
        return salary;
    }

    @Override
    public String toString() {
        return "Employee{" +
                "name='" + name + '\'' +
                ", salary=" + salary +
                '}';
    }
}