package com.du.mongoDbCourse.service;

import com.du.mongoDbCourse.domain.User;
import com.du.mongoDbCourse.dto.UserDto;
import com.du.mongoDbCourse.repository.UserRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {

    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public List<UserDto> findAll() {
        List<User> users = userRepository.findAll();
        return users.stream().map(UserDto::new).toList();
    }

}
