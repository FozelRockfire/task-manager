package com.t1study.taskmanager.api;

import com.t1study.taskmanager.dto.request.TaskRequest;
import com.t1study.taskmanager.model.Task;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.ErrorResponse;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Tag(name = "Data Source", description = "Data Source API")
@RequestMapping("/api/tasks")
public interface TaskApi {

    @Operation(summary = "Получить список всех задач")
    @GetMapping
    @ApiResponses(value = {
            @ApiResponse(
                    code = 200,
                    message = "Список задач получен",
                    response = Task.class),
            @ApiResponse(
                    code = 400,
                    message = "В случае нарушения контракта",
                    response = ErrorResponse.class),
            @ApiResponse(
                    code = 500,
                    message = "В случае внутренних ошибок",
                    response = ErrorResponse.class)
    })
    ResponseEntity<List<Task>> getAllTasks();

    @Operation(summary = "Получить информацию о задаче по её id")
    @GetMapping("/{id}")
    @ApiResponses(value = {
            @ApiResponse(
                    code = 200,
                    message = "Задача получена",
                    response = Task.class),
            @ApiResponse(
                    code = 400,
                    message = "В случае нарушения контракта",
                    response = ErrorResponse.class),
            @ApiResponse(
                    code = 500,
                    message = "В случае внутренних ошибок",
                    response = ErrorResponse.class)
    })
    ResponseEntity<Task> getTaskById(
            @Parameter(name = "id", description = "id Задачи", example = "1")
            @PathVariable Long id);

    @Validated
    @Operation(summary = "Создать новую задачу")
    @PostMapping
    @ApiResponses(value = {
            @ApiResponse(
                    code = 201,
                    message = "Задача создана",
                    response = Task.class),
            @ApiResponse(
                    code = 400,
                    message = "В случае нарушения контракта",
                    response = ErrorResponse.class),
            @ApiResponse(
                    code = 500,
                    message = "В случае внутренних ошибок",
                    response = ErrorResponse.class)
    })
    ResponseEntity<Task> createTask(
            @Valid @RequestBody TaskRequest task);

    @Validated
    @Operation(summary = "Обновить информацию о задаче")
    @PutMapping("/{id}")
    @ApiResponses(value = {
            @ApiResponse(
                    code = 200,
                    message = "Задача обновлена"),
            @ApiResponse(
                    code = 400,
                    message = "В случае нарушения контракта",
                    response = ErrorResponse.class),
            @ApiResponse(
                    code = 500,
                    message = "В случае внутренних ошибок",
                    response = ErrorResponse.class)
    })
    ResponseEntity<Task> updateTask(
            @Parameter(name = "id", description = "id Задачи", example = "1")
            @PathVariable Long id,
            @Valid @RequestBody TaskRequest task);

    @Operation(summary = "Удалить задачу")
    @DeleteMapping("/{id}")
    @ApiResponses(value = {
            @ApiResponse(
                    code = 204,
                    message = "Задача удалена"),
            @ApiResponse(
                    code = 400,
                    message = "В случае нарушения контракта",
                    response = ErrorResponse.class),
            @ApiResponse(
                    code = 500,
                    message = "В случае внутренних ошибок",
                    response = ErrorResponse.class)
    })
    ResponseEntity<Void> deleteTask(
            @Parameter(name = "id", description = "id Задачи", example = "1")
            @PathVariable Long id);
}
