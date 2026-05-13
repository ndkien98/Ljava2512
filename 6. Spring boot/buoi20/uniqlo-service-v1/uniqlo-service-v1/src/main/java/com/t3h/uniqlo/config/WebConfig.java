package com.t3h.uniqlo.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig implements WebMvcConfigurer {
    // Khong can cau hinh ResourceHandler nua vi da su dung API FileResource de load anh
}
