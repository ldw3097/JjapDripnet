package ldw3097.JjapDripnet.web;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import ldw3097.JjapDripnet.domain.User;
import ldw3097.JjapDripnet.repository.UserRepository;
import ldw3097.JjapDripnet.service.UserService;
import ldw3097.JjapDripnet.web.form.LoginForm;
import ldw3097.JjapDripnet.web.form.UserSaveForm;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;



@Controller
@RequiredArgsConstructor
@RequestMapping("user")
public class UserController {

    private final UserRepository userRepository;
    private final UserService userService;

    @GetMapping("/add")
    public String addForm(@ModelAttribute(name = "userSaveForm") UserSaveForm userSaveForm){
        return "addMember";
    }

    @PostMapping("/add")
    public String save(@Valid @ModelAttribute UserSaveForm userSaveForm, BindingResult bindingResult){
        if(bindingResult.hasErrors()){
            return "addMember";
        }
        User newUser = new User();
        newUser.setId(userSaveForm.getId());
        newUser.setPassword(userSaveForm.getPassword());
        userRepository.save(newUser);
        return "redirect:/";
    }

    @GetMapping("/login")
    public String login(@ModelAttribute(name="loginForm")LoginForm loginForm){
        return "login";
    }

    @PostMapping("/login")
    public String login(@Valid @ModelAttribute LoginForm loginForm, BindingResult bindingResult, HttpServletRequest request){
        if(bindingResult.hasErrors()){
            return "login";
        }
        User loginUser = userService.login(loginForm.getId(), loginForm.getPassword());
        if(loginUser == null){
            bindingResult.reject("loginFail", "아이디 또는 비밀번호가 맞지 않습니다.");
            return "login";
        }
        HttpSession session = request.getSession();
        session.setAttribute(SessionConst.LOGIN_USER, loginUser);
        return "redirect:/";
    }

    @GetMapping("/logout")
    public String logout(HttpServletRequest request){
        HttpSession session = request.getSession(false);
        if(session != null){
            session.invalidate();
        }
        return "redirect:/";
    }
}
