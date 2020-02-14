package com.doraemon.incubate;

public class Test {
    private static final String VAR = "A";

    public static void main(String[] args) {
        if ("A".equals(VAR)) {
            System.out.println("A");
        } else if ("B".equals(VAR)) {
            System.out.println("B");
        } else if ("C".equals(VAR)) {
            System.out.println("C");
        } else {
            System.out.println("D");
        }
    }
}
