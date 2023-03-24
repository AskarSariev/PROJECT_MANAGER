package ru.advengineering.projectmanager.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
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

    @PostMapping("/new-task")
    public void addNewTask(@RequestBody Task task) {
        taskService.saveTask(task);
    }
}
