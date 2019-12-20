package com.huage.processor;

import us.codecraft.webmagic.ResultItems;
import us.codecraft.webmagic.Task;
import us.codecraft.webmagic.pipeline.Pipeline;


import java.io.IOException;
import java.io.InputStream;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;

public class MyPipeline implements Pipeline {
    public MyPipeline() {
    }
    public void process(ResultItems resultitems, Task task) {
        Map<String, Object> mapResults = resultitems.getAll();
        Iterator<Entry<String, Object>> iter = mapResults.entrySet().iterator();
        Map.Entry<String, Object> entry;
        // 输出到控制台
        while (iter.hasNext()) {
            entry = iter.next();
            System.out.println(entry.getKey() + " : " + entry.getValue());
        }
    }
}
