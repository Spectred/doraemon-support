package com.doraemon.s.statepattern;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class OptionContext {

    private OptionState state;

    public OptionContext() {
        state = null;
    }
}
