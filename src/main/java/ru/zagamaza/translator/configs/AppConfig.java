package ru.zagamaza.translator.configs;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.ResourceBundleMessageSource;
import org.springframework.http.MediaType;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@Configuration
public class AppConfig {

    @Bean
    public MessageSource messageSource() {
        ResourceBundleMessageSource messageSource = new ResourceBundleMessageSource();
        messageSource.addBasenames("messages");
        messageSource.setDefaultEncoding("utf-8");
        return messageSource;
    }

    @Bean
    public RestTemplate restTemplate(RestTemplateBuilder builder, ObjectMapper objectMapper) {
        RestTemplate restTemplate = builder.build();

        MappingJackson2HttpMessageConverter mappingJackson2HttpMessageConverter = new MappingJackson2HttpMessageConverter(
                objectMapper);
        mappingJackson2HttpMessageConverter.setSupportedMediaTypes(List.of(MediaType.APPLICATION_FORM_URLENCODED));
        restTemplate.getMessageConverters().add(mappingJackson2HttpMessageConverter);
        return restTemplate;
    }

}
