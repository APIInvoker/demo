package com.example.springmvc;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @Author zx
 * @Date 2022/9/25 3:30
 */
@Controller
@RequestMapping("test")
public class TestController {
    @GetMapping("test.do")
    public String test(Model model) {
        model.addAttribute("vegetable", "卷心菜");
        // 跳转页面不能用@ResponseBody注解，因为@ResponseBody是将方法的返回值封装成方法的返回类型返回到页面上
        return "user/test";
    }
}
