package com.doraemon.pattern.chain;

import lombok.Getter;
import lombok.Setter;

import java.util.Map;
import java.util.Objects;
import java.util.concurrent.ConcurrentHashMap;

/**
 * 责任链-工具类
 *
 * @author SWD
 */
public class ChainSupport {


    /**
     * 责任链是否继续的状态
     */
    private static final ThreadLocal<Boolean> CHAIN_CONTINUE_STATE = ThreadLocal.withInitial(() -> Boolean.TRUE);


    /**
     * 是否应该继续链的向下传递
     *
     * @return 是否应该继续链的向下传递
     */
    public static boolean shouldContinue() {
        return CHAIN_CONTINUE_STATE.get();
    }

    /**
     * 打断链,不再向下传递
     */
    public static void brokeChain() {
        CHAIN_CONTINUE_STATE.set(Boolean.FALSE);
    }

    public static void remove() {
        CHAIN_CONTINUE_STATE.remove();
    }


}
