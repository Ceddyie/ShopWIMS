package org.example.shopbackend.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.Map;

@Repository
public class UserRepository {
    @Autowired
    private NamedParameterJdbcTemplate jdbcTemplate;

    private User getUser(String username) {
        MapSqlParameterSource namedParameters = new MapSqlParameterSource();
        namedParameters.addValue("username", username);
        return jdbcTemplate.queryForObject("SELECT * FROM user WHERE username = :username", namedParameters, new BeanPropertyRowMapper<>(User.class));
    }

    public User registerUser(User newUser) {
        Map<String, Object> namedParameters = new HashMap<>();

        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

        namedParameters.put("username", newUser.getUsername());
        namedParameters.put("password", passwordEncoder.encode(newUser.getPassword()));
        namedParameters.put("email", newUser.getEmail());
        namedParameters.put("firstName", newUser.getFirstName());
        namedParameters.put("lastName", newUser.getLastName());

        try {
            jdbcTemplate.update("INSERT INTO user (username, password, email, first_name, last_name) VALUES (:username, :password, :email, :firstName, :lastName)", namedParameters);
            return getUser(newUser.getUsername());
        } catch (DataAccessException dae) {
            throw dae;
        }
    }
}
