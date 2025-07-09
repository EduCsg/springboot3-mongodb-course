package com.du.mongoDbCourse.service;

import com.du.mongoDbCourse.domain.Post;
import com.du.mongoDbCourse.repository.PostRepository;
import com.du.mongoDbCourse.service.exception.ObjectNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class PostService {

    private final PostRepository postRepository;

    public PostService(PostRepository postRepository) {
        this.postRepository = postRepository;
    }

    public Post findById(String id) {
        return postRepository.findById(id)
                       .orElseThrow(() -> new ObjectNotFoundException("User not found with id: " + id));
    }

}
