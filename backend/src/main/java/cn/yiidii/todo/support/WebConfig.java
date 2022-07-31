package cn.yiidii.todo.support;

import cn.yiidii.todo.intercepter.LoginInterceptor;
import cn.yiidii.web.config.WebMvcConfiguration;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport;

import javax.annotation.PostConstruct;
import java.util.List;

/**
 * WebConfig
 *
 * @author ed w
 * @since 1.0
 */
@Configuration
@RequiredArgsConstructor
public class WebConfig extends WebMvcConfiguration {
    private final LoginInterceptor loginInterceptor;

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        super.addInterceptors(registry);
        registry.addInterceptor(loginInterceptor).addPathPatterns("/**");
    }
}
