package ru.advengineering.projectmanager.models;

import jakarta.persistence.*;

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
    private int parentProjectId;

    @OneToMany(mappedBy = "projects")
    private List<Task> tasks;

    public Project() {
    }

    public Project(String name) {
        this.name = name;
    }

    public Project(String name, int parentProjectId) {
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

    public int getParentProjectId() {
        return parentProjectId;
    }

    public void setParentProjectId(int parentProjectId) {
        this.parentProjectId = parentProjectId;
    }

    @Override
    public String toString() {
        return "Project{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", parentProjectId=" + parentProjectId +
                '}';
    }
}
