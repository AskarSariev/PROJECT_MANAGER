package ru.advengineering.projectmanager.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import ru.advengineering.projectmanager.models.Task;
import ru.advengineering.projectmanager.services.TaskService;

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
    public void addNewTask(@RequestBody Task task) {
        taskService.saveTask(task);
    }

    @PutMapping("/task")
    public void updateTask(@RequestBody Task task) {
        taskService.updateTask(task);
    }

    @DeleteMapping("/task/{id}")
    public void deleteTask(@PathVariable("id") int id) {
        taskService.deleteTask(id);
    }
}
