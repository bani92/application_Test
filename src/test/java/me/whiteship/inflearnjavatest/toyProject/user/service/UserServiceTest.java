package me.whiteship.inflearnjavatest.toyProject.user.service;

import me.whiteship.inflearnjavatest.toyProject.mock.FakeMailSender;
import me.whiteship.inflearnjavatest.toyProject.mock.FakeUserRepository;
import me.whiteship.inflearnjavatest.toyProject.mock.TestClockHolder;
import me.whiteship.inflearnjavatest.toyProject.mock.TestUuidHolder;
import me.whiteship.inflearnjavatest.toyProject.user.domain.User;
import me.whiteship.inflearnjavatest.toyProject.user.domain.UserCreate;
import me.whiteship.inflearnjavatest.toyProject.user.domain.UserStatus;
import me.whiteship.inflearnjavatest.toyProject.user.domain.UserUpdate;
import me.whiteship.inflearnjavatest.toyProject.user.exception.CertificationCodeNotMatchedException;
import me.whiteship.inflearnjavatest.toyProject.user.exception.ResourceNotFoundException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.BDDMockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.jdbc.SqlGroup;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.ArgumentMatchers.any;

class UserServiceTest {

    private UserService userService;

    @BeforeEach
    void init() {
        FakeMailSender fakeMailSender = new FakeMailSender();
        FakeUserRepository fakeUserRepository = new FakeUserRepository();
        this.userService = UserService.builder()
                .uuidHolder(new TestUuidHolder("aaaaaaa-aa"))
                .clockHolder(new TestClockHolder(1533L))
                .userRepository(fakeUserRepository)
                .certificationService(new CertificationService(fakeMailSender))
                .build();
        fakeUserRepository.save(User.builder()
                .id(1L)
                .email("banseok@naver.com")
                .nickname("bani")
                .address("'Seoul'")
                .certificationCode("aaaaaaa-aa")
                .status(UserStatus.ACTIVE)
                .lastLoginAt(0L)
                .build());
        fakeUserRepository.save(User.builder()
                .id(2L)
                .email("banseok2@naver.com")
                .nickname("bani2")
                .address("'Seoul'")
                .certificationCode("aaaaaaa-aa")
                .status(UserStatus.PENDING)
                .lastLoginAt(0L)
                .build());
    }
    @Test
    void getByEmail은_ACTIVE_상태인_유저를_찾아올수_있다() {
        // given
        String email = "banseok@naver.com";

        // when
        User result = userService.getByEmail(email);

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
            User result = userService.getByEmail(email);
        }).isInstanceOf(ResourceNotFoundException.class);

    }

    @Test
    void getById은_ACTIVE_상태인_유저를_찾아올수_있다() {
        // given
        // when
        User result = userService.getById(1);

        // then
        assertThat(result.getNickname()).isEqualTo("bani");
    }

    @Test
    void getById은_PENDING_상태인_유저는_찾아올수_없다() {
        // given
        // when
        // then
        assertThatThrownBy(() -> {
            User result = userService.getById(2);
        }).isInstanceOf(ResourceNotFoundException.class);

    }

    @Test
    void userCreateDto를_이용하여_유저를_생성할수있다() {
        // given
        UserCreate userCreate = UserCreate.builder()
                .email("banseok@naver.com")
                .address("Seoul")
                .nickname("bani")
                .build();

        // when
        User result = userService.create(userCreate);

        // then
        assertThat(result.getId()).isNotNull();
        assertThat(result.getStatus()).isEqualTo(UserStatus.PENDING);
        assertThat(result.getCertificationCode()).isEqualTo("aaaaaaa-aa");
    }


    @Test
    void userUpdateDto를_이용하여_유저를_수정할수있다() {
        // given
        UserUpdate userUpdate = UserUpdate.builder()
                .address("Incheon")
                .nickname("bani3")
                .build();

        // when
        userService.update(1, userUpdate);

        // then
        User user = userService.getById(1);
        assertThat(user.getId()).isNotNull();
        assertThat(user.getAddress()).isEqualTo("Incheon");
        assertThat(user.getNickname()).isEqualTo("bani3");
    }

    @Test
    void user를_로그인_시키면_마지막_로그인_시간이_변경된다() {
        // given
        // when
        userService.login(1);

        // then
        User user = userService.getById(1);
        assertThat(user.getLastLoginAt()).isEqualTo(1533L);
    }

    @Test
    void PENDING_상태의_사용자는_인증코드로_ACTIVE_시킬수있다() {
        // given
        // when
        userService.verifyEmail(2,"aaaaaaa-aa");

        // then
        User user = userService.getById(2);
        assertThat(user.getStatus()).isEqualTo(UserStatus.ACTIVE);
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