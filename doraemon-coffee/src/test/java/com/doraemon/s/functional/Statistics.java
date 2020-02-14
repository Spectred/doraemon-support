package com.doraemon.s.functional;

import com.doraemon.s.domain.Option;
import com.doraemon.s.domain.OptionA;
import com.doraemon.s.domain.OptionB;
import com.doraemon.s.domain.OptionC;
import com.doraemon.s.domain.OptionD;

public final class Statistics {

    private Statistics() {
        throw new AssertionError("Not supported");
    }

    public static Option markA(String option) {
        return new OptionA();
    }

    public static Option markB(String option) {
        return new OptionB();
    }

    public static Option markC(String option) {
        return new OptionC();
    }

    public static Option markD(String option) {
        return new OptionD();
    }
}
