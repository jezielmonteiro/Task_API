package com.jezielmonteiro.task_api.domain;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Entity
@Table(name="tasks")
public class Task {
    @EmbeddedId
    private TaskId id;

    @Column(nullable = false)
    private String title;
    private String description;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private TaskStatus status;

    public Task(String title, String description) {
        this.id = TaskId.newId();
        this.title = title;
        this.description = description;
        this.status = TaskStatus.PENDING;
    }

    public void update(String title, String description) {
        this.title = title;
        this.description = description;
    }

    public void start() {
        this.status = TaskStatus.IN_PROGRESS;
    }

    public void complete() {
        this.status = TaskStatus.COMPLETED;
    }
}