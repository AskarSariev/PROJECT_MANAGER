package ru.advengineering.projectmanager.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.advengineering.projectmanager.models.Project;

@Repository
public interface ProjectRepository extends JpaRepository<Project, Integer> {
}
