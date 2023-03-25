package ru.advengineering.projectmanager.configs;

import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.crypto.password.PasswordEncoder;

@EnableWebSecurity
//@EnableGlobalMethodSecurity(prePostEnabled=true,proxyTargetClass=true)
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Override
    protected void configure(HttpSecurity http) throws Exception {

        http
                .httpBasic()
                .and()
                .authorizeRequests()
                .antMatchers(HttpMethod.GET, "/projects", "/tasks").hasAnyRole("USER", "ADMIN")
                .antMatchers(HttpMethod.POST, "/task").hasAnyRole("USER", "ADMIN")
                .antMatchers(HttpMethod.POST, "/project").hasRole("ADMIN")
                .antMatchers(HttpMethod.PUT, "/task").hasAnyRole("USER", "ADMIN")
                .antMatchers(HttpMethod.PUT, "/project").hasRole("ADMIN")
                .antMatchers(HttpMethod.DELETE, "/task/**").hasAnyRole("USER", "ADMIN")
                .antMatchers(HttpMethod.DELETE, "/project/**").hasRole("ADMIN")
                .and()
                .csrf().disable()
                .formLogin().disable();
//                .formLogin().permitAll()
//                .defaultSuccessUrl("/projects")
//                .and()
//                .logout()
//                .logoutUrl("/logout")
//                .logoutSuccessUrl("/login");
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        User.UserBuilder userBuilder = User.withDefaultPasswordEncoder();

        auth.inMemoryAuthentication()
                .withUser(userBuilder.username("user").password("user").roles("USER"))
                .withUser(userBuilder.username("admin").password("admin").roles("ADMIN"));
    }
}
