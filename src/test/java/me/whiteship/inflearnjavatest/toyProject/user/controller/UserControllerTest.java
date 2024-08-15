package me.whiteship.inflearnjavatest.toyProject.user.controller;

import me.whiteship.inflearnjavatest.toyProject.user.domain.UserStatus;
import me.whiteship.inflearnjavatest.toyProject.user.domain.UserUpdate;
import me.whiteship.inflearnjavatest.toyProject.user.infrastructure.UserEntity;
import me.whiteship.inflearnjavatest.toyProject.user.infrastructure.UserRepository;
import org.assertj.core.api.Assertions;
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

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
@AutoConfigureTestDatabase
@SqlGroup({
        @Sql(scripts = "/sql/user-controller-test-data.sql", executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD),
        @Sql(scripts = "/sql/delete-all-data.sql", executionPhase = Sql.ExecutionPhase.AFTER_TEST_METHOD)
})
public class UserControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private UserRepository userRepository;

    private final ObjectMapper objectMapper = new ObjectMapper();

    @Test
    void 사용자는_특정_유저의_정보를_개인정보는_소거된채_전달받을수있다() throws Exception{
        //given
        //when
        //then
        mockMvc.perform(get("/api/users/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(1))
                .andExpect(jsonPath("$.email").value("banseok@naver.com"))
                .andExpect(jsonPath("$.nickname").value("bani"))
                .andExpect(jsonPath("$.address").doesNotExist())                ;
    }

    @Test
    void 사용자는_존재하지_않는_유저의_아이디로_api_호출할_경우_404_응답을받는다() throws Exception{
        //given
        //when
        //then
        mockMvc.perform(get("/api/users/112347"))
                .andExpect(status().isNotFound())
                .andExpect(content().string("Users에서 ID 112347를 찾을 수 없습니다."));
    }

    @Test
    void 사용자는_인증코드로_계정을_활성화_할수있다() throws Exception{
        //given
        //when
        //then
        mockMvc.perform(get("/api/users/2/verify").queryParam("certificationCode","aaaaaaa-aa"))
                .andExpect(status().isFound());
        UserEntity userEntity = userRepository.findById(2L).get();
        Assertions.assertThat(userEntity.getId()).isEqualTo(2L);
        Assertions.assertThat(userEntity.getAddress()).isEqualTo("Seoul");
        Assertions.assertThat(userEntity.getStatus()).isNotNull();
        Assertions.assertThat(userEntity.getStatus()).isEqualTo(UserStatus.ACTIVE);
    }


    @Test
    void 사용자는_인증코드가_일치하지_않을_경우_권한없음_에러를_내려준다() throws Exception{
        //given
        //when
        //then
        mockMvc.perform(get("/api/users/2/verify").queryParam("certificationCode","aaaaaaa-aa2"))
                .andExpect(status().isForbidden());

    }

    @Test
    void 사용자는_내_정보를_불러올때_개인정보인_주소도_갖고올수있다() throws Exception{
        //given
        //when
        //then
        mockMvc.perform(get("/api/users/me").header("EMAIL","banseok@naver.com"))
                        // .queryParam("certificationCode","aaaaaaa-aa"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(1))
                .andExpect(jsonPath("$.email").value("banseok@naver.com"))
                .andExpect(jsonPath("$.nickname").value("bani"));

    }

    @Test
    void 사용자는_내_정보를_수정할_수_있다() throws Exception{
        //given
        UserUpdate userUpdate = UserUpdate.builder()
                .nickname("bani33")
                .address("Seoul2")
                .build();
        //when
        //then
        mockMvc.perform(put("/api/users/me")
                .header("EMAIL","banseok@naver.com")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(userUpdate)))

                // .queryParam("certificationCode","aaaaaaa-aa"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(1))
                .andExpect(jsonPath("$.email").value("banseok@naver.com"))
                .andExpect(jsonPath("$.nickname").value("bani33"))
                .andExpect(jsonPath("$.address").value("Seoul2"));

    }
}