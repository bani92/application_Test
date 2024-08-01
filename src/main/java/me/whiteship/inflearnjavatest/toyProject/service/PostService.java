package me.whiteship.inflearnjavatest.toyProject.service;


import lombok.RequiredArgsConstructor;
import me.whiteship.inflearnjavatest.toyProject.exception.ResourceNotFoundException;
import me.whiteship.inflearnjavatest.toyProject.model.dto.PostCreateDto;
import me.whiteship.inflearnjavatest.toyProject.model.dto.PostUpdateDto;
import me.whiteship.inflearnjavatest.toyProject.repository.PostEntity;
import me.whiteship.inflearnjavatest.toyProject.repository.PostRepository;
import me.whiteship.inflearnjavatest.toyProject.repository.UserEntity;
import org.springframework.stereotype.Service;

import java.time.Clock;

@Service
@RequiredArgsConstructor
public class PostService {

    private final PostRepository postRepository;
    private final UserService userService;

    public PostEntity getPostById(long id) {
        return postRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Posts", id));
    }

    public PostEntity createPost(PostCreateDto postCreateDto) {
        UserEntity userEntity = userService.getByIdOrElseThrow(postCreateDto.getWriterId());
        PostEntity postEntity = new PostEntity();
        postEntity.setWriter(userEntity);
        postEntity.setContent(postCreateDto.getContent());
        postEntity.setCreatedAt(Clock.systemUTC().millis());
        return postRepository.save(postEntity);
    }

    public PostEntity updatePost(long id, PostUpdateDto postUpdateDto) {
        PostEntity postEntity = getPostById(id);
        postEntity.setContent(postUpdateDto.getContent());
        postEntity.setModifiedAt(Clock.systemUTC().millis());
        return postRepository.save(postEntity);
    }
}