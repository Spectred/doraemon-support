package com.doraemon.s.factorypattern;

import com.doraemon.s.domain.Option;
import com.doraemon.s.domain.OptionA;
import com.doraemon.s.domain.OptionB;
import com.doraemon.s.domain.OptionC;
import com.doraemon.s.domain.OptionD;

public class OptionFactory implements AbstractOptionFactory {

    @Override
    public Option createOption(String type) {
        Option opt;
        switch (type) {
            case "A":
                opt = new OptionA();
                break;
            case "B":
                opt = new OptionB();
                break;
            case "C":
                opt = new OptionC();
                break;
            case "D":
                opt = new OptionD();
                break;
            default:
                throw new IllegalArgumentException("Invalid VAR");
        }
        return opt;
    }
}
