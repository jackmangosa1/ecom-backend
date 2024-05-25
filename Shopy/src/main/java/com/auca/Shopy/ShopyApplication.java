package com.auca.Shopy;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@SpringBootApplication
@ComponentScan(basePackages = "com.auca.Shopy")
public class ShopyApplication {

	public static void main(String[] args) {
		SpringApplication.run(ShopyApplication.class, args);
	}

}
