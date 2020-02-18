package com.doraemon.threads.threadlocal;

import lombok.Data;

import java.util.Objects;

/**
 * 使用ThreadLocal构建上下文
 *
 * @author SWD
 */
@Data
public class LocalContext {

    private static final ThreadLocal<LocalContext> LOCAL_CONTEXT = ThreadLocal.withInitial(LocalContext::new);

    private String attr1;

    private Object attr2;

    /**
     * 初始化属性
     */
    private LocalContext() {
        this.attr1 = "Hello";
        this.attr2 = new Object();
    }

    /**
     * 获取LocalContext实例
     *
     * @return LocalContext实例
     */
    public static LocalContext getInstance() {
        if (Objects.isNull(LOCAL_CONTEXT.get())) {
            LOCAL_CONTEXT.set(new LocalContext());
        }
        return LOCAL_CONTEXT.get();
    }

    /**
     * REMOVE
     */
    public static void remove() {
        LOCAL_CONTEXT.remove();
    }

    /**
     * 赋值属性1
     *
     * @param attr
     */
    public static void assignAttr1(String attr) {
        getInstance().setAttr1(attr);
    }

    /**
     * 赋值属性2
     *
     * @param attr
     */
    public static void assignAttr2(Object attr) {
        getInstance().setAttr2(attr);
    }

}
