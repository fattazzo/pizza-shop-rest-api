package com.fattazzo.pizzashop.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;

@javax.annotation.Generated(value = "io.swagger.codegen.v3.generators.java.SpringCodegen", date = "2020-04-25T04:45:09.813Z[GMT]")
@Configuration
public class SwaggerDocumentationConfig implements WebMvcConfigurer {

	@Override
	public void addResourceHandlers(ResourceHandlerRegistry registry) {
		registry.addResourceHandler("swagger-ui.html").addResourceLocations("classpath:/META-INF/resources/");
		registry.addResourceHandler("/webjars/**").addResourceLocations("classpath:/META-INF/resources/webjars/");
	}

	ApiInfo apiInfo() {
		return new ApiInfoBuilder().title("PizzaShop").description(
				"<h1>REST API for managing a pizzeria.</h1> <br>  The application includes the management of:  <ul>     <li>users (workers and customers)</li>     <li>company branches</li>     <li>products (variations like doughs, dimensions and toppings, categories)</li>     <li>orders (creation, management)</li> <ul>")
				.license("MIT License").licenseUrl("https://opensource.org/licenses/MIT")
				.termsOfServiceUrl("https://gianlucafattarsi.github.io/").version("1.0.0")
				.contact(new Contact("", "", "gianluca.fattarsi@gmail.com")).build();
	}

	@Bean
	public Docket customImplementation() {
		return new Docket(DocumentationType.SWAGGER_2).select()
				.apis(RequestHandlerSelectors.basePackage("com.fattazzo.pizzashop"))

				// .paths(PathSelectors.any())
				// .paths(PathSelectors.any()).paths(Predicates.not(PathSelectors.regex("/controller/impl/*.*")))
				.build().apiInfo(apiInfo());
	}

}