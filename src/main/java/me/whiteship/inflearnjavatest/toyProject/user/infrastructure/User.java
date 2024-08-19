package me.whiteship.inflearnjavatest.toyProject.user.infrastructure;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import me.whiteship.inflearnjavatest.toyProject.user.domain.UserStatus;

@Getter
@Setter
@Entity
@Table(name = "users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "email")
    private String email;

    @Column(name = "nickname")
    private String nickname;

    @Column(name = "address")
    private String address;

    @Column(name = "certification_code")
    private String certificationCode;

    @Column(name = "status")
    @Enumerated(EnumType.STRING)
    private UserStatus status;

    @Column(name = "last_login_at")
    private Long lastLoginAt;

    public static User fromModel(me.whiteship.inflearnjavatest.toyProject.user.domain.User user) {
        User userEntity = new User();
        userEntity.id = user.getId();
        userEntity.email = user.getEmail();
        userEntity.nickname = user.getNickname();
        userEntity.address = user.getAddress();
        userEntity.certificationCode = user.getCertificationCode();
        userEntity.status = user.getStatus();
        userEntity.lastLoginAt = user.getLastLoginAt();
        return userEntity;
    }

    public me.whiteship.inflearnjavatest.toyProject.user.domain.User toModel() {
        return me.whiteship.inflearnjavatest.toyProject.user.domain.User.builder()
            .id(id)
            .email(email)
            .nickname(nickname)
            .address(address)
            .certificationCode(certificationCode)
            .status(status)
            .lastLoginAt(lastLoginAt)
            .build();
    }
}