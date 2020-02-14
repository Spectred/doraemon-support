package com.doraemon.pattern.state;

/**
 * 状态模式 - 状态3
 *
 * @author SWD
 */
public class ConcreteState3 extends BaseState {
    @Override
    public void handle1() {
        System.out.println("3-1 is running");
    }

    @Override
    public void handle2() {
        System.out.println("3-2 is running");
        System.out.println("Convert State: 3 -> 1");
        super.context.convertCurrentState(Context.STATE_1);
        super.context.handle1();
    }
}
