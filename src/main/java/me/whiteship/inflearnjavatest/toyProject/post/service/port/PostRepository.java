package me.whiteship.inflearnjavatest.toyProject.post.service.port;

import me.whiteship.inflearnjavatest.toyProject.post.domain.Post;

import java.util.Optional;

public interface PostRepository {

    Optional<Post> findById(long id);

    Post save(Post post);
}
