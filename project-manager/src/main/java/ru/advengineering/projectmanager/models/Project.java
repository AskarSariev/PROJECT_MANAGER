package ru.advengineering.projectmanager.models;

import jakarta.persistence.*;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "Projects")
public class Project {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @Column(name = "name")
    private String name;
    @Column(name = "parent_project_id")
    private Integer parentProjectId;

    @OneToMany(mappedBy = "project", fetch = FetchType.LAZY)
    private List<Task> tasks;

    public Project() {
    }

    public Project(String name) {
        this.name = name;
    }

    public Project(String name, Integer parentProjectId) {
        this.name = name;
        this.parentProjectId = parentProjectId;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getParentProjectId() {
        return parentProjectId;
    }

    public void setParentProjectId(Integer parentProjectId) {
        this.parentProjectId = parentProjectId;
    }

    public List<Task> getTasks() {
        return tasks;
    }

    public void setTasks(List<Task> tasks) {
        this.tasks = tasks;
    }

    @Override
    public String toString() {
        return "Project{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", parentProjectId=" + parentProjectId +
                ", tasks=" + tasks +
                '}';
    }
}
