package com.doraemon.pattern.state;

/**
 * 状态模式 - 状态2
 *
 * @author SWD
 */
public class ConcreteState2 extends BaseState {
    @Override
    public void handle1() {
        System.out.println("2-1 is running");
    }

    @Override
    public void handle2() {
        System.out.println("2-2 is running");
        System.out.println("Convert State: 2 -> 3");
        super.context.convertCurrentState(Context.STATE_3);
        super.context.handle2();
    }
}
