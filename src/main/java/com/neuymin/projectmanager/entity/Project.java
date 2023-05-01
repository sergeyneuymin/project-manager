package com.neuymin.projectmanager.entity;

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
@Table(name = "projects")
public class Project {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @Column(name = "title")
    private String title;

    @Column(name = "description")
    private String description;

    @Column(name = "author")
    private String author;

    @Column(name = "parent_id")
    private int parentId;

    public Project() {
    }

    public Project(int id, String title, String description, String author, int parentId) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.author = author;
        this.parentId = parentId;
    }
}
