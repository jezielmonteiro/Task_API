package com.jezielmonteiro.task_api.web;

import com.jezielmonteiro.task_api.application.TaskService;
import com.jezielmonteiro.task_api.domain.Task;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.UUID;

@AllArgsConstructor
@RestController
@RequestMapping("/tasks")
public class TaskController {

    private final TaskService taskService;

    @PostMapping
    public ResponseEntity<Task> create(@Valid @RequestBody TaskRequest request) {
        Task task = taskService.create(
                request.title(),
                request.description()
        );

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(task);
    }

    @GetMapping
    public ResponseEntity<List<Task>> findAll() {
        return ResponseEntity.ok(taskService.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Task> findById(@PathVariable UUID id) {
        return ResponseEntity.ok(taskService.findById(id));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Task> update(
            @PathVariable UUID id,
            @Valid @RequestBody TaskRequest request
    ) {
        Task task = taskService.update(
                id,
                request.title(),
                request.description()
        );

        return ResponseEntity.ok(task);
    }

    @PatchMapping("/{id}/start")
    public ResponseEntity<Task> start(@PathVariable UUID id) {
        return ResponseEntity.ok(taskService.start(id));
    }

    @PatchMapping("/{id}/complete")
    public ResponseEntity<Task> complete(@PathVariable UUID id) {
        return ResponseEntity.ok(taskService.complete(id));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable UUID id) {
        taskService.delete(id);

        return ResponseEntity.noContent().build();
    }

    public record TaskRequest(
            @NotBlank(message = "O título é obrigatório")
            String title,
            String description
    ) {
    }
}