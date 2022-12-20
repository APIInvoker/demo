package com.example.springmvc;

import javax.annotation.Nonnull;

import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.Controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 该Controller在applicationContext.xml中配置，作用等同于@Controller
 *
 * @author zx
 * @since 2022/9/25 4:30
 */
public class HeroController implements Controller {
    @Override
    public ModelAndView handleRequest(@Nonnull HttpServletRequest request, @Nonnull HttpServletResponse response) {
        // 默认使用请求转发
        ModelAndView mv = new ModelAndView("hero");
        mv.addObject("hero","Invoker");
        return mv;
    }
}