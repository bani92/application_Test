package me.whiteship.inflearnjavatest.toyProject.service;

import me.whiteship.inflearnjavatest.toyProject.exception.CertificationCodeNotMatchedException;
import me.whiteship.inflearnjavatest.toyProject.exception.ResourceNotFoundException;
import me.whiteship.inflearnjavatest.toyProject.model.UserStatus;
import me.whiteship.inflearnjavatest.toyProject.model.dto.UserCreateDto;
import me.whiteship.inflearnjavatest.toyProject.model.dto.UserUpdateDto;
import me.whiteship.inflearnjavatest.toyProject.repository.UserEntity;
import org.apache.catalina.User;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.BDDMockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.jdbc.SqlGroup;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;

@SpringBootTest
@TestPropertySource("classpath:test-application.properties")
@SqlGroup({
    @Sql(scripts = "/sql/user-service-test-data.sql", executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD),
        @Sql(scripts = "/sql/delete-all-data.sql", executionPhase = Sql.ExecutionPhase.AFTER_TEST_METHOD)
})
class UserServiceTest {


    @Autowired
    private UserService userService;

    // 스프링에 있는 JavaMailSender 객체를 Mock으로 선언된 객체로 덮어쓰기
    @MockBean
    private JavaMailSender mailSender;

    @Test
    void getByEmail은_ACTIVE_상태인_유저를_찾아올수_있다() {
        // given
        String email = "banseok@naver.com";

        // when
        UserEntity result = userService.getByEmail(email);

        // then
        assertThat(result.getNickname()).isEqualTo("bani");
    }

    @Test
    void getByEmail은_PENDING_상태인_유저는_찾아올수_없다() {
        // given
        String email = "banseok2@naver.com";

        // when
        // then
        assertThatThrownBy(() -> {
            UserEntity result = userService.getByEmail(email);
        }).isInstanceOf(ResourceNotFoundException.class);

    }

    @Test
    void getById은_ACTIVE_상태인_유저를_찾아올수_있다() {
        // given
        // when
        UserEntity result = userService.getById(1);

        // then
        assertThat(result.getNickname()).isEqualTo("bani");
    }

    @Test
    void getById은_PENDING_상태인_유저는_찾아올수_없다() {
        // given
        // when
        // then
        assertThatThrownBy(() -> {
            UserEntity result = userService.getById(2);
        }).isInstanceOf(ResourceNotFoundException.class);

    }

    @Test
    void userCreateDto를_이용하여_유저를_생성할수있다() {
        // given
        UserCreateDto userCreateDto = UserCreateDto.builder()
                .email("banseok@naver.com")
                .address("Seoul")
                .nickname("bani")
                .build();
        // 이메일을 발송하는 JavaMailSender를 더미로 대체
        BDDMockito.doNothing().when(mailSender).send(any(SimpleMailMessage.class));

        // when
        UserEntity result = userService.create(userCreateDto);

        // then
        assertThat(result.getId()).isNotNull();
        assertThat(result.getStatus()).isEqualTo(UserStatus.PENDING);
    }


    @Test
    void userUpdateDto를_이용하여_유저를_수정할수있다() {
        // given
        UserUpdateDto userUpdateDto = UserUpdateDto.builder()
                .address("Incheon")
                .nickname("bani3")
                .build();

        // when
        userService.update(1, userUpdateDto);

        // then
        UserEntity userEntity = userService.getById(1);
        assertThat(userEntity.getId()).isNotNull();
        assertThat(userEntity.getAddress()).isEqualTo("Incheon");
        assertThat(userEntity.getNickname()).isEqualTo("bani3");
    }

    @Test
    void user를_로그인_시키면_마지막_로그인_시간이_변경된다() {
        // given
        // when
        userService.login(1);

        // then
        UserEntity userEntity = userService.getById(1);
        assertThat(userEntity.getLastLoginAt()).isGreaterThan(0L);
    }

    @Test
    void PENDING_상태의_사용자는_인증코드로_ACTIVE_시킬수있다() {
        // given
        // when
        userService.verifyEmail(2,"aaaaaaa-aa");

        // then
        UserEntity userEntity = userService.getById(2);
        assertThat(userEntity.getStatus()).isEqualTo(UserStatus.ACTIVE);
    }

    @Test
    void PENDING_상태의_사용자는_잘못된_인증코드를_받으면_에러를_던진다() {
        // given
        // when
        // then
        assertThatThrownBy(() -> {
            userService.verifyEmail(2,"aaaaaaa-aaa");
        }).isInstanceOf(CertificationCodeNotMatchedException.class);
    }



}