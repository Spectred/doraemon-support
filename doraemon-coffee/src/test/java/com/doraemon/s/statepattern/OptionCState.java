package com.doraemon.s.statepattern;

public class OptionCState implements OptionState {
    @Override
    public void handle(OptionContext context) {
        System.out.println("C");
        context.setState(this);
    }
}
