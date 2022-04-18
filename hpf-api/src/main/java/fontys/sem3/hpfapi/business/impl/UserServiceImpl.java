package fontys.sem3.hpfapi.business.impl;

import fontys.sem3.hpfapi.business.UserService;
import fontys.sem3.hpfapi.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

@Primary
@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;


}
