package com.fattazzo.pizzashop;

import java.util.Comparator;
import java.util.TimeZone;

import javax.annotation.PostConstruct;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import com.fattazzo.pizzashop.service.initializer.Initializer;

import springfox.documentation.swagger2.annotations.EnableSwagger2;

@SpringBootApplication
@EnableSwagger2
public class PizzaShopApplication {

	protected static final Logger logger = LoggerFactory.getLogger(PizzaShopApplication.class);

	public static void main(String[] args) {
		SpringApplication.run(PizzaShopApplication.class, args);
	}

	@Autowired
	private java.util.List<Initializer> appInitializer;

	@Bean
	public CommandLineRunner initData() {
		return (args) -> {
			appInitializer.stream().sorted(Comparator.comparingInt(Initializer::priority)).forEach(i -> i.init());
		};
	}

	@PostConstruct
	void started() {
		TimeZone.setDefault(TimeZone.getTimeZone("UTC"));
	}

}
