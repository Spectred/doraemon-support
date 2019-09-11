package com.doraemon;

import java.util.HashMap;
import java.util.Map;

/**
 * 字符串语言判断工具类
 *
 * @author SWD
 */
public class StringLanguageUtil {

    /**
     * 中文
     */
    public static final String CHINESE = "zh";

    /**
     * English
     */
    public static final String ENGLISH = "en";

    /**
     * 其他
     */
    public static final String OTHER = "other";


    /**
     * 语言和正则表达式的映射
     */
    private static final Map<String, String> LANGUAGE_REGEX_MAP = new HashMap<>(1 << 4);

    static {
        LANGUAGE_REGEX_MAP.put(CHINESE, "[\\u4e00-\\u9fa5]+");
        LANGUAGE_REGEX_MAP.put(ENGLISH, "^[a-zA-Z]*");
    }


    /**
     * 判断字符串属于哪种语言
     *
     * @param str 待判断的字符串
     * @return 所属语言
     */
    public static String getLanguage(String str) {
        for (Map.Entry<String, String> entry : LANGUAGE_REGEX_MAP.entrySet()) {
            if (str.matches(entry.getValue())) {
                return entry.getKey();
            }
        }
        return OTHER;
    }

}
