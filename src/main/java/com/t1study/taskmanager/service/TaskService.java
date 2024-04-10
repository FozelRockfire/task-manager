package com.t1study.taskmanager.service;

import com.t1study.taskmanager.model.Task;
import com.t1study.taskmanager.repository.TaskRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
public class TaskService {

    private final TaskRepository taskRepository;

    public List<Task> getAllTasks() {
        return taskRepository.findAll();
    }

    public Task getTaskById(long id) {
        return taskRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Задача по заданному id не найдена"));
    }

    public Task createTask(Task task) {
        return taskRepository.save(task);
    }

    public void upgradeTask(Long id, Task task) {
        taskRepository.updateTaskById(id, task.getTitle(),
                task.getDescription(), task.getDueDate(), task.isCompleted());
    }

    public void deleteTask(Long id) {
        taskRepository.deleteById(id);
    }
}
