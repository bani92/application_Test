package me.whiteship.inflearnjavatest.toyProject.user.infrastructure;

import lombok.RequiredArgsConstructor;
import me.whiteship.inflearnjavatest.toyProject.user.domain.UserStatus;
import me.whiteship.inflearnjavatest.toyProject.user.service.port.UserRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class UserRepositoryImpl implements UserRepository {

    private final UserJpaRepository userJpaRepository;

    @Override
    public Optional<me.whiteship.inflearnjavatest.toyProject.user.domain.User> findById(long id) {
        return userJpaRepository.findById(id).map(User::toModel);
    }

    @Override
    public Optional<me.whiteship.inflearnjavatest.toyProject.user.domain.User> findByIdAndStatus(long id, UserStatus userStatus) {
        return userJpaRepository.findByIdAndStatus(id, userStatus).map(User::toModel);
    }

    @Override
    public Optional<me.whiteship.inflearnjavatest.toyProject.user.domain.User> findByEmailAndStatus(String email, UserStatus userStatus) {
        return userJpaRepository.findByEmailAndStatus(email, userStatus).map(User::toModel);
    }

    @Override
    public me.whiteship.inflearnjavatest.toyProject.user.domain.User save(me.whiteship.inflearnjavatest.toyProject.user.domain.User user) {
        return userJpaRepository.save(User.fromModel(user)).toModel();
    }
}
