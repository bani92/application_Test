package me.whiteship.inflearnjavatest.toyProject.user.infrastructure;

import me.whiteship.inflearnjavatest.toyProject.user.domain.UserStatus;

import java.util.Optional;


public interface UserRepository {

    Optional<UserEntity> findById(long id);

    Optional<UserEntity> findByIdAndStatus(long id, UserStatus userStatus);

    Optional<UserEntity> findByEmailAndStatus(String email, UserStatus userStatus);

    UserEntity save(UserEntity userEntity);



}
