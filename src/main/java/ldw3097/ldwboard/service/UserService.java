package ldw3097.ldwboard.service;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import ldw3097.ldwboard.domain.User;
import ldw3097.ldwboard.repository.UserRepository;
import ldw3097.ldwboard.web.SessionConst;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public void addUser(String id, String password){
        if(userRepository.findOne(id) != null){
            throw new RuntimeException("이미 존재하는 아이디 입니다.");
        }
        String encodedPassword = passwordEncoder.encode(password);
        User user = new User();
        user.setId(id);
        user.setPassword(encodedPassword);
        userRepository.save(user);
    }

    public Boolean login(String id, String password, HttpSession session){
        User user = userRepository.findOne(id);
        if(user == null) return false;
        if(passwordEncoder.matches(password, user.getPassword())){
            session.setAttribute(SessionConst.LOGIN_USER, user);
            return true;
        }else{
            return false;
        }
    }

}
