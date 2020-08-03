package org.websparrow;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(scanBasePackages = "org.websparrow.*")
public class SpringBootAngularApp {

	public static void main(String[] args) {

		SpringApplication.run(SpringBootAngularApp.class, args);
	}
}
