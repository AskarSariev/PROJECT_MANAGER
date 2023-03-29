package ru.advengineering.projectmanager.controllers;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import ru.advengineering.projectmanager.models.UserApp;
import ru.advengineering.projectmanager.services.RegistrationService;
import ru.advengineering.projectmanager.utils.UserAppValidator;

@Controller
@RequestMapping("/auth")
public class AuthController {

    private final UserAppValidator userAppValidator;
    private final RegistrationService registrationService;

    @Autowired
    public AuthController(UserAppValidator userAppValidator, RegistrationService registrationService) {
        this.userAppValidator = userAppValidator;
        this.registrationService = registrationService;
    }

    @GetMapping("/registration")
    public String registrationPage(@ModelAttribute("userApp") UserApp userApp) {
        return "auth/registration";
    }

    @PostMapping("/registration")
    public String performRegistration(@ModelAttribute("userApp") @Valid UserApp userApp, BindingResult bindingResult) {
        userAppValidator.validate(userApp, bindingResult);

        if (bindingResult.hasErrors())
            return "/auth/registration";

        registrationService.register(userApp);
        return "redirect:/login";
    }
}
