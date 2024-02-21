package org.example.shopbackend.products;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
public class ProductListener {
    @Autowired
    private NamedParameterJdbcTemplate jdbcTemplate;

    @KafkaListener(topics = "update_product_status")
    private void updateProductStatus(String message) {
        System.out.println(message);
        String[] split = message.split(":");

    }
}
