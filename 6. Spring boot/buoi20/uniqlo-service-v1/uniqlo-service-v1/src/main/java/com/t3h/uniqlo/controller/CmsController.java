package com.t3h.uniqlo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/cms")
public class CmsController {

    @GetMapping
    public String dashboard() {
        return "cms_dashboard";
    }


    @GetMapping("/products")
    public String products() {
        return "cms_products";
    }

    @GetMapping("/skus")
    public String skus() {
        return "cms_skus";
    }

    @GetMapping("/reviews")
    public String reviews() {
        return "cms_reviews";
    }

    @GetMapping("/visit-stats")
    public String visitStats() {
        return "cms_visit_stats";
    }
}

