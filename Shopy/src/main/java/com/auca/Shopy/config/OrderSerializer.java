package com.auca.Shopy.config;

import com.auca.Shopy.model.Order;
import com.auca.Shopy.model.User;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.ser.std.StdSerializer;

import java.io.IOException;

public class OrderSerializer extends StdSerializer<Order> {

    public OrderSerializer() {
        this(null);
    }

    public OrderSerializer(Class<Order> t) {
        super(t);
    }

    @Override
    public void serialize(Order order, JsonGenerator gen, SerializerProvider provider) throws IOException {
        gen.writeStartObject();
        gen.writeNumberField("id", order.getId());
        gen.writeObjectField("date", order.getDate());
        gen.writeNumberField("amount", order.getAmount());
        gen.writeStringField("address", order.getAddress());
        gen.writeStringField("payment", order.getPayment());
        gen.writeObjectField("orderStatus", order.getOrderStatus());
        gen.writeObjectField("trackingId", order.getTrackingId());

        // Serialize the user field without recursion
        gen.writeFieldName("user");
        gen.writeStartObject();
        User user = order.getUser();
        gen.writeNumberField("id", user.getId());
        gen.writeStringField("username", user.getUsername());
        gen.writeStringField("email", user.getEmail());
        gen.writeStringField("name", user.getName());
        gen.writeObjectField("role", user.getRole());
        gen.writeEndObject();

        gen.writeEndObject();
    }
}