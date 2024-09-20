package com.ing.ls;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
@RestController
public class LoanServiceDemoApplication {

	public static void main(String[] args) {
		SpringApplication.run(LoanServiceDemoApplication.class, args);
	}

	@RequestMapping(value = "/ping")
	public String ping(){
		return "App is running fine!";

	}

}
