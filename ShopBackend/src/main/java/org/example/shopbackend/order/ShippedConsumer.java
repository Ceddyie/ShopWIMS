package org.example.shopbackend.order;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.example.shopbackend.messages.ShippedMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

import java.io.IOException;

@Service
public class ShippedConsumer {
    @Autowired
    private OrderService orderService;

    @Autowired
    private ObjectMapper objectMapper;

    @KafkaListener(topics = "shipped_order")
    public void consumeOrderId(String message) throws IOException {
        ShippedMessage shippedMessage = objectMapper.readValue(message, ShippedMessage.class);

        long measureTime = System.currentTimeMillis() - shippedMessage.getTimestamp();
        System.out.println("Measure Time: " + measureTime);

        System.out.println(String.format("##########\nConsumed Order Id-> %s\n##########", shippedMessage));
        orderService.updateOrder(shippedMessage.getOrderId());
    }
}
