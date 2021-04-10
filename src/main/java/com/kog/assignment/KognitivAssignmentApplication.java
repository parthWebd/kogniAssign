package com.kog.assignment;

import java.time.LocalDate;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

import com.kog.assignment.dao.OfferRepo;
import com.kog.assignment.dto.Offer;

import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@SpringBootApplication
@EnableSwagger2
public class KognitivAssignmentApplication {

	public static void main(String[] args) {
		SpringApplication.run(KognitivAssignmentApplication.class, args);
	}

	@Bean
	public Docket productApi() {
		return new Docket(DocumentationType.SWAGGER_2).select()
				.apis(RequestHandlerSelectors.basePackage("com.kog.assignment")).build();
	}

}

@Component
class DemoCommandLineRunner implements CommandLineRunner {

	@Autowired
	private OfferRepo repo;

	@Override
	public void run(String... args) throws Exception {

		LocalDate fr = LocalDate.of(2021, 1, 13);
		LocalDate to = LocalDate.of(2027, 1, 13);
		Offer offer = new Offer("Sam", "Bangluru", fr, to);
		repo.save(offer);

	}
}
