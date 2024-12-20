package me.whiteship.inflearnjavatest.toyProject.post.controller;


import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import me.whiteship.inflearnjavatest.toyProject.post.domain.PostCreate;
import me.whiteship.inflearnjavatest.toyProject.post.controller.response.PostResponse;
import me.whiteship.inflearnjavatest.toyProject.post.service.PostService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Tag(name = "게시물(posts)")
@RestController
@RequestMapping("/api/posts")
@RequiredArgsConstructor
public class PostCreateController {

    private final PostService postService;
    private final PostController postController;

    @PostMapping
    public ResponseEntity<PostResponse> createPost(@RequestBody PostCreate postCreate) {
        return ResponseEntity
            .status(HttpStatus.CREATED)
            .body(PostResponse.from(postService.create(postCreate)));
    }
}