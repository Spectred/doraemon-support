package com.doraemon.pattern.chain;

import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

public class Chain {

    private List<Handler> chains = new CopyOnWriteArrayList<>();

    public Chain next(Handler handler) {
        this.chains.add(handler);
        return this;
    }

    public String handle(String msg) {
        for (Handler chain : chains) {
            if (chain.shouldHandle() && ChainSupport.shouldContinue()) {
                msg = chain.handle(msg);
            }
        }
        return msg;
    }
}
