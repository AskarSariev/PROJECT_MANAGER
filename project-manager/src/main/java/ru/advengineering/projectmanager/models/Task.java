package ru.advengineering.projectmanager.models;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import java.time.LocalDate;

@Entity
@Table(name = "Tasks")
public class Task {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @Column(name = "name")
    @NotEmpty(message = "Task name shouldn't be empty")
    private String name;

    @Column(name = "status")
    @NotEmpty(message = "Task status shouldn't be empty")
    private String status;

    @Column(name = "create_date")
    @NotNull(message = "Create date shouldn't be null")
    private LocalDate createDate;

    @Column(name = "update_date")
    private LocalDate updateDate;

    @Column(name = "executor")
    private String executor;

    @Column(name = "project_id", nullable=false)
    @NotNull(message = "Project_ID shouldn't be null")
    private int projectId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "project_id", insertable=false, updatable=false)
    private Project project;

    public Task() {
    }

    public Task(String name, String status, LocalDate createDate, LocalDate updateDate, String executor, int projectId) {
        this.name = name;
        this.status = status;
        this.createDate = createDate;
        this.updateDate = updateDate;
        this.executor = executor;
        this.projectId = projectId;
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

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public LocalDate getCreateDate() {
        return createDate;
    }

    public void setCreateDate(LocalDate createDate) {
        this.createDate = createDate;
    }

    public LocalDate getUpdateDate() {
        return updateDate;
    }

    public void setUpdateDate(LocalDate updateDate) {
        this.updateDate = updateDate;
    }

    public String getExecutor() {
        return executor;
    }

    public void setExecutor(String executor) {
        this.executor = executor;
    }

    public int getProjectId() {
        return projectId;
    }

    public void setProjectId(int projectId) {
        this.projectId = projectId;
    }

    @Override
    public String toString() {
        return "Task{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", status='" + status + '\'' +
                ", createDate=" + createDate +
                ", updateDate=" + updateDate +
                ", executor='" + executor + '\'' +
                ", projectId=" + projectId +
                '}';
    }
}
