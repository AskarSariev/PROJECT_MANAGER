package ru.advengineering.projectmanager.configs;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import ru.advengineering.projectmanager.services.UserAppDetailsService;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true, securedEnabled = true, jsr250Enabled = true)
public class SecurityConfig {

    private final UserAppDetailsService userAppDetailsService;

    @Autowired
    public SecurityConfig(UserAppDetailsService userAppDetailsService) {
        this.userAppDetailsService = userAppDetailsService;
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http.csrf().disable()
                .httpBasic()
                .and()
                .authorizeHttpRequests()
                .requestMatchers(HttpMethod.GET, "/auth/registration").permitAll()
                .requestMatchers(HttpMethod.POST, "/auth/registration").permitAll()
                .requestMatchers(HttpMethod.POST, "/errors").permitAll()
                .requestMatchers(HttpMethod.GET, "/projects", "/tasks").hasAnyRole("ADMIN", "USER")
                .requestMatchers(HttpMethod.POST, "/project").hasRole("ADMIN")
                .requestMatchers(HttpMethod.POST, "/task").hasAnyRole("ADMIN", "USER")
                .requestMatchers(HttpMethod.DELETE, "/project/{id}").hasRole("ADMIN")
                .requestMatchers(HttpMethod.DELETE, "/task/{id}").hasAnyRole("ADMIN", "USER")
                .and()
                .formLogin().permitAll()
                .defaultSuccessUrl("/projects")
                .and()
                .logout()
                .logoutUrl("/logout")
                .logoutSuccessUrl("/login");

        return http.build();
    }
    @Bean
    public PasswordEncoder getPasswordEncoder() {
        return new BCryptPasswordEncoder();
    }

    protected void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userAppDetailsService)
                .passwordEncoder(getPasswordEncoder());
    }


}
