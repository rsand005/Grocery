package com.DailyGroceryShop;

import org.springframework.boot.SpringApplication;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

//@SpringBootApplication
@SpringBootApplication(exclude = { SecurityAutoConfiguration.class })
public class DailyGroceryShopApplication {

	public static void main(String[] args) throws Exception {
		SpringApplication.run(DailyGroceryShopApplication.class, args);

		//String password = "reetu"; //$2a$10$R6P9.1.qdlfH/cFdtEF73.cVMD6Wrv3Zc/5IO/qzJfQNIv/Iba0WO
		//String password = "kate";  //$2a$10$Wcv8MTXZ3AlrO0YTTbWC5exQO8OcAEvRq153dBnJoDShyNtodNMi2
		//BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
		//String hashedPassword = passwordEncoder.encode(password);
		//System.out.println("_HP_: hashedPassword =  " + hashedPassword);
	}

}