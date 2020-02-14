package com.doraemon.pattern.chain;

public class ConcreteHandler1 implements Handler {


    @Override
    public boolean shouldHandle() {
        return true;
    }

    @Override
    public String handle(String msg) {
        if (Thread.currentThread().getName().equals("A")){
            ChainSupport.brokeChain();
        }
        return msg+"-1";
    }
}
