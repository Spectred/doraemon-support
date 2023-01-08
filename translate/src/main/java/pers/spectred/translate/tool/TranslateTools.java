package pers.spectred.translate.tool;

import pers.spectred.translate.dto.YouDaoResponse;

public final class TranslateTools {

    private TranslateTools() {
    }

    public static YouDaoResponse translate(String word, int mode) {
        return translateByYouDao(word);
    }

    private static YouDaoResponse translateByYouDao(String word) {
        return YouDaoTranslateTool.translate(word);
    }
}
