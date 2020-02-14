package com.doraemon.s;

import com.doraemon.s.command.OptionsMarkCommand;
import com.doraemon.s.domain.Option;
import com.doraemon.s.domain.OptionA;
import com.doraemon.s.domain.OptionB;
import com.doraemon.s.domain.OptionC;
import com.doraemon.s.domain.OptionD;
import com.doraemon.s.enums.OptionsEnum;
import com.doraemon.s.factorypattern.OptionFactory;
import com.doraemon.s.functional.FunctionalStatistics;
import com.doraemon.s.statepattern.OptionAState;
import com.doraemon.s.statepattern.OptionContext;
import com.doraemon.s.statepattern.OptionDState;
import com.doraemon.s.statepattern.OptionState;
import com.doraemon.s.supplier.OptionsSupplier;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;

public class RefactorSwitchTest {

    private static String VAR;

    @BeforeClass
    public static void init() {
        VAR = "A";
    }

    @Test
    public void normalIfElseTest() {
        Option opt;
        if ("A".equals(VAR)) {
            opt = new OptionA();
        } else if ("B".equals(VAR)) {
            opt = new OptionB();
        } else if ("C".equals(VAR)) {
            opt = new OptionC();
        } else if ("D".equals(VAR)) {
            opt = new OptionD();
        } else {
            throw new IllegalArgumentException("Invalid VAR");
        }
        Assert.assertEquals("A", opt.property());
    }

    @Test
    public void normalSwitchTest() {
        Option opt;
        switch (VAR) {
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
        Assert.assertEquals("A", opt.property());
    }


    /**
     * 枚举策略
     */
    @Test
    public void enumStrategyTest() {
        Option option = OptionsEnum.valueOf(VAR).mark();
        Assert.assertEquals("A", option.property());
    }

    /**
     * 命令模式
     */
    @Test
    public void commandTest() {
        Option option = new OptionsMarkCommand().toMark(VAR);
        Assert.assertEquals("A", option.property());
    }

    /**
     * Supplier
     */
    @Test
    public void supplierTest() {
        Option option = new OptionsSupplier().supplyPlayer(VAR);
        Assert.assertEquals("A", option.property());
    }

    @Test
    public void functionTest() throws Exception {
        Option option = FunctionalStatistics.mark(VAR);
        Assert.assertEquals("A", option.property());
    }

    /**
     * 工厂模式
     */
    @Test
    public void factoryTest() {
        OptionFactory factory = new OptionFactory();
        Option option = factory.createOption(VAR);
        Assert.assertEquals("A", option.property());
    }

    @Test
    public void stateTest() {
        OptionContext context = new OptionContext();

        OptionState state = new OptionDState();
        state.handle(context);

        System.out.println(context.getState().toString());
    }


}
