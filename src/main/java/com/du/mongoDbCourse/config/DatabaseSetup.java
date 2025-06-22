package com.du.mongoDbCourse.config;

import com.du.mongoDbCourse.domain.User;
import com.du.mongoDbCourse.repository.UserRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;

import java.util.Arrays;

@Configuration
public class DatabaseSetup implements CommandLineRunner {

    private final UserRepository userRepository;

    public DatabaseSetup(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public void run(String... args) throws Exception {

        userRepository.deleteAll();

        User u1 = new User(null, "User1", "User1@test.com");
        User u2 = new User(null, "User2", "User2@test.com");
        User u3 = new User(null, "User3", "User3@test.com");

        userRepository.saveAll(Arrays.asList(u1, u2, u3));

    }

}
