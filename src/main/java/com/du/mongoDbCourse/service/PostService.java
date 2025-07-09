package com.du.mongoDbCourse.service;

import com.du.mongoDbCourse.domain.Post;
import com.du.mongoDbCourse.repository.PostRepository;
import com.du.mongoDbCourse.resource.util.URL;
import com.du.mongoDbCourse.service.exception.ObjectNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

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

    public List<Post> findByTitle(String title) {
        String decodedTitle = URL.decodeParam(title);

        List<Post> posts = postRepository.searchByTitle(decodedTitle);

        if (posts.isEmpty())
            throw new ObjectNotFoundException("No posts found with title containing: " + title);

        return posts;
    }

    public List<Post> fullSearch(String text, String startDateStr, String endDateStr) {

        Date minDate = URL.convertDate(startDateStr, new Date(0));
        Date maxDate = URL.convertDate(endDateStr, new Date());

        // Adjust maxDate to include the entire day
        maxDate = new Date(maxDate.getTime() + 24 * 60 * 60 * 1000 - 1);

        List<Post> posts = postRepository.searchByTextBetweenDates(URL.decodeParam(text), minDate, maxDate);

        if (posts.isEmpty())
            throw new ObjectNotFoundException("No posts found with text containing: " + text);

        return posts;

    }

}
