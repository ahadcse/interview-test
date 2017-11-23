package com.interview.hr;

import java.util.Scanner;

public class TimeConversion {
    static String timeConversion(String s) {

        if(s.endsWith("PM")) {
            int hour = Integer.parseInt(s.substring(0,2));
            String time = "12";
            if(hour != 12) {
                hour = hour + 12;
                time = Integer.toString(hour);
            }
            return time + s.substring(2,8);
        }
        if(s.substring(0, 2).equalsIgnoreCase("12")) {
            return "00" + s.substring(2,8);
        }
        return s.substring(0, 8);
    }

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        String s = in.next();
        String result = timeConversion(s);
        System.out.println(result);
    }
}
