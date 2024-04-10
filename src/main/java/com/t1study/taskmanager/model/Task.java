package com.t1study.taskmanager.model;


import java.time.LocalDate;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "t_tasks")
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Task {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Schema(description = "Id задачи",
            example = "1")
    private Long id;

    @Schema(description = "Название задачи",
            example = "Помыть посуду")
    @Column(name = "title")
    private String title;

    @Schema(description = "Описание задачи",
            example = "Кружки закончились!")
    @Column(name = "description")
    private String description;

    @Schema(description = "Дата исполнения",
            example = "2077-01-01")
    @Column(name = "dueDate")
    private LocalDate dueDate;

    @Schema(description = "Выполнено/Не выполнено",
            example = "false")
    @Column(name = "completed")
    private boolean completed;
}

