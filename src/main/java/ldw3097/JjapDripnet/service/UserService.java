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
    public String join(User user){
        userRepository.save(user);
        return user.getId();
    }

    public User login(String id, String password){
        User user = userRepository.findOne(id);
        if(user ==null) return null;
        if(user.getPassword().equals(password)){
            return user;
        }else{
            return null;
        }
    }
}
