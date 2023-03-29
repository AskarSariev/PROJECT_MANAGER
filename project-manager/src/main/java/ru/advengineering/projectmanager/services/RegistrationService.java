package ru.advengineering.projectmanager.services;

import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import ru.advengineering.projectmanager.models.UserApp;
import ru.advengineering.projectmanager.repositories.UserAppRepository;

@Service
public class RegistrationService {

    private final UserAppRepository userAppRepository;
    private final PasswordEncoder passwordEncoder;

    @Autowired
    public RegistrationService(UserAppRepository userAppRepository, PasswordEncoder passwordEncoder) {
        this.userAppRepository = userAppRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Transactional
    public void register(UserApp userApp) {
        String encodedPassword = passwordEncoder.encode(userApp.getPassword());
        userApp.setPassword(encodedPassword);
        userApp.setRole("ROLE_USER");
        userAppRepository.save(userApp);
    }
}
