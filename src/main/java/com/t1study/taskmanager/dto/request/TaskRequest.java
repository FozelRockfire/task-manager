package com.t1study.taskmanager.dto.request;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;

import java.time.LocalDate;

@Builder
public record TaskRequest(

        @Schema(description = "Название задачи",
                example = "Создать простую систему управления задачами")

        String title,

        @Schema(description = "Описание задачи",
                example = "Создать простую систему управления задачами с использованием Spring Boot и Spring Data JPA. "+
                        "Система должна позволять создавать, просматривать, обновлять и удалять задачи.")
        String description,

        @Schema(description = "Дата исполнения",
                example = "2024-04-18")
        LocalDate dueDate,

        @Schema(description = "Выполнено/Не выполнено",
                example = "false")
        boolean completed
) {
}
