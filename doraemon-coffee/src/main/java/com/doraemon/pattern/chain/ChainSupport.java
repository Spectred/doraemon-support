package com.doraemon.pattern.chain;

import lombok.Getter;
import lombok.Setter;

/**
 * 责任链-工具类
 *
 * @author SWD
 */
@Setter
@Getter
public class ChainSupport {

    private static final ThreadLocal<ChainSupport> SUPPORT_THREAD_LOCAL = ThreadLocal.withInitial(ChainSupport::new);

    /**
     * 责任链是否继续的状态
     */
    private boolean shouldTransmit;

    private ChainSupport() {
        // 初始赋值
        this.shouldTransmit = true;
    }


    /**
     * 是否应该继续链的向下传递
     */
    public static boolean shouldTransmit() {
        return SUPPORT_THREAD_LOCAL.get().isShouldTransmit();
    }

    /**
     * 打断链,不再向下传递
     */
    public static void brokeChain() {
        SUPPORT_THREAD_LOCAL.get().setShouldTransmit(false);
    }

    public static void remove() {
        SUPPORT_THREAD_LOCAL.remove();
    }


}
