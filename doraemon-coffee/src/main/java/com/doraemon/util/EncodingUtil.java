package com.doraemon.util;

import java.io.UnsupportedEncodingException;

public class EncodingUtil {

    public static void main(String[] args) throws UnsupportedEncodingException {
        String str = "ddd哈哈";

        String s = new String(str.getBytes("GBK"), "GBK");
        System.out.println(s);
    }
}
