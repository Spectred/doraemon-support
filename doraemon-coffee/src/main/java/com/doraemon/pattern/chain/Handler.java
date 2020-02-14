package com.doraemon.pattern.chain;


public interface Handler {

    /**
     * 该链是否继续执行
     *
     * @return
     */
    boolean shouldHandle();

    /**
     * 具体执行
     *
     * @param msg
     * @return
     */
    String handle(String msg);
}
