package br.com.volks.rota2030.configuration;

import org.springframework.context.annotation.Bean;

import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;

public class SwaggerConfiguration {

	@Bean
	public Docket rota2030Api() {
		return new Docket(DocumentationType.SWAGGER_2)
				.select()
				.apis(RequestHandlerSelectors.basePackage("br.com.volks.rota2030"))
				.paths(PathSelectors.ant("/**"))
				.build();
				//.ignoredParameterTypes(Usuario.class);
	}
}
