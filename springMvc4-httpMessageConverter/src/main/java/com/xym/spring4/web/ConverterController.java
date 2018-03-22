package com.xym.spring4.web;

import com.xym.spring4.domain.DemoObj;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @author xym
 */
@Controller
public class ConverterController {

    /**
     * response响应Content-Type为自定义application/xym格式
     * ① 指定返回的媒体类型为我们自定义的媒体类型application/xym
     *
     * @param demoObj
     * @return
     */
    @RequestMapping(value = "/convert", produces = {"application/xym"})
    public @ResponseBody
    DemoObj convert(@RequestBody DemoObj demoObj) {
        return demoObj;
    }
}
