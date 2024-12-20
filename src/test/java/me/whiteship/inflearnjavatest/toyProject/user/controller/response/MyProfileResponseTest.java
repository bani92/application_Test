package me.whiteship.inflearnjavatest.toyProject.user.controller.response;

import me.whiteship.inflearnjavatest.toyProject.user.domain.User;
import me.whiteship.inflearnjavatest.toyProject.user.domain.UserStatus;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

class MyProfileResponseTest {

    @Test
    public void User으로_응답을_생성할_수_있다() {
        // given
        User user = User.builder()
                .id(1L)
                .email("banseok@naver.com")
                .nickname("bani")
                .address("Seoul")
                .status(UserStatus.PENDING)
                .certificationCode(UUID.randomUUID().toString())
                .build();

        // when
        MyProfileResponse myProfileResponse = MyProfileResponse.from(user);

        // then
        assertThat(myProfileResponse.getId()).isEqualTo(1);
        assertThat(myProfileResponse.getEmail()).isEqualTo("banseok@naver.com");
        assertThat(myProfileResponse.getNickname()).isEqualTo("bani");
        assertThat(myProfileResponse.getStatus()).isEqualTo(UserStatus.PENDING);

    }
}