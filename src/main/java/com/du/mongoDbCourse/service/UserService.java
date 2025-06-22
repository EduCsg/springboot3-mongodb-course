package com.du.mongoDbCourse.service;

import com.du.mongoDbCourse.domain.User;
import com.du.mongoDbCourse.dto.UserDto;
import com.du.mongoDbCourse.repository.UserRepository;
import com.du.mongoDbCourse.service.exception.ObjectNotFoundException;
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

    public UserDto findById(String id) {
        User user = userRepository.findById(id)
                            .orElseThrow(() -> new ObjectNotFoundException("User not found with id: " + id));
        return new UserDto(user);
    }

    public UserDto insert(UserDto userDto) {
        User user = fromDto(userDto);
        user = userRepository.insert(user);
        return new UserDto(user);
    }

    public void delete(String id) {
        User user = userRepository.findById(id)
                            .orElseThrow(() -> new ObjectNotFoundException("User not found with id: " + id));
        userRepository.delete(user);
    }

    public User fromDto(UserDto userDto) {
        return new User(userDto.getId(), userDto.getName(), userDto.getEmail());
    }

}
