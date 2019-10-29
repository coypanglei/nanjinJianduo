package com.shaoyue.weizhegou.util;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

/**
 * 作者：PangLei on 2019/6/4 0004 11:16
 * <p>
 * 邮箱：434604925@qq.com
 */
public class WebViewUtils {
    /**
     * 获取新的内容
     *
     * @param htmltext
     * @return
     */
    public static String getNewContent(String htmltext) {
        Document doc = Jsoup.parse(htmltext);
        Elements elements = doc.getElementsByTag("img");
        for (Element element : elements) {
            element.attr("width", "100%").attr("height", "auto");
        }
        return doc.toString();
    }

    /**
     * 将毫秒转时分秒
     */
    public static String generateTime(long time) {
        String format;
        int totalSeconds = (int) (time / 1000);
        int minutes = totalSeconds % 86400 % 3600 / 60;
        int seconds = totalSeconds % 86400 % 3600 % 60 % 60;
        int hours = totalSeconds % 86400 / 3600;
        int day = totalSeconds / 86400;
        format = String.format("%02d天%02d时%02d分%02d秒", day, hours, minutes, seconds);
        return format;
    }

}
