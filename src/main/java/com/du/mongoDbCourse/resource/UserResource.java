package com.du.mongoDbCourse.resource;

import com.du.mongoDbCourse.domain.Post;
import com.du.mongoDbCourse.domain.User;
import com.du.mongoDbCourse.dto.UserDto;
import com.du.mongoDbCourse.service.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/users")
public class UserResource {

    private final UserService userService;

    public UserResource(UserService userService) {
        this.userService = userService;
    }

    @GetMapping
    public ResponseEntity<List<UserDto>> findAll() {
        return ResponseEntity.ok(userService.findAll());
    }

    @GetMapping("{id}")
    public ResponseEntity<UserDto> findById(@PathVariable String id) {
        return ResponseEntity.ok(userService.findById(id));
    }

    @PostMapping
    public ResponseEntity<UserDto> insert(@RequestBody UserDto userDto) {
        UserDto createdUser = userService.insert(userDto);
        URI uri = URI.create("/users/" + createdUser.getId());
        return ResponseEntity.created(uri).body(createdUser);
    }

    @DeleteMapping("{id}")
    public ResponseEntity<Void> delete(@PathVariable String id) {
        userService.delete(id);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("{id}")
    public ResponseEntity<UserDto> update(@PathVariable String id, @RequestBody UserDto userDto) {
        userDto.setId(id);
        return ResponseEntity.ok(userService.update(userDto));
    }

    @GetMapping("{id}/posts")
    public ResponseEntity<List<Post>> findPosts(@PathVariable String id) {
        return ResponseEntity.ok(userService.findPostsByUser(id));
    }

}
