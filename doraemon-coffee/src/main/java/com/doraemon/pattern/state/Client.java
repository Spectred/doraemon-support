package com.doraemon.pattern.state;

/**
 * 状态模式 - 客户端
 *
 * @author SWD
 */
public class Client {

    public static void main(String[] args) {
        Context context = new Context();
        context.convertCurrentState(new ConcreteState1());

        context.handle1();
        System.out.println("---");
        context.handle2();
    }
}
