package me.whiteship.inflearnjavatest.toyProject.user.controller.response;

import me.whiteship.inflearnjavatest.toyProject.user.domain.User;
import me.whiteship.inflearnjavatest.toyProject.user.domain.UserStatus;
import org.junit.jupiter.api.Test;

import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;

public class UserResponseTest {

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
        UserResponse userResponse = UserResponse.from(user);

        // then
        assertThat(userResponse.getId()).isEqualTo(1);
        assertThat(userResponse.getEmail()).isEqualTo("banseok@naver.com");
        assertThat(userResponse.getNickname()).isEqualTo("bani");
        assertThat(userResponse.getStatus()).isEqualTo(UserStatus.PENDING);


    }
}
