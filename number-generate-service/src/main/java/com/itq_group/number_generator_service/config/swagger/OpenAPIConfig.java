package com.itq_group.number_generator_service.config.swagger;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.servers.Server;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;

@Configuration
public class OpenAPIConfig {

    public static final String LOCALHOST_NUMBER_GENERATE_SERVER = "Localhost number generate server";
    public static final String ITQ_GROUP_ITQ_GROUP_COM = "itq_group@itq_group.com";
    public static final String ITQ_GROUP = "ITQ GROUP";
    public static final String URL = "https://itq-group.com/";
    public static final String NUMBER_GENERATOR_SERVICE_API = "Number generator service API";
    public static final String VERSION = "1.0";
    public static final String THIS_API_DESCRIBE_OUR_NUMBER_GENERATOR_SERVICE = "This API describe our number generator service";

    @Value("${app.openapi.local-url}")
    private String localhostUrl;

    @Bean
    public OpenAPI myOpenApi() {
        Server localhostServer = new Server();
        localhostServer.setUrl(localhostUrl);
        localhostServer.setDescription(LOCALHOST_NUMBER_GENERATE_SERVER);

        Contact contact = new Contact();
        contact.setEmail(ITQ_GROUP_ITQ_GROUP_COM);
        contact.setName(ITQ_GROUP);
        contact.setUrl(URL);

        Info info = new Info();
        info.setTitle(NUMBER_GENERATOR_SERVICE_API);
        info.version(VERSION);
        info.contact(contact);
        info.description(THIS_API_DESCRIBE_OUR_NUMBER_GENERATOR_SERVICE);
        return new OpenAPI().info(info).servers(List.of(localhostServer));
    }
}
