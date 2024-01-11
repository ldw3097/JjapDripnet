package ldw3097.ldwboard.web;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import ldw3097.ldwboard.domain.User;
import ldw3097.ldwboard.service.UserService;
import ldw3097.ldwboard.web.form.LoginForm;
import ldw3097.ldwboard.web.form.UserSaveForm;
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
        try{
            userService.addUser(userSaveForm.getId(), userSaveForm.getPassword());
        }catch(RuntimeException e){
            bindingResult.rejectValue("id","existingId", "이미 존재하는 ID 입니다.");
            return "addMember";
        }
        return "redirect:/";
    }

    @GetMapping("/login")
    public String login(@ModelAttribute(name="loginForm")LoginForm loginForm){
        return "login";
    }

    @PostMapping("/login")
    public String login(@Valid @ModelAttribute LoginForm loginForm, BindingResult bindingResult, HttpSession httpSession){
        if(bindingResult.hasErrors()){
            return "login";
        }

        if(! userService.login(loginForm.getId(), loginForm.getPassword(), httpSession)){
            bindingResult.reject("loginFail", "아이디 또는 비밀번호가 맞지 않습니다.");
            return "login";
        }
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
