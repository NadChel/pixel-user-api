package com.example.pixel_user_api;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@OpenAPIDefinition(info = @Info(version = "1.0", title = "Pixel User API"),
		security = @SecurityRequirement(name = "bearer-key"))
public class PixelUserApiApplication {

	public static void main(String[] args) {
		SpringApplication.run(PixelUserApiApplication.class, args);
	}

}
