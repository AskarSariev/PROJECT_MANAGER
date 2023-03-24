package ru.advengineering.projectmanager.services;

import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.advengineering.projectmanager.models.Project;
import ru.advengineering.projectmanager.repositories.ProjectRepository;

import java.util.List;

@Service
public class ProjectService {

    private final ProjectRepository projectRepository;

    @Autowired
    public ProjectService(ProjectRepository projectRepository) {
        this.projectRepository = projectRepository;
    }

    public List<Project> findAllProjects() {
        return projectRepository.findAll();
    }

    @Transactional
    public void saveProject(Project project) {
        projectRepository.save(project);
    }

    @Transactional
    public void updateProject(Project project) {
        projectRepository.save(project);
    }

    @Transactional
    public void deleteProject(int id) {
        projectRepository.deleteById(id);
    }
}
