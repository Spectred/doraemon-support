package com.doraemon.pattern.state;

import lombok.Setter;

/**
 * 状态模式 - 基础状态
 *
 * @author SWD
 */
public abstract class BaseState {

    /**
     * 上下文
     */
    @Setter
    protected Context context;

    /**
     * 行为1
     */
    public abstract void handle1();

    /**
     * 行为2
     */
    public abstract void handle2();
}
