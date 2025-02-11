package com.itq_group.number_generator_service.config.swagger;

//import io.swagger.v3.oas.models.OpenAPI;
//import io.swagger.v3.oas.models.info.Contact;
//import io.swagger.v3.oas.models.info.Info;
//import io.swagger.v3.oas.models.servers.Server;
//import org.springframework.beans.factory.annotation.Value;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;

import java.util.List;

//@Configuration
public class OpenAPIConfig {

//
//    @Value("${app.openapi.local-url}")
//    private String localhostUrl;
//
//    @Bean
//    public OpenAPI myOpenApi() {
//        Server localhostServer = new Server();
//        localhostServer.setUrl(localhostUrl);
//        localhostServer.setDescription("Localhost number generate server");
//
//        Contact contact = new Contact();
//        contact.setEmail("itq_group@itq_group.com");
//        contact.setName("ITQ GROUP");
//        contact.setUrl("https://itq-group.com/");
//
//        Info info = new Info();
//        info.setTitle("Number generator service API");
//        info.version("1.0");
//        info.contact(contact);
//        info.description("This API describe our number generator service");
//        return new OpenAPI().info(info).servers(List.of(localhostServer));
//    }
}
