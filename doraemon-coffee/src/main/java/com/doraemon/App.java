package com.doraemon;

import org.apache.commons.lang3.StringUtils;

import java.util.Locale;

/**
 * Hello world!
 */
public class App {
    public static void main(String[] args) {
        Locale ss = new Locale("ss");
        System.out.println(ss.getLanguage());
        System.out.println(ss.toLanguageTag());
        System.out.println("Hello World!");
    }
}
