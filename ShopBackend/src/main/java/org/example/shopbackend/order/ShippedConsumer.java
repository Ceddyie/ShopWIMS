package org.example.shopbackend.order;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

import java.io.IOException;

@Service
public class ShippedConsumer {
    @Autowired
    private OrderController orderController;

    @KafkaListener(topics = "shipped_order")
    public void consumeOrderId(Long orderId) throws IOException {
        System.out.println(String.format("##########\nConsumed Order Id-> %s\n##########", orderId));
        orderController.updateOrder(orderId);
    }
}
