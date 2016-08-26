package com.stevengiblin.spring.taleemdb.config;

import java.net.URI;
import java.net.URISyntaxException;

import com.mchange.v2.c3p0.ComboPooledDataSource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.stevengiblin.spring.taleemdb.controllers.RootController;

@Configuration
public class MainConfig {

	private static final Logger logger = LoggerFactory.getLogger(RootController.class);
	
	@Bean
    public ComboPooledDataSource dataSource() throws URISyntaxException {
    	
    	String str_dbUrl = System.getenv("DATABASE_URL");
    	
        URI dbUri = new URI(str_dbUrl);

        String username = dbUri.getUserInfo().split(":")[0];
        String password = dbUri.getUserInfo().split(":")[1];
        String dbUrl = "jdbc:postgresql://" + dbUri.getHost() + ':' + dbUri.getPort() + dbUri.getPath();

        ComboPooledDataSource basicDataSource = new ComboPooledDataSource();
        basicDataSource.setPassword(password);
        basicDataSource.setUser(username);
        basicDataSource.setJdbcUrl(dbUrl);

        logger.info("Created database connection");
        return basicDataSource;
    }
	
}