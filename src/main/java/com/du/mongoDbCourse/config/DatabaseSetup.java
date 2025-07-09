package com.du.mongoDbCourse.config;

import com.du.mongoDbCourse.domain.Post;
import com.du.mongoDbCourse.domain.User;
import com.du.mongoDbCourse.dto.AuthorDto;
import com.du.mongoDbCourse.dto.CommentDto;
import com.du.mongoDbCourse.repository.PostRepository;
import com.du.mongoDbCourse.repository.UserRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;

import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.List;
import java.util.TimeZone;

@Configuration
public class DatabaseSetup implements CommandLineRunner {

    private final UserRepository userRepository;
    private final PostRepository postRepository;

    public DatabaseSetup(UserRepository userRepository, PostRepository postRepository) {
        this.userRepository = userRepository;
        this.postRepository = postRepository;
    }

    @Override
    public void run(String... args) throws Exception {

        SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
        dateFormat.setTimeZone(TimeZone.getTimeZone("GMT"));

        userRepository.deleteAll();
        postRepository.deleteAll();

        User u1 = new User(null, "User1", "User1@test.com");
        User u2 = new User(null, "User2", "User2@test.com");
        User u3 = new User(null, "User3", "User3@test.com");

        userRepository.saveAll(Arrays.asList(u1, u2, u3));

        Post p1 = new Post(null, "Post1", "Content of Post1", dateFormat.parse("01/01/2025"), new AuthorDto(u1));
        Post p2 = new Post(null, "Post2", "Content of Post2", dateFormat.parse("15/01/2025"), new AuthorDto(u1));

        CommentDto c1 = new CommentDto("Comment1 on Post1", dateFormat.parse("02/01/2025"), new AuthorDto(u2));
        CommentDto c2 = new CommentDto("Comment2 on Post1", dateFormat.parse("03/01/2025"), new AuthorDto(u3));
        CommentDto c3 = new CommentDto("Comment1 on Post2", dateFormat.parse("16/01/2025"), new AuthorDto(u3));

        p1.getComments().addAll(Arrays.asList(c1, c2));
        p2.getComments().add(c3);
        postRepository.saveAll(Arrays.asList(p1, p2));

        u1.getPosts().addAll(Arrays.asList(p1, p2));
        userRepository.save(u1);

    }

}
