package ldw3097.ldwboard.service;

import ldw3097.ldwboard.domain.User;
import ldw3097.ldwboard.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public void addUser(String id, String password){
        String encodedPassword = passwordEncoder.encode(password);
        User user = new User();
        user.setId(id);
        user.setPassword(encodedPassword);
        userRepository.save(user);
    }

    public User login(String id, String password){
        User user = userRepository.findOne(id);
        if(user == null) return null;
        if(passwordEncoder.matches(password, user.getPassword())){
            return user;
        }else{
            return null;
        }
    }

}
