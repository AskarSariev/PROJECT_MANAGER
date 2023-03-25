package ru.advengineering.projectmanager.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;
import ru.advengineering.projectmanager.exceptions.ProjectErrorResponse;
import ru.advengineering.projectmanager.exceptions.ProjectNotCreatedException;
import ru.advengineering.projectmanager.exceptions.ProjectNotFoundException;
import ru.advengineering.projectmanager.exceptions.ProjectNotUpdatedException;
import ru.advengineering.projectmanager.models.Project;
import ru.advengineering.projectmanager.services.ProjectService;
import ru.advengineering.projectmanager.services.TaskService;
import ru.advengineering.projectmanager.utils.MessageFromBindingResult;

import javax.validation.Valid;
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

    @PostMapping("/project")
    public ResponseEntity<HttpStatus> createNewProject(@RequestBody @Valid Project project, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            throw new ProjectNotCreatedException(MessageFromBindingResult
                    .returnErrorMessageFromBindingResult(bindingResult).toString());
        }
        projectService.saveProject(project);
        return ResponseEntity.ok(HttpStatus.OK);
    }

    @PutMapping("/project")
    public ResponseEntity<HttpStatus> updateProject(@RequestBody @Valid Project project, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            throw new ProjectNotUpdatedException(MessageFromBindingResult
                    .returnErrorMessageFromBindingResult(bindingResult).toString());
        }
        projectService.updateProject(project);
        return ResponseEntity.ok(HttpStatus.OK);
    }

    @DeleteMapping("/project/{id}")
    public ResponseEntity<HttpStatus> deleteProject(@PathVariable("id") int id) {
        projectService.deleteProject(id);
        return ResponseEntity.ok(HttpStatus.OK);
    }

    @ExceptionHandler
    private ResponseEntity<ProjectErrorResponse> handleException(ProjectNotFoundException e) {
        ProjectErrorResponse response = new ProjectErrorResponse(
                "Project with this id wasn't found!",
                System.currentTimeMillis()
        );
        return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler
    private ResponseEntity<ProjectErrorResponse> handleException(ProjectNotCreatedException e) {
        ProjectErrorResponse response = new ProjectErrorResponse(
                e.getMessage(),
                System.currentTimeMillis()
        );
        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler
    private ResponseEntity<ProjectErrorResponse> handleException(ProjectNotUpdatedException e) {
        ProjectErrorResponse response = new ProjectErrorResponse(
                e.getMessage(),
                System.currentTimeMillis()
        );
        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }
}
