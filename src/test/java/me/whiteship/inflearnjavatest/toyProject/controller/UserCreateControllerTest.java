package me.whiteship.inflearnjavatest.toyProject.controller;

import me.whiteship.inflearnjavatest.toyProject.model.dto.UserCreateDto;
import me.whiteship.inflearnjavatest.toyProject.model.dto.UserUpdateDto;
import org.junit.jupiter.api.Test;
import org.mockito.BDDMockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.jdbc.SqlGroup;
import org.springframework.test.web.servlet.MockMvc;
import org.testcontainers.shaded.com.fasterxml.jackson.databind.ObjectMapper;

import static org.mockito.ArgumentMatchers.any;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
@AutoConfigureTestDatabase
@SqlGroup({
        @Sql(scripts = "/sql/delete-all-data.sql", executionPhase = Sql.ExecutionPhase.AFTER_TEST_METHOD)
})
public class UserCreateControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private JavaMailSender mailSender;

    private final ObjectMapper objectMapper = new ObjectMapper();

    @Test
    void 사용자는_회원가입을_할수있고_회원가입된_사용자는_펜딩_상태이다() throws Exception{
        //given
        UserCreateDto userCreateDto = UserCreateDto.builder()
                .email("banseok@naver.com")
                .nickname("bani33")
                .address("Seoul2")
                .build();
        BDDMockito.doNothing().when(mailSender).send(any(SimpleMailMessage.class));
        //when
        //then
        mockMvc.perform(post("/api/users")
                        .header("EMAIL","banseok@naver.com")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(userCreateDto)))

                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.id").isNumber())
                .andExpect(jsonPath("$.email").value("banseok@naver.com"))
                .andExpect(jsonPath("$.nickname").value("bani33"))
                .andExpect(jsonPath("$.status").value("PENDING"));

    }

}