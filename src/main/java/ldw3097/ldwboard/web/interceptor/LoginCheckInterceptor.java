package ldw3097.ldwboard.web.interceptor;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import ldw3097.ldwboard.web.SessionConst;
import org.springframework.web.servlet.HandlerInterceptor;

/**
 * 로그인 여부를 체크하여 로그인하지 않았으면 로그인 화면으로 이동시킴
 */
public class LoginCheckInterceptor implements HandlerInterceptor {
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        HttpSession session = request.getSession(false);
        if(session == null || session.getAttribute(SessionConst.LOGIN_USER) == null){
            response.sendRedirect("/user/login");
            return false;
        }

        return true;
    }
}
