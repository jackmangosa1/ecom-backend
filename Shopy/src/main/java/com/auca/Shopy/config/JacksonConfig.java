package com.auca.Shopy.config;

import com.auca.Shopy.model.Order;
import com.auca.Shopy.model.User;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.module.SimpleModule;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.converter.json.Jackson2ObjectMapperBuilder;

@Configuration
public class JacksonConfig {

    @Bean
    public ObjectMapper objectMapper(Jackson2ObjectMapperBuilder builder) {
        ObjectMapper objectMapper = builder.build();
        SimpleModule module = new SimpleModule();
        module.addSerializer(Order.class, new OrderSerializer());
        module.addSerializer(User.class, new UserSerializer());
        objectMapper.registerModule(module);
        return objectMapper;
    }


}