package me.whiteship.inflearnjavatest.toyProject.post.controller;


import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import me.whiteship.inflearnjavatest.toyProject.user.controller.UserController;
import me.whiteship.inflearnjavatest.toyProject.post.controller.response.PostResponse;
import me.whiteship.inflearnjavatest.toyProject.post.domain.PostUpdate;
import me.whiteship.inflearnjavatest.toyProject.post.infrastructure.PostEntity;
import me.whiteship.inflearnjavatest.toyProject.post.service.PostService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Tag(name = "게시물(posts)")
@RestController
@RequestMapping("/api/posts")
@RequiredArgsConstructor
public class PostController {

    private final PostService postService;
    private final UserController userController;

    @GetMapping("/{id}")
    public ResponseEntity<PostResponse> getPostById(@PathVariable(value = "id") long id) {
        return ResponseEntity
            .ok()
            .body(toResponse(postService.getById(id)));
    }

    @PutMapping("/{id}")
    public ResponseEntity<PostResponse> updatePost(@PathVariable(value = "id") long id, @RequestBody PostUpdate postUpdate) {
        return ResponseEntity
            .ok()
            .body(toResponse(postService.update(id, postUpdate)));
    }

    public PostResponse toResponse(PostEntity postEntity) {
        PostResponse PostResponse = new PostResponse();
        PostResponse.setId(postEntity.getId());
        PostResponse.setContent(postEntity.getContent());
        PostResponse.setCreatedAt(postEntity.getCreatedAt());
        PostResponse.setModifiedAt(postEntity.getModifiedAt());
        PostResponse.setWriter(userController.toResponse(postEntity.getWriter()));
        return PostResponse;
    }
}