package me.whiteship.inflearnjavatest.toyProject.user.controller.response;


import lombok.Builder;
import lombok.Getter;
import me.whiteship.inflearnjavatest.toyProject.user.domain.User;
import me.whiteship.inflearnjavatest.toyProject.user.domain.UserStatus;

@Getter
@Builder
public class UserResponse {

    private Long id;
    private String email;
    private String nickname;
    private UserStatus status;
    private Long lastLoginAt;

    public static UserResponse from(User user) {
        return UserResponse.builder()
                .id(user.getId())
                .email(user.getEmail())
                .nickname(user.getNickname())
                .status(user.getStatus())
                .lastLoginAt(user.getLastLoginAt())
                .build();
    }
}
