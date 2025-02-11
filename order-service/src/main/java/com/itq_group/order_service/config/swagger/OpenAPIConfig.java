package com.itq_group.order_service.config.swagger;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.servers.Server;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;

@Configuration
@OpenAPIDefinition(
        info = @io.swagger.v3.oas.annotations.info.Info(title = "OpenAPI from Spring MVC", version = "1.0.0"))

public class OpenAPIConfig {


    @Value("${app.openapi.local-url}")
    private String localhostUrl;

    @Bean
    public OpenAPI myOpenApi() {
        Server localhostServer = new Server();
        localhostServer.setUrl(localhostUrl);
        localhostServer.setDescription("Localhost server");

        Contact contact = new Contact();
        contact.setEmail("itq_group@itq_group.com");
        contact.setName("ITQ GROUP");
        contact.setUrl("https://itq-group.com/");

        Info info = new Info();
        info.setTitle("Order service API");
        info.version("1.0");
        info.contact(contact);
        info.description("This API describe our order service");
        return new OpenAPI().info(info).servers(List.of(localhostServer));
    }
}
