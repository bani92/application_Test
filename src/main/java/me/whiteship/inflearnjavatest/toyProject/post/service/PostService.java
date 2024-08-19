package me.whiteship.inflearnjavatest.toyProject.post.service;


import lombok.RequiredArgsConstructor;
import me.whiteship.inflearnjavatest.toyProject.post.domain.Post;
import me.whiteship.inflearnjavatest.toyProject.post.service.port.PostRepository;
import me.whiteship.inflearnjavatest.toyProject.user.domain.User;
import me.whiteship.inflearnjavatest.toyProject.user.exception.ResourceNotFoundException;
import me.whiteship.inflearnjavatest.toyProject.post.domain.PostCreate;
import me.whiteship.inflearnjavatest.toyProject.post.domain.PostUpdate;
import me.whiteship.inflearnjavatest.toyProject.user.service.UserService;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class PostService {

    private final PostRepository postRepository;
    private final UserService userService;

    public Post getById(long id) {
        return postRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Posts", id));
    }

    public Post create(PostCreate postCreate) {
        User writer = userService.getById(postCreate.getWriterId());
        System.out.println("Writer: " + writer);  // 디버깅 메시지 추가
        Post post = Post.from(writer, postCreate);
        System.out.println("Post: " + post);  // 디버깅 메시지 추가
        return postRepository.save(post);
    }

    public Post update(long id, PostUpdate postUpdate) {

        Post post = getById(id);
        post = post.update(postUpdate);
        return postRepository.save(post);
    }
}