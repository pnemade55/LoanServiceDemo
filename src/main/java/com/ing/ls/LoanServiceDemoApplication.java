package com.ing.ls;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class LoanServiceDemoApplication {

	private static final Logger logger = LoggerFactory.getLogger(LoanServiceDemoApplication.class);

	public static void main(String[] args) {

		SpringApplication.run(LoanServiceDemoApplication.class, args);
		logger.info("LoanServiceDemoApplication started successfully");

	}

}
