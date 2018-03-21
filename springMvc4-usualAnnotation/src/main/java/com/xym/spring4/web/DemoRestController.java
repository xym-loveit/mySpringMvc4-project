package com.xym.spring4.web;

import com.xym.spring4.domain.DemoObj;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * ① 使用@RestController，声明是控制器，并且返回数据时不需要@ResponseBody。
 * ② 返回数据的媒体类型为json。
 * ③ 直接返回对象，对象会自动转换成json。
 * ④ 返回数据的媒体类型为xml。
 * ⑤ 直接返回对象，对象会自动转换为xml。
 *
 * @author xym
 */
@RestController
@RequestMapping("/rest")
public class DemoRestController {

    @RequestMapping(value = "/getjson", produces = {"application/json;charset=UTF-8"})
    public DemoObj getjson(DemoObj obj) {
        return new DemoObj(obj.getId() + 1, obj.getName() + "yy");
    }

    @RequestMapping(value = "/getxml", produces = {"application/xml;charset=UTF-8"})
    public DemoObj getxml(DemoObj obj) {
        return new DemoObj(obj.getId() + 1, obj.getName() + "yy");
    }

}
