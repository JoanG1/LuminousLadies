package com.luminousladies.investorSystem;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
public class InvestorSystemApplication {

	public static void main(String[] args) {
		SpringApplication.run(InvestorSystemApplication.class, args);
	}

}
