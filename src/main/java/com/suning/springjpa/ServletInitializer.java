package com.suning.springjpa;

import com.suning.springjpa.SpringbootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.support.SpringBootServletInitializer;

public class ServletInitializer extends SpringBootServletInitializer {
    protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
	return application.sources(new Class[] { SpringbootApplication.class });
    }
}