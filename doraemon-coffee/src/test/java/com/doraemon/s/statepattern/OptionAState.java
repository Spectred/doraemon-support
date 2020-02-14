package com.doraemon.s.statepattern;

import com.doraemon.s.domain.Option;

public class OptionAState implements OptionState {
    @Override
    public void handle(OptionContext context) {
        System.out.println("A");
        context.setState(this);
    }
}
