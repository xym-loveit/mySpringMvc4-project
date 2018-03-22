package com.xym.spring4.web;

import com.xym.spring4.service.PushService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.context.request.async.DeferredResult;

/**
 * 异步任务实现是通过控制器从另外一个线程返回一个DeferredResult，这里的DeferredResult是从pushService中获得的。
 * ① 定时任务，定时更新DeferredResult。
 * ② 返回给客户端DeferredResult。
 *
 * @author xym
 */
@Controller
public class AysncController {

    @Autowired
    private PushService pushService;

    @RequestMapping("/defer")
    @ResponseBody
    public DeferredResult<String> deferredCall() {
        return pushService.getAsyncUpdate();
    }

}
