package com.doraemon.pattern.chain;

public class ConcreteHandler3 implements Handler {

    @Override
    public boolean shouldHandle() {
        return true;
    }

    @Override
    public String handle(String msg) {
        return msg + "-3";
    }
}
