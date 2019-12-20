package com.huage.processor;

import us.codecraft.webmagic.ResultItems;
import us.codecraft.webmagic.Task;
import us.codecraft.webmagic.pipeline.Pipeline;
import java.util.Iterator;
import java.util.Map;

public class DownloadPipeline implements Pipeline  {
    public DownloadPipeline() {
    }
    public void process(ResultItems resultitems, Task task) {
        Map<String, Object> mapResults = resultitems.getAll();
        Iterator<Map.Entry<String, Object>> iter = mapResults.entrySet().iterator();
        Map.Entry<String, Object> entry;
        String url = null;
        String name = null;
        while (iter.hasNext()) {
            entry = iter.next();
            if ("URL".equals(entry.getKey())) {
                url = entry.getValue().toString().substring(29, 91);
            }
            if ("NAME".equals(entry.getKey())) {
                name = entry.getValue().toString().substring(0, entry.getValue().toString().length() - 6).substring(5);
            }
        }
        try {
            DownloadUtil.downloadPicture(url, name);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
//                File file = new File("D:/image/", NAME + ".jpg");
//                InputStream inputStream = URLDownloadUtil
//                        .getInputStreamByGet(URL);
//                URLDownloadUtil.saveData(inputStream, file);