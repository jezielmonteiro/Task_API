package com.jezielmonteiro.task_api.infrastructure;

import com.jezielmonteiro.task_api.domain.Task;
import com.jezielmonteiro.task_api.domain.TaskId;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TaskRepository extends JpaRepository<Task, TaskId> {
}