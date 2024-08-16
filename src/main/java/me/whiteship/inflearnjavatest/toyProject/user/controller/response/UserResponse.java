package me.whiteship.inflearnjavatest.toyProject.user.controller.response;


import lombok.Getter;
import lombok.Setter;
import me.whiteship.inflearnjavatest.toyProject.user.domain.UserStatus;

@Getter
@Setter
public class UserResponse {

    private Long id;
    private String email;
    private String nickname;
    private UserStatus status;
    private Long lastLoginAt;
}