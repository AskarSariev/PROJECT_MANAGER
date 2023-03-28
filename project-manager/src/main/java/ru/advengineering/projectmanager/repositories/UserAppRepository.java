package ru.advengineering.projectmanager.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.advengineering.projectmanager.models.UserApp;

import java.util.Optional;

@Repository
public interface UserAppRepository extends JpaRepository<UserApp, Integer> {

    Optional<UserApp> findByUsername(String username);
}
