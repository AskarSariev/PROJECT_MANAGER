package ru.advengineering.projectmanager.controllers;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import ru.advengineering.projectmanager.exceptions.*;
import ru.advengineering.projectmanager.models.Task;
import ru.advengineering.projectmanager.services.TaskService;
import ru.advengineering.projectmanager.utils.ErrorMessageFromBindingResult;

import java.util.List;

@RestController
public class TaskController {

    private final TaskService taskService;

    @Autowired
    public TaskController(TaskService taskService) {
        this.taskService = taskService;
    }

    @GetMapping("/tasks")
    public List<Task> getAllTasks() {
        return taskService.findAllTasks();
    }

    @PostMapping("/task")
    public ResponseEntity<HttpStatus> addNewTask(@RequestBody @Valid Task task, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            throw new TaskNotCreatedException(ErrorMessageFromBindingResult
                    .returnErrorMessageFromBindingResult(bindingResult).toString());
        }
        taskService.saveTask(task);
        return ResponseEntity.ok(HttpStatus.OK);
    }

    @PutMapping("/task")
    public ResponseEntity<HttpStatus> updateTask(@RequestBody @Valid Task task, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            throw new TaskNotUpdatedException(ErrorMessageFromBindingResult
                    .returnErrorMessageFromBindingResult(bindingResult).toString());
        }
        taskService.updateTask(task);
        return ResponseEntity.ok(HttpStatus.OK);
    }

    @DeleteMapping("/task/{id}")
    public ResponseEntity<HttpStatus> deleteTask(@PathVariable("id") int id) {
        try {
            taskService.deleteTask(id);
            return ResponseEntity.ok(HttpStatus.OK);
        } catch (TaskNotFoundException e) {
            throw new TaskNotFoundException("Task with this id wasn't found!");
        }
    }
}
