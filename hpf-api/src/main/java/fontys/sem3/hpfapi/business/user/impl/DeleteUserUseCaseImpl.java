package fontys.sem3.hpfapi.business.user.impl;

import fontys.sem3.hpfapi.business.user.DeleteUserUseCase;
import fontys.sem3.hpfapi.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
@RequiredArgsConstructor
public class DeleteUserUseCaseImpl implements DeleteUserUseCase {
    private final UserRepository userRepository;

    @Transactional
    @Override
    public void deleteUser(long userId) {
        this.userRepository.deleteById(userId);
    }
}
