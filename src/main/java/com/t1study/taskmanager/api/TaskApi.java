package com.t1study.taskmanager.api;

import com.t1study.taskmanager.model.Task;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Tag(name = "Data Source", description = "Data Source API")
@RequestMapping("/api/tasks")
public interface TaskApi {

    @Operation(summary = "Получить список всех задач")
    @GetMapping
    ResponseEntity<List<Task>> getAllTasks();

    @Operation(summary = "Получить информацию о задаче по её id")
    @GetMapping("/{id}")
    ResponseEntity<Task> getTaskById(@PathVariable Long id);

    @Operation(summary = "Создать новую задачу")
    @PostMapping
    ResponseEntity<Task> createTask(@RequestBody Task task);

    @Operation(summary = "Обновить информацию о задаче")
    @PutMapping("/{id}")
    ResponseEntity<Void> updateTask(@PathVariable Long id, @RequestBody Task task);

    @Operation(summary = "Удалить задачу")
    @DeleteMapping("/{id}")
    ResponseEntity<Void> deleteTask(@PathVariable Long id);
}
