package com.doraemon.s.enums;

import com.doraemon.s.domain.Option;
import com.doraemon.s.domain.OptionA;
import com.doraemon.s.domain.OptionB;
import com.doraemon.s.domain.OptionC;
import com.doraemon.s.domain.OptionD;

public enum OptionsEnum {

    A {
        @Override
        public Option mark() {
            return new OptionA();
        }
    },
    B {
        @Override
        public Option mark() {
            return new OptionB();
        }
    },
    C {
        @Override
        public Option mark() {
            return new OptionC();
        }
    },
    D {
        @Override
        public Option mark() {
            return new OptionD();
        }
    };

    public abstract Option mark();


}
