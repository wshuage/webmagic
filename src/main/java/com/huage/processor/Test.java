package com.huage.processor;

public class Test {
    public static void main(String[] args) {
        String url = "http://www.duwenzhang.com/wenzhang/aiqingwenzhang/shanggan/20190919/407838.html";
        String regex = "http://www\\.duwenzhang\\.com/wenzhang/aiqingwenzhang/shanggan/[0-9]{8}/[0-9]{6}\\.html";
        boolean isMatchurl = url.matches(regex);
        System.out.println(isMatchurl);

        String url1 = "http://www.win4000.com/wallpaper_detail_163265.html";
        String regex1 = "http://www\\.win4000\\.com/wallpaper_detail_[0-9]{6}\\.html";
        boolean isMatchurl1 = url1.matches(regex1);
        System.out.println(isMatchurl1);

        String name = "[<h1>王者荣耀上官婉儿梁祝皮肤桌面壁纸</h1>]";
        name = name.substring(0, name.length() - 6);
        name = name.substring(5);
        System.out.println(name);

        String URL = "[<img class=\"pic-large\" src=\"http://pic1.win4000.com/wallpaper/2019-11-22/5dd7593f10b9c.jpg\" alt=\"英雄联盟新英雄厄斐琉斯桌面壁纸\" title=\"英雄联盟新英雄厄斐琉斯桌面壁纸\">]";
        URL = URL.substring(29, 91);
        System.out.println(URL);
    }
}
