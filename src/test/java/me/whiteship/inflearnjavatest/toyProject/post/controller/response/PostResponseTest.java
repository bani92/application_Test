package me.whiteship.inflearnjavatest.toyProject.post.controller.response;

import me.whiteship.inflearnjavatest.toyProject.post.domain.Post;
import me.whiteship.inflearnjavatest.toyProject.user.domain.User;
import me.whiteship.inflearnjavatest.toyProject.user.domain.UserStatus;
import org.junit.jupiter.api.Test;

import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

class PostResponseTest {

    @Test
    public void Post로_응답을_생성할_수_있다() {
        // given
        Post post = Post.builder()
                .content("helloworld")
                .writer(User.builder()
                .email("banseok@naver.com")
                .nickname("bani")
                .address("Seoul")
                .status(UserStatus.PENDING)
                .certificationCode(UUID.randomUUID().toString())
                .build()).build();

        // when
        PostResponse postResponse = PostResponse.from(post);

        // then
        assertThat(postResponse.getContent()).isEqualTo("helloworld");
        assertThat(postResponse.getWriter().getEmail()).isEqualTo("banseok@naver.com");
        assertThat(postResponse.getWriter().getNickname()).isEqualTo("bani");
        assertThat(postResponse.getWriter().getStatus()).isEqualTo(UserStatus.PENDING);
    }
}