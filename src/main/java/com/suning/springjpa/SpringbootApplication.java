package com.suning.springjpa;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class SpringbootApplication {
    private static final Logger LOG = LoggerFactory.getLogger(SpringApplication.class);

    public static void main(String[] args) {
	SpringApplication.run(SpringbootApplication.class, args);
	LOG.warn("OK OK OK OK OK OK");
    }
}