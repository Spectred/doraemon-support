package pers.spectred.translate.tool;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import pers.spectred.translate.dto.YouDaoResponse;
import pers.spectred.translate.util.SingletonObjectMapper;

import java.io.IOException;
import java.net.URI;
import java.net.URLEncoder;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * <a href="https://ai.youdao.com/DOCSIRMA/html/%E8%87%AA%E7%84%B6%E8%AF%AD%E8%A8%80%E7%BF%BB%E8%AF%91/API%E6%96%87%E6%A1%A3/%E6%96%87%E6%9C%AC%E7%BF%BB%E8%AF%91%E6%9C%8D%E5%8A%A1/%E6%96%87%E6%9C%AC%E7%BF%BB%E8%AF%91%E6%9C%8D%E5%8A%A1-API%E6%96%87%E6%A1%A3.html">有道-文本翻译服务</a>
 */
@Slf4j
public final class YouDaoTranslateTool {


    private static final String YOUDAO_URL = "https://openapi.youdao.com/api";

    private static final String APP_KEY = "6e3f32dfc55beb49";

    private static final String APP_SECRET = "jmamKzO0Aodh9vYjy4B8wImzpzrHWf2a";

    private YouDaoTranslateTool() {
    }

    public static YouDaoResponse translate(String word) {
        YouDaoResponse response = null;
        try {
            Map<String, String> params = buildParams(word);
            response = invoke(params);
        } catch (Exception e) {
            log.error(e.getMessage(), e);
        }
        return response;
    }

    private static YouDaoResponse invoke(Map<String, String> params) throws IOException, InterruptedException {
        HttpRequest request = HttpRequest.newBuilder()
                .version(HttpClient.Version.HTTP_1_1)
                .uri(URI.create(YOUDAO_URL))
                .POST(HttpRequest.BodyPublishers.ofString(getFormDataAsString(params)))
                .header("Content-Type", "application/x-www-form-urlencoded; charset=UTF-8")
                .build();
        HttpResponse<String> response = HttpClient.newHttpClient().send(request, HttpResponse.BodyHandlers.ofString());
        // TODO 参考官方文档，会有不同的类型，这里仅仅考虑翻译的文本
        return SingletonObjectMapper.INSTANCE.get().readValue(response.body(), YouDaoResponse.class);
    }

    private static String getFormDataAsString(Map<String, String> formData) {
        return formData.entrySet().stream().map(YouDaoTranslateTool::buildURLParam).collect(Collectors.joining("&"));
    }

    private static String buildURLParam(Map.Entry<String, String> entry) {
        return URLEncoder.encode(entry.getKey(), StandardCharsets.UTF_8) + "=" + URLEncoder.encode(entry.getValue(), StandardCharsets.UTF_8);
    }

    private static Map<String, String> buildParams(String q) {
        String salt = String.valueOf(System.currentTimeMillis());
        String curtime = String.valueOf(System.currentTimeMillis() / 1000);
        String signStr = APP_KEY + truncate(q) + salt + curtime + APP_SECRET;
        String sign = getDigest(signStr);

        Map<String, String> params = new HashMap<>(16);
        params.put("from", "en");
        params.put("to", "zh-CHS");
        params.put("signType", "v3");
        params.put("curtime", curtime);
        params.put("appKey", APP_KEY);
        params.put("q", q);
        params.put("salt", salt);
        params.put("sign", sign);
        params.put("vocabId", "");
        return params;
    }

    public static String truncate(String q) {
        if (q == null) {
            return null;
        }
        int len = q.length();
        String result;
        return len <= 20 ? q : (q.substring(0, 10) + len + q.substring(len - 10, len));
    }

    public static String getDigest(String string) {
        if (string == null) {
            return null;
        }
        char hexDigits[] = {'0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'A', 'B', 'C', 'D', 'E', 'F'};
        byte[] btInput = string.getBytes(StandardCharsets.UTF_8);
        try {
            MessageDigest mdInst = MessageDigest.getInstance("SHA-256");
            mdInst.update(btInput);
            byte[] md = mdInst.digest();
            int j = md.length;
            char str[] = new char[j * 2];
            int k = 0;
            for (byte byte0 : md) {
                str[k++] = hexDigits[byte0 >>> 4 & 0xf];
                str[k++] = hexDigits[byte0 & 0xf];
            }
            return new String(str);
        } catch (NoSuchAlgorithmException e) {
            return null;
        }
    }
}
