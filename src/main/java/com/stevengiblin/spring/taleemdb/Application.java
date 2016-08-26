package com.stevengiblin.spring.taleemdb;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@SpringBootApplication
@EnableTransactionManagement
@EnableAsync
public class Application {

	private static final Logger logger = LoggerFactory.getLogger(Application.class);
	
    public static void main(String[] args) {
        
   		SpringApplication.run(Application.class, args);

    }
}
