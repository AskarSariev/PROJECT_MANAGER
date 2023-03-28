package ru.advengineering.projectmanager.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import ru.advengineering.projectmanager.models.UserApp;

import java.util.Collection;
import java.util.Collections;

public class UserAppDetails implements UserDetails {

    private final UserApp userApp;

    @Autowired
    public UserAppDetails(UserApp userApp) {
        this.userApp = userApp;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return Collections.singletonList(new SimpleGrantedAuthority(userApp.getRole()));
    }

    @Override
    public String getPassword() {
        return this.userApp.getPassword();
    }

    @Override
    public String getUsername() {
        return this.userApp.getUsername();
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }

    public UserApp getUserApp() {
        return userApp;
    }
}
