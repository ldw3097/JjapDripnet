package ldw3097.JjapDripnet.web.interceptor;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import ldw3097.JjapDripnet.web.SessionConst;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;

public class LoginCheckInterceptor implements HandlerInterceptor {
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        HttpSession session = request.getSession(false);
        if(session == null || session.getAttribute(SessionConst.LOGIN_USER) == null){
            response.sendRedirect("/member/login");
            return false;
        }

        return true;
    }
}
