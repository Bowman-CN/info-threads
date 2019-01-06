package com.infoservice.init.infothreads;

import com.infoservice.init.infothreads.config.CorsConfig;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Import;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@SpringBootApplication
//@Import({CorsConfig.class})
public class InfoThreadsApplication extends SpringBootServletInitializer {

    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
        return application.sources(InfoThreadsApplication.class);
    }

    public static void main(String[] args) {
        SpringApplication.run(InfoThreadsApplication.class, args);
    }

//    @Bean
//    public WebMvcConfigurer corsConfig() {
//        return new WebMvcConfigurer() {
//            @Override
//            public void addCorsMappings(CorsRegistry registry) {
//
//                registry.addMapping("/**")
//                        .allowedOrigins("*")
//                        .allowedMethods("OPTIONS", "GET", "POST", "PUT", "DELETE", "HEAD")
//                        .allowCredentials(true)
//                ;
//            }
//        };
//    }
}
