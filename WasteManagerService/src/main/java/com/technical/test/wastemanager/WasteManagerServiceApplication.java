package com.technical.test.wastemanager;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;


@EnableFeignClients
@SpringBootApplication
public class WasteManagerServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(WasteManagerServiceApplication.class, args);
	}

}
