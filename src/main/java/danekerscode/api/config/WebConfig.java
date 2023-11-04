package danekerscode.api.config;

import danekerscode.api.common.interceptor.ApiKeyInterceptor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
@RequiredArgsConstructor
public class WebConfig implements WebMvcConfigurer {

    private final ApiKeyInterceptor apiKeyInterceptor;

    @Override
    public void addInterceptors(
           @NonNull InterceptorRegistry registry
    ) {
        registry.addInterceptor(apiKeyInterceptor);
        WebMvcConfigurer.super.addInterceptors(registry);
    }
}
