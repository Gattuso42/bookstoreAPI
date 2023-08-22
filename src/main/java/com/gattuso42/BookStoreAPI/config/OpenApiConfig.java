package com.gattuso42.BookStoreAPI.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class OpenApiConfig {

    @Bean
    OpenAPI openAPI(){
        return new OpenAPI()
                .info(new Info()
                        .title("Bookstore Api")
                        .description("This Api allows to user managing a bookstore by enabling them to create,read,update and delete books. It includes features such author,genre,price and other relevant details")
                        .contact(new Contact().url("https://github.com/Gattuso42"))
                        .version("1.0.0")
                );
    }
}
