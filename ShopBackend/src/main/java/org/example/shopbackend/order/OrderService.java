package org.example.shopbackend.order;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrderService {
    @Autowired
    private NamedParameterJdbcTemplate jdbcTemplate;

    public List<Order> getOrdersByCustomer(long customerId) {
        MapSqlParameterSource namedParameters = new MapSqlParameterSource();
        namedParameters.addValue("customerId", customerId);

        return jdbcTemplate.query("SELECT * FROM orders WHERE customer_id = :customerId ORDER BY id desc", namedParameters, new BeanPropertyRowMapper<>(Order.class));
    }
}
