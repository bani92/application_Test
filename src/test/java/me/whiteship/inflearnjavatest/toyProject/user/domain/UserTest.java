package me.whiteship.inflearnjavatest.toyProject.user.domain;

import me.whiteship.inflearnjavatest.toyProject.mock.TestClockHolder;
import me.whiteship.inflearnjavatest.toyProject.mock.TestUuidHolder;
import me.whiteship.inflearnjavatest.toyProject.user.exception.CertificationCodeNotMatchedException;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

public class UserTest {

    @Test
    public void UserCreate_객체로_생성할_수_있다() {
        // given
        UserCreate userCreate = UserCreate.builder()
                .email("banseok@naver.com")
                .nickname("bani33")
                .address("Seoul2")
                .build();
        // when
        User user = User.from(userCreate, new TestUuidHolder("a1234"));

        // then
        assertThat(user.getId()).isNull();
        assertThat(user.getEmail()).isEqualTo("banseok@naver.com");
        assertThat(user.getNickname()).isEqualTo("bani33");
        assertThat(user.getAddress()).isEqualTo("Seoul2");
        assertThat(user.getStatus()).isEqualTo(UserStatus.PENDING);
        assertThat(user.getCertificationCode()).isEqualTo("a1234");

    }

    @Test
    public void UserUpdate_객체로_데이터를_업데이트_할_수_있다() {
        // given
        User user = User.builder()
                .id(1L)
                .email("banseok@naver.com")
                .nickname("bani33")
                .address("Seoul2")
                .status(UserStatus.PENDING)
                .lastLoginAt(100L)
                .certificationCode("a1234")
                .build();

        UserUpdate userUpdate = UserUpdate.builder()
                .nickname("kok202-k")
                .address("Pangyo")
                .build();
        // when
        user = user.update(userUpdate);

        // then
        assertThat(user.getId()).isEqualTo(1L);
        assertThat(user.getEmail()).isEqualTo("banseok@naver.com");
        assertThat(user.getNickname()).isEqualTo("kok202-k");
        assertThat(user.getAddress()).isEqualTo("Pangyo");
        assertThat(user.getStatus()).isEqualTo(UserStatus.PENDING);
        assertThat(user.getCertificationCode()).isEqualTo("a1234");
        assertThat(user.getLastLoginAt()).isEqualTo(100L);

    }

    @Test
    public void 로그인을_할수있고_로그인시_마지막_로그인시간이_반영된다() {
        // given
        User user = User.builder()
                .id(1L)
                .email("banseok@naver.com")
                .nickname("bani33")
                .address("Seoul2")
                .status(UserStatus.PENDING)
                .lastLoginAt(100L)
                .certificationCode("a1234")
                .build();
        // when
        user = user.login(new TestClockHolder(1533L));

        // then
        assertThat(user.getLastLoginAt()).isEqualTo(1533L);
    }

    @Test
    public void 유효한_인증코드로_계정을_활성화_할수있다() {
        // given
        User user = User.builder()
                .id(1L)
                .email("banseok@naver.com")
                .nickname("bani33")
                .address("Seoul2")
                .status(UserStatus.PENDING)
                .lastLoginAt(100L)
                .certificationCode("a1234")
                .build();
        // when
        user = user.certificate("a1234");

        // then
        assertThat(user.getStatus()).isEqualTo(UserStatus.ACTIVE);
    }

    @Test
    public void 잘못된_인증코드로_계정을_활성화하려하면_에러를_던진다() {
        // given
        User user = User.builder()
                .id(1L)
                .email("banseok@naver.com")
                .nickname("bani33")
                .address("Seoul2")
                .status(UserStatus.PENDING)
                .lastLoginAt(100L)
                .certificationCode("a1234")
                .build();
        // when


        // then
        assertThatThrownBy(() ->
            user.certificate("a12345")
        ).isInstanceOf(CertificationCodeNotMatchedException.class);

    }
}
