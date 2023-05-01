package com.neuymin.projectmanager.entity;

import com.neuymin.projectmanager.additional.TaskStatus;
import com.neuymin.projectmanager.additional.TaskType;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.util.Date;

@Entity
@Getter
@Setter
@Table(name = "tasks")
@EntityListeners(AuditingEntityListener.class)
public class Task {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @Column(name = "title")
    private String title;

    @Enumerated(EnumType.STRING)
    @Column(name = "task_type")
    private TaskType taskType;

    @Enumerated(EnumType.STRING)
    @Column(name = "status")
    private TaskStatus status;

    @Column(name = "author")
    private String author;

    @Column(name = "date_created")
    @Temporal(TemporalType.DATE)
    @CreatedDate
    private Date dateCreated;

    @Column(name = "date_updated")
    @Temporal(TemporalType.DATE)
    @LastModifiedDate
    private Date dateUpdated;

    @Column(name = "description")
    private String description;

    @Column(name = "project_id")
    private int projectId;

    public Task() {
    }

    public Task(int id, String title, TaskType taskType, TaskStatus status, String author, Date dateCreated, Date dateUpdated, String description, int projectId) {
        this.id = id;
        this.title = title;
        this.taskType = taskType;
        this.status = status;
        this.author = author;
        this.dateCreated = dateCreated;
        this.dateUpdated = dateUpdated;
        this.description = description;
        this.projectId = projectId;
    }

    @Override
    public String toString() {
        return "Task{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", taskType='" + taskType + '\'' +
                ", status='" + status + '\'' +
                ", author='" + author + '\'' +
                ", dateCreated=" + dateCreated +
                ", dateUpdated=" + dateUpdated +
                ", description='" + description + '\'' +
                ", projectId=" + projectId +
                '}';
    }
}
