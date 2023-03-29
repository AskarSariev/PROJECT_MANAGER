package ru.advengineering.projectmanager.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import ru.advengineering.projectmanager.models.UserApp;
import ru.advengineering.projectmanager.repositories.UserAppRepository;
import ru.advengineering.projectmanager.security.UserAppDetails;

import java.util.Optional;

@Service
public class UserAppDetailsService implements UserDetailsService {

    private final UserAppRepository userAppRepository;

    @Autowired
    public UserAppDetailsService(UserAppRepository userAppRepository) {
        this.userAppRepository = userAppRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<UserApp> userApp = userAppRepository.findByUsername(username);

        if (userApp.isEmpty()) {
            throw new UsernameNotFoundException("User not found");
        }
        return new UserAppDetails(userApp.get());
    }
}
