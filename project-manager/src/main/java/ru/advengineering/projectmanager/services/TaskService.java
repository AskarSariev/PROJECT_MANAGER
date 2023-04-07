package ru.advengineering.projectmanager.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.advengineering.projectmanager.exceptions.TaskNotCreatedException;
import ru.advengineering.projectmanager.exceptions.TaskNotFoundException;
import ru.advengineering.projectmanager.exceptions.TaskNotUpdatedException;
import ru.advengineering.projectmanager.models.Task;
import ru.advengineering.projectmanager.repositories.TaskRepository;
import ru.advengineering.projectmanager.utils.Executor;
import ru.advengineering.projectmanager.utils.Status;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@Service
public class TaskService {

    private final TaskRepository taskRepository;

    @Autowired
    public TaskService(TaskRepository taskRepository) {
        this.taskRepository = taskRepository;
    }

    public List<Task> findAllTasks() {
        return taskRepository.findAll();
    }

    @Transactional
    public void saveTask(Task task) {
        if (checkValueFieldTaskStatus(task)) {
            throw new TaskNotCreatedException("Task status should be NEW, PROGRESS or DONE");
        }
        if (checkValueFieldTaskExecutor(task)) {
            throw new TaskNotCreatedException("Task executor should be MANAGER or TECH_SPECIALIST");
        }
        task.setStatus(task.getStatus().toUpperCase());
        task.setExecutor(task.getExecutor().toUpperCase());
        taskRepository.save(task);
    }

    @Transactional
    public void updateTask(Task updatedTask) {
        Optional<Task> foundTask = taskRepository.findById(updatedTask.getId());
        if (foundTask.isEmpty()) {
            throw new TaskNotFoundException("");
        }
        if (checkValueFieldTaskStatus(updatedTask)) {
            throw new TaskNotUpdatedException("Task status should be NEW, PROGRESS or DONE");
        }
        if (checkValueFieldTaskExecutor(updatedTask)) {
            throw new TaskNotUpdatedException("Task executor should be MANAGER or TECH_SPECIALIST");
        }
        updatedTask.setStatus(updatedTask.getStatus().toUpperCase());
        updatedTask.setExecutor(updatedTask.getExecutor().toUpperCase());
        taskRepository.save(updatedTask);
    }

    @Transactional
    public void deleteTask(int id) {
        Optional<Task> foundTask = taskRepository.findById(id);
        if (foundTask.isEmpty()) {
            throw new TaskNotFoundException("Task with this id wasn't found!");
        }
        taskRepository.deleteById(id);
    }

    private boolean checkValueFieldTaskStatus(Task task) {
        return !(Arrays.stream(Status.values())
                .anyMatch(e -> e.toString().equalsIgnoreCase(task.getStatus())));
    }

    private boolean checkValueFieldTaskExecutor(Task task) {
        return !(Arrays.stream(Executor.values())
                .anyMatch(e -> e.toString().equalsIgnoreCase(task.getExecutor())));
    }
}
