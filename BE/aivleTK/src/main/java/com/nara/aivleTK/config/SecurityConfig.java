package com.nara.aivleTK.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Bean
    public BCryptPasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
            .csrf(AbstractHttpConfigurer::disable) // CSRF 보안 끄기 (API 서버는 보통 끕니다)
            .formLogin(AbstractHttpConfigurer::disable) // 로그인 폼 화면 끄기
            .httpBasic(AbstractHttpConfigurer::disable) // HTTP Basic 인증 끄기
            .authorizeHttpRequests(auth -> auth
                .anyRequest().permitAll() // "누구든지 다 들어와도 좋아!" (전체 허용)
            );
        
        return http.build();
    }
}
