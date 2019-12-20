package com.huage.processor;

import us.codecraft.webmagic.Page;
import us.codecraft.webmagic.Site;
import us.codecraft.webmagic.Spider;
import us.codecraft.webmagic.processor.PageProcessor;

public class PictureProcessor implements PageProcessor {
    // 抓取网站的相关配置，包括编码、抓取间隔、重试次数等
    private Site site = Site.me().setRetryTimes(3).setSleepTime(100);

    public void process(Page page) {
        // 匹配该url的网址进行爬取操作
        String regex = "http://www\\.win4000\\.com/wallpaper_detail_[0-9]{6}\\.html";
        // 要爬取内容的selector
        String urlSelector = "body > div.main > div > div.pic_main > div > div.col-main > div.main-wrap > div.pic-meinv > a > img ";
        String nameSelector = "body > div.main > div > div.pic_main > div > div.Bigimg > div.ptitle > h1";
        // 图片页
        if (page.getUrl().regex(regex).match()) {
            page.putField("URL", page.getHtml().$(urlSelector).all().toString());
            page.putField("NAME", page.getHtml().$(nameSelector).all().toString());
        } else {
            // 这里没有用xpath,使用了selector
            // 详情页url
            String detailHref = "body > div.main > div > div.w1180.clearfix > div.Left_bar > div.list_cont.Left_list_cont > div > div > div > ul > li > a";
            // 下一页url
            String nextSelector = "body > div.main > div > div.w1180.clearfix > div.Left_bar > div.pages > div > a.next";
            page.addTargetRequests(page.getHtml().$(detailHref).links().all());
            page.addTargetRequests(page.getHtml().$(nextSelector).links().all());
        }
    }

    public Site getSite() {
        return site;
    }

    public static void main(String[] args) {
        Spider.create(new PictureProcessor()).addUrl("http://www.win4000.com/wallpaper_191_0_0_1.html")
                .addPipeline(new DownloadPipeline())
                .thread(1).run();
    }
}
