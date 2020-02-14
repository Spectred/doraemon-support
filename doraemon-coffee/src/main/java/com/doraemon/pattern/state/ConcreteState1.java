package com.doraemon.pattern.state;

/**
 * 状态模式 - 状态1
 *
 * @author SWD
 */
public class ConcreteState1 extends BaseState {
    @Override
    public void handle1() {
        System.out.println("1-1 is running");
    }

    @Override
    public void handle2() {
        System.out.println("1-2 is running");
        System.out.println("Convert State: 1 -> 2");
        super.context.convertCurrentState(Context.STATE_2);
        super.context.handle2();
    }
}
