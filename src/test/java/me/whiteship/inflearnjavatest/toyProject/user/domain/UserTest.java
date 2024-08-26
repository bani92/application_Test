package me.whiteship.inflearnjavatest.toyProject.user.domain;

import me.whiteship.inflearnjavatest.toyProject.mock.TestUuidHolder;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

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

        // when

        // then
    }

    @Test
    public void 로그인을_할수있고_로그인시_마지막_로그인시간이_반영된다() {
        // given

        // when

        // then
    }

    @Test
    public void 유효한_인증코드로_계정을_활성화_할수있다() {
        // given

        // when

        // then
    }

    @Test
    public void 잘못된_인증코드로_계정을_활성화하려하면_에러를_던진다() {
        // given

        // when

        // then
    }
}
