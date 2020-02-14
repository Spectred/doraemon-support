package com.doraemon.s.statepattern;

public class OptionDState implements OptionState {
    @Override
    public void handle(OptionContext context) {
        System.out.println("D");
        context.setState(this);
    }
}
