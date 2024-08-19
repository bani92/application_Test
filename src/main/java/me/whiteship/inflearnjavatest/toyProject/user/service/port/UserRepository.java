package me.whiteship.inflearnjavatest.toyProject.user.service.port;

import me.whiteship.inflearnjavatest.toyProject.user.domain.User;
import me.whiteship.inflearnjavatest.toyProject.user.domain.UserStatus;

import java.util.Optional;


public interface UserRepository {

    Optional<User> findById(long id);

    Optional<User> findByIdAndStatus(long id, UserStatus userStatus);

    Optional<User> findByEmailAndStatus(String email, UserStatus userStatus);

    User save(User user);



}
