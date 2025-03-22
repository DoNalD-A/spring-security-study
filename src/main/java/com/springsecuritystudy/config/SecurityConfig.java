package com.springsecuritystudy.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.ResponseEntity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
//SecurityConfig클래스란 어떤 URL 요청이 허용되고, 권한을 요구하는지 결정하는 보안 규칙
public class SecurityConfig {

    ResponseEntity

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {

        /*
         * 위에서부터 깔때기 형태로 필터링된다(맨 위에 anyRequest() '모두 혀용'을 해주면 아무 소용 없어진다 (매우 중요)
         * .authorizeHttpRequest()메서드는 안에 작성된 '순서대로' 적용된다 (매우 중요)
         */
        http
                .authorizeHttpRequests((auth) -> auth
                        .requestMatchers("/", "/login").permitAll() // "/"와 "/login"은 누구나 접근 가능
                        .requestMatchers("/admin").hasRole("ADMIN") // /"admin" 경로는 ADMIN 권한이 있어야 접근 가능
                        .requestMatchers("/my/**").hasAnyRole("ADMIN", "USER") // "/my" 경로는 ADMIN or USER 권한 있으면 접근 가능
                        .anyRequest().authenticated() //그 외 모든 요청은 '로그인 상태'여야 접근 가능
                );



        return http.build();
    }
}
