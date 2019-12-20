package com.huage.processor;

import us.codecraft.webmagic.Page;
import us.codecraft.webmagic.Site;
import us.codecraft.webmagic.Spider;
import us.codecraft.webmagic.pipeline.ConsolePipeline;
import us.codecraft.webmagic.pipeline.JsonFilePipeline;
import us.codecraft.webmagic.processor.PageProcessor;

public class SanWenProcessor implements PageProcessor {
    // 抓取网站的相关配置，包括编码、抓取间隔、重试次数等
    private Site site = Site.me().setRetryTimes(3).setSleepTime(100);

    public void process(Page page) {
        String regex = "http://www\\.duwenzhang\\.com/wenzhang/aiqingwenzhang/shanggan/[0-9]{8}/[0-9]{2,6}\\.html";
        String titleXPath = "/html/body/center/table[4]/tbody/tr/td[1]/table[2]/tbody/tr[2]/td[2]/table/tbody/tr[1]/td/h1/text()";
        String typeXPath = "/html/body/center/table[4]/tbody/tr/td[1]/table[1]/tbody/tr/td[2]/a[3]/text()";
        String contentXPath = "//*[@id=\"wenzhangziti\"]/p/text()";
        // 文章页
        if (page.getUrl().regex(regex).match()) {
            page.putField("Title", page.getHtml().xpath(titleXPath).toString());
            page.putField("Type", page.getHtml().xpath(typeXPath).toString());
            page.putField("Content", page.getHtml().xpath(contentXPath).all().toString());
        }
        // 列表页
        else {
            // /html/body/center/table[5]/tbody/tr[2]/td[1]/table[3]/tbody/tr/td/a[11]
            String titleHref = "/html/body/center/table[5]/tbody/tr[2]/td[1]/table[2]/tbody/tr[2]/td[2]/table/tbody/tr[2]/td[2]/b/a/@href";
            String nextPageHref = "/html/body/center/table[5]/tbody/tr[2]/td[1]/table[3]/tbody/tr/td/a/@href";
            // 文章url
            page.addTargetRequests(page.getHtml().xpath(titleHref).all());
            // 翻页url
            page.getHtml().xpath(nextPageHref).regex(".*下一页.*").all();
            page.addTargetRequests(page.getHtml().xpath(nextPageHref).all());
        }
    }

    public Site getSite() {
        return site;
    }

    public static void main(String[] args) {
        Spider.create(new SanWenProcessor()).addUrl("http://www.duwenzhang.com/wenzhang/aiqingwenzhang/shanggan/").addPipeline(new MyPipeline())
                .thread(1).run();
    }
}
