package com.doraemon.s.supplier;

import com.doraemon.s.domain.Option;
import com.doraemon.s.domain.OptionA;
import com.doraemon.s.domain.OptionB;
import com.doraemon.s.domain.OptionC;
import com.doraemon.s.domain.OptionD;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.function.Supplier;

public class OptionsSupplier {

    private static final Map<String, Supplier<Option>> OPTION_SUPPLIER_MAP;

    static {
        final Map<String, Supplier<Option>> optionSupplierMap = new HashMap<>();

        optionSupplierMap.put("A", OptionA::new);
        optionSupplierMap.put("B", OptionB::new);
        optionSupplierMap.put("C", OptionC::new);
        optionSupplierMap.put("D", OptionD::new);

        OPTION_SUPPLIER_MAP = optionSupplierMap;
    }

    public Option supplyPlayer(String type) {
        Supplier<Option> supplier = OPTION_SUPPLIER_MAP.get(type);
        if (Objects.isNull(supplier)) {
            throw new IllegalArgumentException("IllegalArgument");
        }
        return supplier.get();
    }
}
