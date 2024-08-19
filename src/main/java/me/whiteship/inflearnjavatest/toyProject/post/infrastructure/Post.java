package me.whiteship.inflearnjavatest.toyProject.post.infrastructure;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import me.whiteship.inflearnjavatest.toyProject.user.infrastructure.User;

@Getter
@Setter
@Entity
@Table(name = "posts")
public class Post {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "content")
    private String content;

    @Column(name = "created_at")
    private Long createdAt;

    @Column(name = "modified_at")
    private Long modifiedAt;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User writer;

    public static Post fromModel(me.whiteship.inflearnjavatest.toyProject.post.domain.Post post) {
        Post postEntity = new Post();
        postEntity.id = post.getId();
        postEntity.content = post.getContent();
        postEntity.createdAt = post.getCreatedAt();
        postEntity.modifiedAt = post.getModifiedAt();
        postEntity.writer = User.fromModel(post.getWriter());
        return postEntity;
    }

    public me.whiteship.inflearnjavatest.toyProject.post.domain.Post toModel() {
        return me.whiteship.inflearnjavatest.toyProject.post.domain.Post.builder()
                .id(id)
                .content(content)
                .modifiedAt(modifiedAt)
                .writer(writer.toModel())
                .build();
    }
}