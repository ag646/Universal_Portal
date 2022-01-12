package com.dal.universityPortal;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@SpringBootApplication
@Controller
public class UniversityPortalApplication {

	public static void main(String[] args) {
		SpringApplication.run(UniversityPortalApplication.class, args);
	}
	@GetMapping("/health_check")
	public String healthCheck() {
		return "health_check";
	}
}
