package org.example.shopbackend.order;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
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

    public void updateOrder(long orderId) {
        try {
            MapSqlParameterSource namedParameters = new MapSqlParameterSource();
            namedParameters.addValue("orderId", orderId);

            jdbcTemplate.update("UPDATE orders SET shipped = true WHERE id = :orderId", namedParameters);
            Boolean newShipped = jdbcTemplate.queryForObject("SELECT shipped FROM orders WHERE id = :orderId", namedParameters, boolean.class);
            System.out.println("newShipped: " + newShipped);
        } catch (DataAccessException e) {
            throw new RuntimeException("Fail");
        }
    }
}
