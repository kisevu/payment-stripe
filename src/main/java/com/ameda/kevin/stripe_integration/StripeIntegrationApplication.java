package com.ameda.kevin.stripe_integration;

import io.github.cdimascio.dotenv.Dotenv;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(scanBasePackages = {"com.ameda.kevin.stripe_integration.*","org.springdoc"})
public class StripeIntegrationApplication {

	public static void main(String[] args) {
		Dotenv dotenv = Dotenv.configure().load();
		System.setProperty("SERVER_PORT",dotenv.get("SERVER_PORT"));
		System.setProperty("STRIPE_KEY",dotenv.get("STRIPE_KEY"));
		SpringApplication.run(StripeIntegrationApplication.class, args);
	}

}
