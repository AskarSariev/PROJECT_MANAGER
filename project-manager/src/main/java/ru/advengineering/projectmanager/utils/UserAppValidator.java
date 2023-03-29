package ru.advengineering.projectmanager.utils;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;
import ru.advengineering.projectmanager.models.UserApp;
import ru.advengineering.projectmanager.services.UserAppDetailsService;

@Component
public class UserAppValidator implements Validator {

    private final UserAppDetailsService userAppDetailsService;

    @Autowired
    public UserAppValidator(UserAppDetailsService userAppDetailsService) {
        this.userAppDetailsService = userAppDetailsService;
    }

    @Override
    public boolean supports(Class<?> clazz) {
        return UserApp.class.equals(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
        UserApp userApp = (UserApp) target;
        try {
            userAppDetailsService.loadUserByUsername(userApp.getUsername());
        } catch (UsernameNotFoundException ignore) {
            return;
        }

        errors.rejectValue("username", "", "User with such name is already exist");
    }
}
