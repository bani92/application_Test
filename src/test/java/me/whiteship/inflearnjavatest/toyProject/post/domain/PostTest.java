package me.whiteship.inflearnjavatest.toyProject.post.domain;

import me.whiteship.inflearnjavatest.toyProject.user.domain.User;
import me.whiteship.inflearnjavatest.toyProject.user.domain.UserStatus;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.UUID;

import static org.assertj.core.api.Assertions.*;

public class PostTest {

    @Test
    public void PostCreate으로_게시물을_만들수있다() {
        // given
        PostCreate postCreate = PostCreate.builder()
                .writerId(1)
                .content("helloworld")
                .build();

        User writer = User.builder()
                .id(1L)
                .email("banseok@naver.com")
                .nickname("bani")
                .address("Seoul")
                .status(UserStatus.PENDING)
                .certificationCode(UUID.randomUUID().toString())
                .build();
        // when
        Post post = Post.from(writer, postCreate);

        // then
        assertThat(post.getContent()).isEqualTo("helloworld");
        assertThat(post.getWriter().getEmail()).isEqualTo("banseok@naver.com");
        assertThat(post.getWriter().getNickname()).isEqualTo("bani");
        assertThat(post.getWriter().getAddress()).isEqualTo("Seoul");
        assertThat(post.getWriter().getStatus()).isEqualTo(UserStatus.PENDING);

    }
}
