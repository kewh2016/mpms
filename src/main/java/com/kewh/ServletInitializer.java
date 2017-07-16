package com.kewh;

import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.support.SpringBootServletInitializer;

import com.kewh.SpringbootApplication;

public class ServletInitializer extends SpringBootServletInitializer {
    protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
	return application.sources(new Class[] { SpringbootApplication.class });
    }
}