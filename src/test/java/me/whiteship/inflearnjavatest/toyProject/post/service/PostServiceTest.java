package me.whiteship.inflearnjavatest.toyProject.post.service;

import me.whiteship.inflearnjavatest.toyProject.post.domain.PostCreate;
import me.whiteship.inflearnjavatest.toyProject.post.domain.PostUpdate;
import me.whiteship.inflearnjavatest.toyProject.post.infrastructure.PostEntity;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.jdbc.SqlGroup;

import static org.assertj.core.api.Assertions.assertThat;
@SpringBootTest
@TestPropertySource("classpath:test-application.properties")
@SqlGroup({
        @Sql(scripts = "/sql/post-service-test-data.sql", executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD),
        @Sql(scripts = "/sql/delete-all-data.sql", executionPhase = Sql.ExecutionPhase.AFTER_TEST_METHOD)
})
class PostServiceTest {

    @Autowired
    private PostService postService;

    @Test
    void getById는_존재하는_게시물을_내려준다() {
        // given
        // when

        PostEntity result = postService.getById(1);

        // then
        assertThat(result.getContent()).isEqualTo("helloworld");
        assertThat(result.getWriter().getEmail()).isEqualTo("banseok@naver.com");
    }

    @Test
    void postCreateDto를_이용하여_게시물을_생성할수있다() {
        // given
        PostCreate postCreate = PostCreate.builder()
                .writerId(1)
                .content("foobar")
                .build();

        // when
        PostEntity postEntity = postService.create(postCreate);

        // then
        assertThat(postEntity.getId()).isNotNull();
        assertThat(postEntity.getContent()).isEqualTo("foobar");
        assertThat(postEntity.getCreatedAt()).isGreaterThan(0);
    }


    @Test
    void postUpdateDto를_이용하여_게시물을_수정할수있다() {
        // given
        PostUpdate postUpdate = PostUpdate.builder()
                .content("foobar22")
                .build();

        // when
        postService.update(1, postUpdate);

        // then
        PostEntity postEntity = postService.getById(1);
        assertThat(postEntity.getId()).isEqualTo(1);
        assertThat(postEntity.getContent()).isEqualTo("foobar22");
        assertThat(postEntity.getModifiedAt()).isGreaterThan(0);
    }

}