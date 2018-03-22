package com.xym.spring4.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Random;

/**
 * ① 注意，这里使用输出的媒体类型为text/event-stream，这是服务器端SSE的支持，本例演示每5秒向浏览器推送随机消息。
 *
 * @author xym
 */
@Controller
public class SseController {

    @RequestMapping(value = "/push222", produces = "text/event-stream")
    public @ResponseBody
    String push() {
        Random r = new Random();
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return "data:Testing 1,2,3" + r.nextInt() + "\n\n";
    }

}
