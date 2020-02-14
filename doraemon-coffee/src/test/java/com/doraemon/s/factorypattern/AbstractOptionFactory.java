package com.doraemon.s.factorypattern;

import com.doraemon.s.domain.Option;

public interface AbstractOptionFactory {

    Option createOption(String type);
}
