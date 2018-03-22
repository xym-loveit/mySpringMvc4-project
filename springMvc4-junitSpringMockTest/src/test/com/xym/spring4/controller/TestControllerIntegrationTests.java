package com.xym.spring4.controller;

import com.xym.spring4.MyMvcConfig;
import com.xym.spring4.service.DemoService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.mock.web.MockHttpSession;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

/**
 * ① @WebAppConfiguration注解在类上，用来声明加载的ApplicationContext是一个WebApplicationContext。它的属性指定的是Web资源的位置，默认为src/main/webapp,本例修改为src/main/resource。
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {MyMvcConfig.class})
@WebAppConfiguration("src/main/resources")
public class TestControllerIntegrationTests {

    /*② MockMvc模拟MVC对象，通过MockMvcBuilders.webAppContextSetup(this.wac).build()进行初始化*/
    private MockMvc mockMvc;
    /*③ 可以在测试用例中注入Spring的Bean*/
    @Autowired
    private DemoService demoService;
    /*④ 可注入WebApplicationContext*/
    @Autowired
    private WebApplicationContext applicationContext;
    /*⑤ 可注入模拟的http session，此处仅作演示，没有使用*/
    @Autowired
    MockHttpSession httpSession;
    /*⑥ 可注入模拟的http request，此处仅作演示，没有使用*/
    @Autowired
    MockHttpServletRequest request;


    /*⑦ @Before 在测试开始前进行的初始化工作。*/
    @Before
    public void setup() {
        mockMvc = MockMvcBuilders.webAppContextSetup(this.applicationContext).build();
    }


    @Test
    public void testNormalController() throws Exception {
        mockMvc.perform(get("/normal"))//⑧ 模拟向/normal进行get请求。
                .andExpect(status().isOk())//⑨ 预期控制返回状态为200.
                .andExpect(view().name("page"))//⑩ 预期view的名称为page
                .andExpect(forwardedUrl("/WEB-INF/classes/views/page.jsp"))//11 预期页面转向的真正路径为/WEB-INF/classes/views/page.jsp
                .andExpect(model().attribute("msg", demoService.saySomething()));//12 预期model里面的值是demoService.saySomething()返回值hello

    }

    @Test
    public void testRestController() throws Exception {
        mockMvc.perform(get("/testRest"))//模拟向/testRest进行get请求
                .andExpect(status().isOk())
                .andExpect(content().contentType("text/plain;charset=UTF-8"))//预期返回值的媒体类型是text/plain;charset=UTF-8
                .andExpect(content().string(demoService.saySomething()));//预期返回值的内容为demoService.saySomething()返回值hello
    }
}


