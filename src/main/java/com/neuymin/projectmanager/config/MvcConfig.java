package com.neuymin.projectmanager.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
@ComponentScan(basePackages = "com.neuymin.projectmanager")
@EnableWebMvc
    public class MvcConfig implements WebMvcConfigurer {

        public void addViewControllers(ViewControllerRegistry registry) {
            registry.addViewController("/").setViewName("board");
            registry.addViewController("/login").setViewName("login");
        }
    }

