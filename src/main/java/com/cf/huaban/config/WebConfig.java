package com.cf.huaban.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig implements WebMvcConfigurer {
    @Autowired
    private JwtInterceptor jwtInterceptor;
    @Override
    public void addCorsMappings(CorsRegistry registry) {
        // 配置跨域规则，但排除静态资源路径
        registry.addMapping("/**")
                .allowedOrigins("*") // 前端应用的地址
                .allowedMethods("GET", "POST", "PUT", "DELETE", "OPTIONS")
                .allowedHeaders("*")
                .allowCredentials(true)
                .exposedHeaders("Authorization", "Content-Disposition"); // 暴露特定的响应头
    }

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        // 映射静态资源路径
        registry.addResourceHandler("/static/**")
                .addResourceLocations("classpath:/static/", "file:" + System.getProperty("user.dir") + "/static/img/");
    }


    @Override
        public void addInterceptors(InterceptorRegistry registry) {
            registry.addInterceptor(jwtInterceptor)
                    .addPathPatterns("/**")
                    .excludePathPatterns(
                            "/static/**","/","/static/",
                            "/login","/category","/index","/particulars","/us","/my","/active","/admin",
                            "/doc.html", "/swagger-resources", "/swagger-resources/**","/v3/api-docs", "/webjars/**",
                            "/user/login", "/user/register","/user/getCode","/user/verifyCode",
                            "/works/user/commentcount","/works/negative","/works/top","/actives/active","/user/getUserbyid",
                            "/admin/works/"
                    );
        }

}