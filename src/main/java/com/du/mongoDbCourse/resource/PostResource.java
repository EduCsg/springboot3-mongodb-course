package com.du.mongoDbCourse.resource;

import com.du.mongoDbCourse.domain.Post;
import com.du.mongoDbCourse.resource.util.URL;
import com.du.mongoDbCourse.service.PostService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/posts")
public class PostResource {

    private final PostService postService;

    public PostResource(PostService postService) {
        this.postService = postService;
    }

    @GetMapping("{id}")
    public ResponseEntity<Post> findById(@PathVariable String id) {
        return ResponseEntity.ok(postService.findById(id));
    }

    @GetMapping("title")
    public ResponseEntity<List<Post>> findByTitle(@RequestParam(defaultValue = "") String title) {
        return ResponseEntity.ok(postService.findByTitle(title));
    }

    @GetMapping("fullsearch")
    public ResponseEntity<List<Post>> fullSearch(
            @RequestParam(defaultValue = "") String text,
            @RequestParam(required = false) String startDate,
            @RequestParam(required = false) String endDate) {
        return ResponseEntity.ok(postService.fullSearch(text, startDate, endDate));
    }

}
