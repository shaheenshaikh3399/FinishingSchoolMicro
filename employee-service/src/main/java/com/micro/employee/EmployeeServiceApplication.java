package com.micro.employee;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.context.annotation.Bean;
import org.springframework.web.reactive.function.client.WebClient;

@SpringBootApplication
//@EnableFeignClients
@EnableEurekaClient
public class EmployeeServiceApplication {
	//Communication using REstTemplate
	/*
	 * @Bean public RestTemplate restTemplate() { return new RestTemplate(); }
	 */
	 
	//Weblient 
	
	
	  @Bean public WebClient webClient() 
	  { 
		  return WebClient.builder().build();
	 
	 }
	 
	 
	  
	public static void main(String[] args) {
		SpringApplication.run(EmployeeServiceApplication.class, args);
	}

}
