package com.example.springmvc;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author zx
 * @since 2022/9/25 3:30
 */
@Controller
@RequestMapping("test")
public class TestController {
    private static final Logger logger = LogManager.getLogger(TestController.class);

    @GetMapping("test.do")
    public String test(Model model) {
        logger.info("vegetable");
        model.addAttribute("vegetable", "卷心菜");
        // 跳转页面不能用@ResponseBody注解，因为@ResponseBody是将方法的返回值封装成方法的返回类型返回到页面上
        return "user/test";
    }

    @GetMapping(value = "html.do", name = "请求转发")
    public void html(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.getRequestDispatcher("/static/test.html").forward(request, response);
    }

    @GetMapping(value = "redirect.do",name = "重定向")
    public void redirect(HttpServletRequest request, HttpServletResponse response) throws IOException {
        response.sendRedirect("http://localhost:6002/test/test.do");
    }
}
