package com.infoservice.init.infothreads;

import com.infoservice.init.infothreads.config.CorsConfig;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.context.annotation.Import;

@SpringBootApplication
@Import({CorsConfig.class})
public class InfoThreadsApplication extends SpringBootServletInitializer {

    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
        return application.sources(InfoThreadsApplication.class);
    }

    public static void main(String[] args) {
        SpringApplication.run(InfoThreadsApplication.class, args);
    }
}
