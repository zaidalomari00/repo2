package com.mhawi.slider.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.Ordered;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WelcomePageRedirect implements WebMvcConfigurer {

    public static final String myExternalFilePath = "file:///";
    @Value("${file.path}")
    public String filePath;

    @Override
    public void addViewControllers(ViewControllerRegistry registry) {

        registry.addViewController("/")
                .setViewName("forward:/index.xhtml");

        registry.addViewController("/login")
                .setViewName("forward:/admin/login.xhtml");

        registry.addViewController("/login.xhtml")
                .setViewName("forward:/admin/login.xhtml");

        registry.addViewController("/view")
                .setViewName("forward:/index.xhtml");

        registry.addViewController("/adminHome")
                .setViewName("forward:/admin/index.xhtml");

        registry.setOrder(Ordered.HIGHEST_PRECEDENCE);
    }

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/images/**")
                .addResourceLocations(myExternalFilePath + filePath)
                .setCachePeriod(3600 * 24);
    }
}
