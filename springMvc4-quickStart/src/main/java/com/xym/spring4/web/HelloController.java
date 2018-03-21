package com.xym.spring4.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * ① 利用@Controller注解声明是一个控制器。
 * ② 利用@RequestMapping配置URL和方法之间的映射。
 * ③ 通过上面ViewResolver的Bean配置，返回值为index，说明我们的页面放置的路径为/WEB-INF/classes/views/。
 *
 * @author xym
 */
@Controller
public class HelloController {

    @RequestMapping("/index")
    public String hello() {
        return "index";
    }
}
