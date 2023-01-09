package pers.spectred.translate.util;

public final class TranslateUtils {

    private TranslateUtils() {
    }

    public static final String WORD_RANK = "word:rank";

    public static String key(String query) {
        int s = query.charAt(0) - 'a';
        return "word:" + s;
    }


}
