package com.doraemon.s.statepattern;

import com.doraemon.s.domain.Option;

public interface OptionState {

    void handle(OptionContext context);
}
