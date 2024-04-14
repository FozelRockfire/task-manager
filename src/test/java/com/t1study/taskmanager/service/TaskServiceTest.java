package com.t1study.taskmanager.service;

import com.t1study.taskmanager.dto.request.TaskRequest;
import com.t1study.taskmanager.exception.NotFoundException;
import com.t1study.taskmanager.model.Task;
import com.t1study.taskmanager.repository.TaskRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
@DisplayName("TaskService тесты")
class TaskServiceTest {

    @Mock
    private TaskRepository taskRepository;

    @InjectMocks
    private TaskService taskService;

    Task task1 = Task.builder()
            .id(1L)
            .title("Имя задачи 1")
            .description("Описание задачи 1")
            .dueDate(LocalDate.now())
            .completed(false)
            .build();

    @Test
    @DisplayName("Успешное получение всех задач")
    void getAllTasks() {
        // given
        Task task2 = Task.builder()
                .id(2L)
                .title("Имя задачи 2")
                .description("Описание задачи 2")
                .dueDate(LocalDate.now())
                .completed(true)
                .build();

        List<Task> tasks = Arrays.asList(task1, task2);

        doReturn(tasks).when(taskRepository).findAll();

        // when
        var result = taskService.getAllTasks();

        // then
        assertEquals(2L, result.get(1).getId());
        assertEquals("Имя задачи 2", result.get(1).getTitle());
        assertEquals("Описание задачи 2", result.get(1).getDescription());
        assertEquals(LocalDate.now(), result.get(1).getDueDate());
        assertTrue(result.get(1).isCompleted());

        verify(taskRepository).findAll();
        verifyNoMoreInteractions(taskRepository);
    }

    @Test
    @DisplayName("Успешное получение задачи по id")
    void getTaskById_IdExists_ReturnsTask() {
        // given
        doReturn(Optional.of(task1)).when(taskRepository).findById(1L);

        //when
        var result = taskService.getTaskById(1L);

        //then
        assertEquals(1L, result.getId());
        assertEquals("Имя задачи 1", result.getTitle());
        assertEquals("Описание задачи 1", result.getDescription());
        assertEquals(LocalDate.now(), result.getDueDate());
        assertFalse(result.isCompleted());

        verify(taskRepository).findById(1L);
        verifyNoMoreInteractions(taskRepository);
    }

    @Test
    @DisplayName("Получение задачи по id с ошибкой: несуществующий id")
    void getTaskById_IdNotExist_ThrowsNotFoundException() {
        // given
        doReturn(Optional.empty()).when(taskRepository).findById(3L);

        //then
        assertThrows(NotFoundException.class, () -> taskService.getTaskById(3L));

        verify(taskRepository).findById(3L);
        verifyNoMoreInteractions(taskRepository);
    }

    @Test
    @DisplayName("Успешное создание задачи")
    void createTask() {
        // given
        TaskRequest taskRequest = TaskRequest.builder()
                .title("Имя задачи 1")
                .description("Описание задачи 1")
                .dueDate(LocalDate.now())
                .completed(false)
                .build();

        doReturn(task1).when(taskRepository).save(any());

        //when
        var result = taskService.createTask(taskRequest);

        //then
        assertEquals(1L, result.getId());
        assertEquals("Имя задачи 1", result.getTitle());
        assertEquals("Описание задачи 1", result.getDescription());
        assertEquals(LocalDate.now(), result.getDueDate());
        assertFalse(result.isCompleted());

        verify(taskRepository).save(any());
        verifyNoMoreInteractions(taskRepository);
    }

    @Test
    @DisplayName("Успешное обновление задачи")
    void upgradeTask() {
        // given

        TaskRequest updatedTaskRequest = TaskRequest.builder()
                .title("Новое имя задачи")
                .description("Новое описание задачи")
                .dueDate(LocalDate.now().plusDays(1))
                .completed(true)
                .build();

        doReturn(Optional.of(task1)).when(taskRepository).findById(1L);
        doReturn(new Task(1L, "Новое имя задачи", "Новое описание задачи",
                LocalDate.now().plusDays(1), true)).when(taskRepository).save(any());

        // when
        var result = taskService.updateTask(1L, updatedTaskRequest);

        // then
        assertEquals(1L, result.getId());
        assertEquals("Новое имя задачи", result.getTitle());
        assertEquals("Новое описание задачи", result.getDescription());
        assertEquals(LocalDate.now().plusDays(1), result.getDueDate());
        assertTrue(result.isCompleted());

        verify(taskRepository).findById(1L);
        verify(taskRepository).save(any());
        verifyNoMoreInteractions(taskRepository);
    }

    @Test
    @DisplayName("Успешное удаление задачи")
    void deleteTask() {
        // when
        taskService.deleteTask(1L);

        // then
        verify(taskRepository).deleteById(1L);
        verifyNoMoreInteractions(taskRepository);
    }
}
