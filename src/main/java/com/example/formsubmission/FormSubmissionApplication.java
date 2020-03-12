package com.example.formsubmission;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.User;

@SpringBootApplication
public class FormSubmissionApplication {

	public static void main(String[] args) {
		SpringApplication.run(FormSubmissionApplication.class, args);
	}

}
