package com.jezielmonteiro.task_api.domain;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import java.util.Objects;
import java.util.UUID;

@Embeddable
public record TaskId(@Column(name = "id") UUID value) {
    public TaskId {
        Objects.requireNonNull(value,
                "O ID da task não pode ser nulo");
    }
    public static TaskId newId() {
        return new TaskId(UUID.randomUUID());
    }
}