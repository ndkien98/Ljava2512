package com.t3h.uniqlo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class ApiDemoUIController {

    @GetMapping("/api-demo")
    public String showApiDemoPage(Model model) {
        // Hướng dẫn ViewResolver: Đẩy dữ liệu tĩnh từ Controller xuống Thymeleaf.
        model.addAttribute("title", "Giao diện Test Spring REST API (Postman Clone)");
        model.addAttribute("description", "Đây là giao diện UI minh hoạ các lệnh cURL và giao tiếp với @RestController");
        
        // Trả về tên file "api-guide.html" nằm trong src/main/resources/templates/
        return "api-guide";
    }
}
