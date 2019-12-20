package com.doraemon;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

/**
 * 富文本解析
 */
public class RichText {

    private static final String FFFF="";

    public static void main(String[] args) {

        new Thread(()->{
            for (int i = 0; i < 10; i++) {
                System.out.print("1");
            }
        }).start();

        for (int i = 0; i < 10; i++) {
            System.out.print("0");
        }

        String html="<h3>手机银行测试不带标题</h3><p>asdfasdfasdfas</p><p>BBBBBf发送</p> <p><a href='www' >立即申请</a></p>";
        Document parse = Jsoup.parse(html);

        Elements p = parse.getElementsByTag("p");
        for (Element element : p) {

            System.out.println(element.text());
        }

        Elements h3 = parse.getElementsByTag("h3");
        System.out.println(h3.text());
        Elements a = parse.getElementsByTag("a");
        System.out.println(a.text()+":"+a.first().attr("href"));
    }
}
