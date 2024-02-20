package org.example.shopbackend.order;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
public class OrderProducer {
    @Autowired
    private KafkaTemplate<String, String> kafkaTemplate;

    private String ORDER_TOPIC = "order_topic";

    public void produceOrder(Order order) {
        ObjectMapper mapper = new ObjectMapper();
        String orderJsonString = null;
        try {
            orderJsonString = mapper.writeValueAsString(order);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        System.out.println(String.format("##########\nProduced Order-> %s\n##########", orderJsonString));
        this.kafkaTemplate.send(ORDER_TOPIC, orderJsonString);
    }
}
