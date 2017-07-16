package com.kewh.config;

import java.util.concurrent.ThreadPoolExecutor.CallerRunsPolicy;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

import com.kewh.interceptor.LoginInterceptor;

@Configuration
public class Configration extends WebMvcConfigurerAdapter {
    @Bean
    public ThreadPoolTaskExecutor getTaskExecutor() {
	ThreadPoolTaskExecutor threadPoolTaskExecutor = new ThreadPoolTaskExecutor();
	threadPoolTaskExecutor.setCorePoolSize(5);
	threadPoolTaskExecutor.setMaxPoolSize(100);
	threadPoolTaskExecutor.setKeepAliveSeconds(200);
	threadPoolTaskExecutor.setQueueCapacity(20);
	threadPoolTaskExecutor.setRejectedExecutionHandler(new CallerRunsPolicy());
	return threadPoolTaskExecutor;
    }

    public void addInterceptors(InterceptorRegistry registry) {
	registry.addInterceptor(new LoginInterceptor()).addPathPatterns(new String[] { "/**" })
		.excludePathPatterns(new String[] { "/login", "/loginSubmit" });
    }
}