package com.t1study.taskmanager.dto.request;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.NotBlank;
import lombok.Builder;

import java.time.LocalDate;

@Builder
@Schema(description = "Новая задача")
public record TaskRequest(

        @Schema(description = "Название задачи",
                example = "Создать простую систему управления задачами")
        @NotBlank(message = "Имя задачи не может быть пустым")
        @Max(value = 250, message = "Имя задачи не должно превышать 250 символов.")
        String title,

        @Schema(description = "Описание задачи",
                example = "Создать простую систему управления задачами с использованием Spring Boot и Spring Data JPA. " +
                        "Система должна позволять создавать, просматривать, обновлять и удалять задачи.")
        @Max(value = 1000, message = "Описание задачи не должно превышать 1000 символов.")
        String description,

        @Schema(description = "Дата исполнения",
                example = "2024-04-18")
        LocalDate dueDate,

        @Schema(description = "Статус задачи: Выполнено/Не выполнено",
                example = "false")
        boolean completed
) {
}
