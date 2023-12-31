package ldw3097.JjapDripnet.service;

import ldw3097.JjapDripnet.domain.User;
import ldw3097.JjapDripnet.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;

    @Transactional
    public Long join(User user){
        userRepository.save(user);
        return user.getId();
    }
}
