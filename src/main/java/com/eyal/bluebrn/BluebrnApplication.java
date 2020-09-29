package com.eyal.bluebrn;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@SpringBootApplication
public class BluebrnApplication {
	 
	public static void main(String[] args) {
	    log.info("sarting app ..");
	 
		SpringApplication.run(BluebrnApplication.class, args);
	}
}
