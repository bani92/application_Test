package me.whiteship.inflearnjavatest.toyProject.controller;

import me.whiteship.inflearnjavatest.toyProject.model.dto.PostCreateDto;
import me.whiteship.inflearnjavatest.toyProject.model.dto.PostUpdateDto;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.jdbc.SqlGroup;
import org.springframework.test.web.servlet.MockMvc;
import org.testcontainers.shaded.com.fasterxml.jackson.databind.ObjectMapper;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
@AutoConfigureTestDatabase
@SqlGroup({
        @Sql(scripts = "/sql/post-controller-test-data.sql", executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD),
        @Sql(scripts = "/sql/delete-all-data.sql", executionPhase = Sql.ExecutionPhase.AFTER_TEST_METHOD)
})
public class PostControllerTest {

    @Autowired
    private MockMvc mockMvc;

    private final ObjectMapper objectMapper = new ObjectMapper();


    @Test
    void 사용자는_게시물을_단건_조회_할_수_있다() throws Exception {
        // given
        // when
        // then
        mockMvc.perform(get("/api/posts/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").isNumber())
                .andExpect(jsonPath("$.writer.id").isNumber())
                .andExpect(jsonPath("$.content").value("helloworld"))
                .andExpect(jsonPath("$.writer.nickname").value("bani"))
                .andExpect(jsonPath("$.writer.email").value("banseok@naver.com"));
    }

    @Test
    void 사용자가_존재하지_않는_게시물을_조회할경우_에러가_난다() throws Exception {
        // given
        // when
        // then
        mockMvc.perform(get("/api/posts/12345"))
                .andExpect(status().isNotFound())
                .andExpect(content().string("Posts에서 ID 12345를 찾을 수 없습니다."));

    }




    @Test
    void 사용자는_게시물을_수정할수있다() throws Exception{
        //given
        PostUpdateDto postUpdateDto = PostUpdateDto.builder()
                .content("foobar")
                .build();
        //when
        //then
        mockMvc.perform(put("/api/posts/1")
                        //  .header("EMAIL","banseok@naver.com")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(postUpdateDto)))

                .andExpect(status().isOk())
                .andExpect(jsonPath("$.writer.id").isNumber())
                .andExpect(jsonPath("$.content").value("foobar"))
                .andExpect(jsonPath("$.writer.nickname").value("bani"))
                .andExpect(jsonPath("$.writer.email").value("banseok@naver.com"));

    }
}