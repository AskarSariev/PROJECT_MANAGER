package ru.advengineering.projectmanager.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.advengineering.projectmanager.exceptions.ProjectNotFoundException;
import ru.advengineering.projectmanager.models.Project;
import ru.advengineering.projectmanager.repositories.ProjectRepository;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ProjectService {

    private final ProjectRepository projectRepository;

    @Autowired
    public ProjectService(ProjectRepository projectRepository) {
        this.projectRepository = projectRepository;
    }

    public List<Project> findAllProjects() {
        return projectRepository.findAll()
                .stream()
                .filter(project -> project.getParentProjectId() == null)
                .collect(Collectors.toList());
    }

    @Transactional
    public void saveProject(Project project) {
        projectRepository.save(project);
    }

    @Transactional
    public void updateProject(Project updatedProject) {
        Optional<Project> foundProject = projectRepository.findById(updatedProject.getId());
        if (foundProject.isEmpty()) {
            throw new ProjectNotFoundException();
        }
        projectRepository.save(updatedProject);
    }

    @Transactional
    public void deleteProject(int id) {
        Optional<Project> foundProject = projectRepository.findById(id);
        if (foundProject.isEmpty()) {
            throw new ProjectNotFoundException();
        }
        projectRepository.deleteById(id);
    }
}
