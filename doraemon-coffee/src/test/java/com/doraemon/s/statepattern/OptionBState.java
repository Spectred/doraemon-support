package com.doraemon.s.statepattern;

public class OptionBState implements OptionState {
    @Override
    public void handle(OptionContext context) {
        System.out.println("B");
        context.setState(this);
    }
}
