package me.whiteship.inflearnjavatest.toyProject.post.infrastructure;

import lombok.RequiredArgsConstructor;
import me.whiteship.inflearnjavatest.toyProject.post.service.port.PostRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class PostRepositoryImpl implements PostRepository {

    private final PostJpaRepository postJpaRepository;

    @Override
    public Optional<me.whiteship.inflearnjavatest.toyProject.post.domain.Post> findById(long id) {
        return postJpaRepository.findById(id).map(Post::toModel);
    }

    @Override
    public me.whiteship.inflearnjavatest.toyProject.post.domain.Post save(me.whiteship.inflearnjavatest.toyProject.post.domain.Post post) {
        return postJpaRepository.save(Post.fromModel(post)).toModel();
    }
}
