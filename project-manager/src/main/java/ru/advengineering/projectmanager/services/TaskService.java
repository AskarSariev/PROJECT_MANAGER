package ru.advengineering.projectmanager.services;

import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.advengineering.projectmanager.models.Task;
import ru.advengineering.projectmanager.repositories.TaskRepository;

import java.util.List;

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
    public void updateTask(Task task) {
        taskRepository.save(task);
    }

    @Transactional
    public void deleteTask(int id) {
        taskRepository.deleteById(id);
    }
}
