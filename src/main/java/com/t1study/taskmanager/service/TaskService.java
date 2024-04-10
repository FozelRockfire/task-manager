package com.t1study.taskmanager.service;

import com.t1study.taskmanager.dto.request.TaskRequest;
import com.t1study.taskmanager.exception.NotFoundException;
import com.t1study.taskmanager.mapper.TaskMapper;
import com.t1study.taskmanager.model.Task;
import com.t1study.taskmanager.repository.TaskRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.dao.EmptyResultDataAccessException;
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

    public Task getTaskById(Long id) {
        return taskRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Задача с id " + id + " не найдена"));
    }

    public Task createTask(TaskRequest task) {
        return taskRepository.save(TaskMapper.INSTANCE.toEntity(task));
    }

    public void upgradeTask(Long id, TaskRequest task) {
        try {
            taskRepository.updateTaskById(id, task.title(),
                    task.description(), task.dueDate(), task.completed());
        } catch (EmptyResultDataAccessException e) {
            throw new NotFoundException("Задача с id " + id + " не найдена"); //todo Ошибки выдать клиенту
        }
    }

    public void deleteTask(Long id) {
        taskRepository.deleteById(id);
    }
}
