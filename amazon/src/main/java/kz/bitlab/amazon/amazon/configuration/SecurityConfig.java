package kz.bitlab.amazon.amazon.configuration;


import kz.bitlab.amazon.amazon.services.UserService;
import kz.bitlab.amazon.amazon.services.impl.UserServiceImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;

import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;



@Configuration
@EnableWebSecurity
@EnableMethodSecurity
public class SecurityConfig {

    @Bean
    public UserService userService(){
        return new UserServiceImpl();
    }

    @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception{
        AuthenticationManagerBuilder authenticationManagerBuilder = http.getSharedObject(AuthenticationManagerBuilder.class);
        authenticationManagerBuilder.userDetailsService(userService()).passwordEncoder(passwordEncoder());

        http.exceptionHandling(
                exception -> exception.accessDeniedPage("/forbidden")
        );

        http.authorizeHttpRequests(
                authorize -> authorize
                        .requestMatchers("/entering", "/hello", "/sign-up", "/registration","/index").anonymous()
                        .requestMatchers("/sign-out", "/change-password", "/save-password").authenticated()
                        .requestMatchers("/profile").authenticated()
                        .requestMatchers("/users").hasAuthority("ROLE_ADMIN")
                        .anyRequest().permitAll()
        ).formLogin(
                login -> login
                        .loginProcessingUrl("/entering")
                        .defaultSuccessUrl("/")
                        .loginPage("/login")
                        .failureUrl("/login?error")
                        .usernameParameter("user_email")
                        .passwordParameter("user_password")
        ).logout(
                logout -> logout
                        .logoutSuccessUrl("/sign-in?logout")
                        .logoutUrl("/sign-out")
        ).csrf(AbstractHttpConfigurer::disable);

        return http.build();
    }
}