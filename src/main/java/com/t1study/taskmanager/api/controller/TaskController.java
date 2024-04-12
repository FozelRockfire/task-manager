package com.t1study.taskmanager.api.controller;

import com.t1study.taskmanager.api.TaskApi;
import com.t1study.taskmanager.dto.request.TaskRequest;
import com.t1study.taskmanager.model.Task;
import com.t1study.taskmanager.service.TaskService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class TaskController implements TaskApi {

    private final TaskService taskService;

    @Override
    public ResponseEntity<List<Task>> getAllTasks() {
        return ResponseEntity.status(HttpStatus.OK)
                .body(taskService.getAllTasks());
    }

    @Override
    public ResponseEntity<Task> getTaskById(Long id) {
        return ResponseEntity.status(HttpStatus.OK)
                .body(taskService.getTaskById(id));
    }

    @Override
    public ResponseEntity<Task> createTask(TaskRequest task) {
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(taskService.createTask(task));
    }

    @Override
    public ResponseEntity<Task> updateTask(Long id, TaskRequest task){
        return ResponseEntity.status(HttpStatus.OK)
                .body(taskService.upgradeTask(id, task));
    }

    @Override
    public ResponseEntity<Void> deleteTask(Long id) {
        taskService.deleteTask(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }
}
