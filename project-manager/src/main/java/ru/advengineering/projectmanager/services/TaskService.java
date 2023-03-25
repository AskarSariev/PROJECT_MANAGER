package ru.advengineering.projectmanager.services;

import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.advengineering.projectmanager.exceptions.TaskNotFoundException;
import ru.advengineering.projectmanager.models.Task;
import ru.advengineering.projectmanager.repositories.TaskRepository;

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
        taskRepository.save(task);
    }

    @Transactional
    public void updateTask(Task updatedTask) {
        Optional<Task> foundTask = taskRepository.findById(updatedTask.getId());
        if (foundTask.isEmpty()) {
            throw new TaskNotFoundException();
        }
        taskRepository.save(updatedTask);
    }

    @Transactional
    public void deleteTask(int id) {
        Optional<Task> foundTask = taskRepository.findById(id);
        if (foundTask.isEmpty()) {
            throw new TaskNotFoundException();
        }
        taskRepository.deleteById(id);
    }
}
