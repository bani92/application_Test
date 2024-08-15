package me.whiteship.inflearnjavatest.toyProject.post.service;


import lombok.RequiredArgsConstructor;
import me.whiteship.inflearnjavatest.toyProject.user.exception.ResourceNotFoundException;
import me.whiteship.inflearnjavatest.toyProject.post.domain.PostCreate;
import me.whiteship.inflearnjavatest.toyProject.post.domain.PostUpdate;
import me.whiteship.inflearnjavatest.toyProject.post.infrastructure.PostEntity;
import me.whiteship.inflearnjavatest.toyProject.post.infrastructure.PostRepository;
import me.whiteship.inflearnjavatest.toyProject.user.infrastructure.UserEntity;
import me.whiteship.inflearnjavatest.toyProject.user.service.UserService;
import org.springframework.stereotype.Service;

import java.time.Clock;

@Service
@RequiredArgsConstructor
public class PostService {

    private final PostRepository postRepository;
    private final UserService userService;

    public PostEntity getById(long id) {
        return postRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Posts", id));
    }

    public PostEntity create(PostCreate postCreate) {
        UserEntity userEntity = userService.getById(postCreate.getWriterId());
        PostEntity postEntity = new PostEntity();
        postEntity.setWriter(userEntity);
        postEntity.setContent(postCreate.getContent());
        postEntity.setCreatedAt(Clock.systemUTC().millis());
        return postRepository.save(postEntity);
    }

    public PostEntity update(long id, PostUpdate postUpdate) {
        PostEntity postEntity = getById(id);
        postEntity.setContent(postUpdate.getContent());
        postEntity.setModifiedAt(Clock.systemUTC().millis());
        return postRepository.save(postEntity);
    }
}