package com.t1study.taskmanager.service;

import com.t1study.taskmanager.dto.request.TaskRequest;
import com.t1study.taskmanager.model.Task;

import java.util.List;

public interface TaskService {

    List<Task> getAllTasks();

    Task getTaskById(Long id);

    Task createTask(TaskRequest task);

    Task updateTask(Long id, TaskRequest taskRequest);

    void deleteTask(Long id);
}
