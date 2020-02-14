package com.doraemon.s.functional;

import com.doraemon.s.domain.Option;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.function.Function;

public final class FunctionalStatistics {

    private FunctionalStatistics() {
        throw new AssertionError();
    }

    private static final Map<String, Function<String, Option>> MAP = new HashMap<>();

    static {
        MAP.put("A", Statistics::markA);
        MAP.put("B", Statistics::markB);
        MAP.put("C", Statistics::markC);
        MAP.put("D", Statistics::markD);
    }

    public static Option mark(String type) throws Exception {
        Function<String, Option> function = MAP.get(type);
        if (Objects.isNull(function)) {
            throw new IllegalArgumentException("IllegalArgument");
        }
        return function.apply(type);
    }
}
