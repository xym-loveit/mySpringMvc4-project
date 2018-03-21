package com.xym.spring4.web;

import org.springframework.ui.Model;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.ModelAndView;

/**
 * 通过@ControllerAdvice。我们可以将对于控制器的全局配置放置在同一个位置，注解了@ControllerAdvice的类的方法可以使用@ExceptionHandler，@InitBinder，@ModelAttribute注解到方法上，这对所有注解了@RequestMapping的控制器内的方法有效。
 *
 * @author xym
 * @ExceptionHandler：用于全局处理控制器里面的异常。
 * @InitBinder：用来设置WebDataBinder，WebDataBinder用来自动绑定前台请求参数到Model中。
 * @ModelAttribute：@ModelAttribute本来的作用是绑定键值对到Model里，此处是让全局的@RequestMapping都能获得在此处设置的键值对。 <p>
 * <p>
 * </p>
 * ① @ControllerAdvice声明一个控制器建言，@ControllerAdvice组合了@Component注解，所以自动注册为Spring的Bean。
 * ② @ExceptionHandler在此处定义全局处理，通过@ExceptionHandler的value属性可过滤拦截的条件，在此处可以看出拦截的是所有的Exception。
 * ③ 此处使用@ModelAttribute注解将键值对添加到全局，所有注解了@RequestMapping的方法可获得此键值对。
 * ④ 通过@InitBinder注解定制WebDataBinder。
 */
@ControllerAdvice
public class ExceptionHandlerAdvice {

    @ExceptionHandler
    public ModelAndView exception(Exception exception, WebRequest request) {
        ModelAndView modelAndView = new ModelAndView("error");
        modelAndView.addObject("errorMessage", exception.getMessage());
        return modelAndView;
    }

    @ModelAttribute
    public void addAttributes(Model model) {
        model.addAttribute("msg", "额外信息");
    }

    @InitBinder
    public void initBinder(WebDataBinder webDataBinder) {
        webDataBinder.setDisallowedFields("id");
    }
}
