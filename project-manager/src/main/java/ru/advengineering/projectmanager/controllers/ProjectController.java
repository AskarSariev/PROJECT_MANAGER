package ru.advengineering.projectmanager.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.advengineering.projectmanager.models.Project;
import ru.advengineering.projectmanager.services.ProjectService;
import ru.advengineering.projectmanager.services.TaskService;

import java.util.List;

@RestController
public class ProjectController {

    private final ProjectService projectService;
    private final TaskService taskService;

    @Autowired
    public ProjectController(ProjectService projectService, TaskService taskService) {
        this.projectService = projectService;
        this.taskService = taskService;
    }

    @GetMapping("/projects")
    public List<Project> getAllProjects() {
        return projectService.findAllProjects();
    }

    @PostMapping("/new-project")
    public void addNewProject(@RequestBody Project project) {
        projectService.saveProject(project);
    }

    @PutMapping("/put-project")
    public void updateProject(@RequestBody Project project) {
        projectService.updateProject(project);
    }
}
