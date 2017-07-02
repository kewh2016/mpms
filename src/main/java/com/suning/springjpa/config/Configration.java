package com.suning.springjpa.config;

import com.suning.springjpa.interceptor.LoginInterceptor;
import java.io.File;
import java.util.concurrent.ThreadPoolExecutor.CallerRunsPolicy;
import org.apache.tomcat.jdbc.pool.DataSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

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

    @Bean
    public DataSource getDateSource() {
	DataSource dataSource = new DataSource();
	String path = "jdbc:h2:file:" + System.getProperty("user.dir") + File.separator + "db/mpms;AUTO_SERVER=TRUE";
	path = path.replaceAll("\\\\", "/");
	dataSource.setUrl(path);
	dataSource.setUsername("root");
	dataSource.setPassword("root");
	dataSource.setDriverClassName("org.h2.Driver");
	return dataSource;
    }

    public void addInterceptors(InterceptorRegistry registry) {
	registry.addInterceptor(new LoginInterceptor()).addPathPatterns(new String[] { "/**" })
		.excludePathPatterns(new String[] { "/login", "/loginSubmit" });
    }
}