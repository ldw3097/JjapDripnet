package ldw3097.ldwboard.web.interceptor;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import ldw3097.ldwboard.domain.User;
import ldw3097.ldwboard.web.SessionConst;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import static org.thymeleaf.spring6.view.ThymeleafViewResolver.REDIRECT_URL_PREFIX;

@Slf4j
public class LoginMemberInfoInterceptor implements HandlerInterceptor {
    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
        HttpSession session = request.getSession(false);
        if(session == null){
            return;
        }
        User user = (User) session.getAttribute(SessionConst.LOGIN_USER);
        if(user == null){
            return;
        }

        if(!modelAndView.getViewName().startsWith(REDIRECT_URL_PREFIX)) {
            modelAndView.addObject("userId", user.getId());
        }


    }
}
