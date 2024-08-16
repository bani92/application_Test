package me.whiteship.inflearnjavatest.toyProject.post.service.port;

import me.whiteship.inflearnjavatest.toyProject.post.infrastructure.PostEntity;

import java.util.Optional;

public interface PostRepository {

    Optional<PostEntity> findById(long id);

    PostEntity save(PostEntity postEntity);
}
