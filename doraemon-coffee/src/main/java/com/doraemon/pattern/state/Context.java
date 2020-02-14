package com.doraemon.pattern.state;

/**
 * 状态模式 - 上下文
 *
 * @author SWD
 */
public class Context {

    public static final BaseState STATE_1 = new ConcreteState1();

    public static final BaseState STATE_2 = new ConcreteState2();

    public static final BaseState STATE_3 = new ConcreteState3();

    /**
     * 当前状态
     */
    private BaseState currentState;


    /**
     * 转换状态
     *
     * @param currentState 当前状态
     */
    public void convertCurrentState(BaseState currentState) {
        this.currentState = currentState;
        this.currentState.setContext(this);
    }

    // 行为委托

    public void handle1() {
        this.currentState.handle1();
    }

    public void handle2() {
        this.currentState.handle2();
    }
}
