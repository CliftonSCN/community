package top.clifton.community.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import top.clifton.community.interceptor.SessionInterceptor;

/**
 * @author Clifton
 * @create 2020/2/6 - 20:16
 */
@Configuration
public class WebConfig implements WebMvcConfigurer
{

    @Autowired
    private SessionInterceptor sessionInterceptor;

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(sessionInterceptor)
                .addPathPatterns("/**").excludePathPatterns("/css/**")
                .excludePathPatterns("/js/**").excludePathPatterns("/fonts/**")
                .excludePathPatterns("/images/**").excludePathPatterns("/callback/**")
        .excludePathPatterns("/github.com/**");
    }
}
