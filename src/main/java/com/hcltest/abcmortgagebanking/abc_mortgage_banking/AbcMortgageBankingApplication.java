package com.hcltest.abcmortgagebanking.abc_mortgage_banking;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@SpringBootApplication
public class AbcMortgageBankingApplication {

	public static void main(String[] args) {
		SpringApplication.run(AbcMortgageBankingApplication.class, args);
	}

    @Bean
    PasswordEncoder getPasswordEncoder() {
        return NoOpPasswordEncoder.getInstance();
    }
}
