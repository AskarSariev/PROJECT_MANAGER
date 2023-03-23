package ru.advengineering.projectmanager.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.advengineering.projectmanager.models.Task;

@Repository
public interface TaskRepository extends JpaRepository<Task, Integer> {
}
