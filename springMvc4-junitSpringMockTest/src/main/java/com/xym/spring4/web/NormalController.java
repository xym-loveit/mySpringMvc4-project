package com.xym.spring4.web;

import com.xym.spring4.service.DemoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class NormalController {

    @Autowired
    DemoService demoService;

    @RequestMapping("/normal")
    public  String testPage(Model model){
        model.addAttribute("msg", demoService.saySomething());
        return "page";
    }
}
