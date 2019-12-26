package com.doraemon

import com.doraemon.util.StringLanguageUtil
import spock.lang.*

import static com.doraemon.util.StringLanguageUtil.CHINESE
import static com.doraemon.util.StringLanguageUtil.ENGLISH
import static com.doraemon.util.StringLanguageUtil.OTHER

class StringLanguageUtilTest extends Specification {


    def "test get Language"() {
        expect:
        result == StringLanguageUtil.getLanguage(str)

        where:
        str          || result
        "helloWorld" || ENGLISH
        "哈喽"         || CHINESE
        "哈喽world"    || OTHER
    }
}
