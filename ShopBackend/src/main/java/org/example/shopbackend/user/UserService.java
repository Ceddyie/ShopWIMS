package org.example.shopbackend.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.CrossOrigin;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
@CrossOrigin
public class UserService {
    @Autowired
    private NamedParameterJdbcTemplate jdbcTemplate;

    @Autowired
    private UserRepository userRepository;

    public ResponseEntity<User> processRegistrationAttempt(User newUser) {
        if (checkForExistingUser(newUser.getEmail(), newUser.getUsername())) {
            System.out.println("Processing registration attempt");

            if (newUser.getPassword().length() < 8) {
                System.out.println("Password must be at least 8 characters!");
                return new ResponseEntity<User>(new User(), HttpStatus.BAD_REQUEST);
            } else {
                System.out.println("processing...");
                return new ResponseEntity<User>(this.userRepository.registerUser(newUser), HttpStatus.CREATED);
            }
        } else {
            System.out.println("Username/email already exists");
            return new ResponseEntity<User>(HttpStatus.CONFLICT);
        }
    }

    private boolean checkForExistingUser(String email, String username) {
        System.out.println("Checking for existing user");
        MapSqlParameterSource namedParameters = new MapSqlParameterSource();
        namedParameters.addValue("email", email);
        namedParameters.addValue("username", username);

        List<User> users = jdbcTemplate.query("SELECT * FROM user WHERE email = :email OR username = :username", namedParameters, new BeanPropertyRowMapper<>(User.class));

        if (users.isEmpty()) {
            System.out.println("Username and email available!");
            return true;
        }
        else {
            System.out.println("Username or email already taken!");
            return false;
        }
    }

    public ResponseEntity<User> processLoginAttempt(User loginUser) {
        MapSqlParameterSource namedParameters = new MapSqlParameterSource();
        namedParameters.addValue("username", loginUser.getUsername());

        List<User> users;
        try {
            users = jdbcTemplate.query("SELECT * FROM user WHERE username = :username", namedParameters, new BeanPropertyRowMapper<>(User.class));

            if(users.isEmpty()) {
                System.out.println("User with username " + loginUser.getUsername() + " not found!");
                return new ResponseEntity<User>((User) null, HttpStatus.NOT_FOUND);
            }
        } catch (EmptyResultDataAccessException e) {
            System.out.println("NO USER FOUND FOR " + loginUser.getUsername());
            return new ResponseEntity<User>((User) null, HttpStatus.NOT_FOUND);
        }

        if (users.get(0).checkPassword(loginUser.getPassword())) {
            System.out.println("CORRECT PASSWORD");
        } else {
            System.out.println("INCORRECT PASSWORD");
            return new ResponseEntity<User>((User) null, HttpStatus.UNAUTHORIZED);
        }
        System.out.println("Login processing...");

        User dbLoginUser = jdbcTemplate.queryForObject("SELECT id, username, email, first_name, last_name, password FROM user WHERE username = :username", namedParameters, new BeanPropertyRowMapper<>(User.class));
        return new ResponseEntity<User>(dbLoginUser, HttpStatus.OK);
    }
}
