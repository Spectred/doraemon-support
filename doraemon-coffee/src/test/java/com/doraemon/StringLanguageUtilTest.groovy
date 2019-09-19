package com.doraemon

import spock.lang.*

import static com.doraemon.StringLanguageUtil.CHINESE
import static com.doraemon.StringLanguageUtil.ENGLISH
import static com.doraemon.StringLanguageUtil.OTHER

class StringLanguageUtilTest extends Specification {


    def "test get Language"() {
        expect:
        result == StringLanguageUtil.getLanguage(str)

        where:
        str       || result
        "helloWorld"        || ENGLISH
        "哈喽"      || CHINESE
        "哈喽world" || OTHER
    }
}
