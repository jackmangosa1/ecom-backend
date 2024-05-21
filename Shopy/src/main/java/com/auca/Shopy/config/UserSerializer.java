package com.auca.Shopy.config;

import com.auca.Shopy.model.User;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;

import java.io.IOException;

public class UserSerializer extends JsonSerializer<User> {

    @Override
    public void serialize(User user, JsonGenerator gen, SerializerProvider serializers) throws IOException {
        gen.writeStartObject();
        gen.writeNumberField("id", user.getId());
        gen.writeStringField("username", user.getUsername());
        gen.writeStringField("email", user.getEmail());
        gen.writeStringField("name", user.getName());
        gen.writeObjectField("role", user.getRole());
        gen.writeEndObject();
    }
}