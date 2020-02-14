package com.doraemon.pattern.chain;

public class ConcreteHandler2 implements Handler {
    @Override
    public boolean shouldHandle() {
        return false;
    }

    @Override
    public String handle(String msg) {
        ChainSupport.brokeChain();
        return msg + "-2";
    }
}
