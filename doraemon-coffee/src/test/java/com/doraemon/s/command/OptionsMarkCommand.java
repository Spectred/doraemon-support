package com.doraemon.s.command;

import com.doraemon.s.domain.Option;
import com.doraemon.s.domain.OptionA;
import com.doraemon.s.domain.OptionB;
import com.doraemon.s.domain.OptionC;
import com.doraemon.s.domain.OptionD;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public class OptionsMarkCommand {

    private static final Map<String, OptionsCommand> OPTIONS_MAP;

    static {
        final Map<String, OptionsCommand> optionsMap = new HashMap<>();

        optionsMap.put("A", new OptionsCommand() {
            @Override
            public Option mark() {
                return new OptionA();
            }
        });

        optionsMap.put("B", new OptionsCommand() {
            @Override
            public Option mark() {
                return new OptionB();
            }
        });

        optionsMap.put("C", new OptionsCommand() {
            @Override
            public Option mark() {
                return new OptionC();
            }
        });

        optionsMap.put("D", new OptionsCommand() {
            @Override
            public Option mark() {
                return new OptionD();
            }
        });

        OPTIONS_MAP = Collections.unmodifiableMap(optionsMap);
    }

    public Option toMark(String option) {
        OptionsCommand optionsCommand = OPTIONS_MAP.get(option);
        if (Objects.isNull(optionsCommand)) {
            throw new IllegalArgumentException("Illegal Argument");
        }

        return optionsCommand.mark();
    }
}
