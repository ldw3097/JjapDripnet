package ldw3097.JjapDripnet.web;

import ldw3097.JjapDripnet.web.interceptor.LoginCheckInterceptor;
import ldw3097.JjapDripnet.web.interceptor.LoginMemberInfoInterceptor;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig implements WebMvcConfigurer {
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(new LoginMemberInfoInterceptor())
                .order(1)
                .addPathPatterns("/**")
                .excludePathPatterns("/css/**", "/*.ico", "/error", "/js/**");
    }
}
