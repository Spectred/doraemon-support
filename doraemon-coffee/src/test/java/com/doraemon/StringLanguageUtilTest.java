package com.doraemon;

import org.junit.Assert;
import org.junit.Test;

import static com.doraemon.StringLanguageUtil.CHINESE;
import static com.doraemon.StringLanguageUtil.ENGLISH;
import static com.doraemon.StringLanguageUtil.OTHER;
import static org.junit.Assert.*;

public class StringLanguageUtilTest {

    @Test
    public void getLanguage() {
        String enStr = "halo";
        String zhStr = "哈喽";
        String numStr = "123";

        String enLang = StringLanguageUtil.getLanguage(enStr);
        String zhLang = StringLanguageUtil.getLanguage(zhStr);
        String other = StringLanguageUtil.getLanguage(numStr);


        Assert.assertEquals(ENGLISH, enLang);
        Assert.assertEquals(CHINESE, zhLang);
        Assert.assertEquals(OTHER, other);
    }
}