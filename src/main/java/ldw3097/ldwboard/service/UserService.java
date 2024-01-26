package ldw3097.ldwboard.service;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import ldw3097.ldwboard.domain.User;
import ldw3097.ldwboard.repository.UserRepository;
import ldw3097.ldwboard.web.SessionConst;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    @Transactional
    public void addUser(String id, String password){
        if(userRepository.existsById(id)){
            throw new RuntimeException("이미 존재하는 아이디 입니다.");
        }
        String encodedPassword = passwordEncoder.encode(password);
        User user = new User();
        user.setId(id);
        user.setPassword(encodedPassword);
        userRepository.save(user);
    }


    public Boolean login(String id, String password, HttpSession session){
        Optional<User> user = userRepository.findById(id);
        if(user.isPresent() && passwordEncoder.matches(password, user.get().getPassword())){
            session.setAttribute(SessionConst.LOGIN_USER, user.get());
            return true;
        }else{
            return false;
        }
    }

}
