package com.t1study.taskmanager.api.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.t1study.taskmanager.dto.request.TaskRequest;
import com.t1study.taskmanager.model.Task;
import com.t1study.taskmanager.service.TaskService;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(MockitoExtension.class)
@DisplayName("TaskController тесты")
class TaskControllerTest {

    @Mock
    private TaskService taskService;

    @InjectMocks
    private TaskController taskController;

    private MockMvc mockMvc;

    private static ObjectMapper objectMapper;

    @BeforeAll
    static void objectMapperSetUp() {
        objectMapper = new ObjectMapper();
        objectMapper.registerModule(new JavaTimeModule());
    }

    @BeforeEach
    void setUp() {
        mockMvc = MockMvcBuilders.standaloneSetup(taskController).build();
    }

    Task task1 = Task.builder()
            .id(1L)
            .title("Имя задачи 1")
            .description("Описание задачи 1")
            .dueDate(LocalDate.now())
            .completed(false)
            .build();

    @Test
    @DisplayName("Успешное получение всех задач")
    void getAllTasks() throws Exception {
        //given
        Task task2 = Task.builder()
                .id(2L)
                .title("Имя задачи 2")
                .description("Описание задачи 2")
                .dueDate(LocalDate.now())
                .completed(true)
                .build();

        List<Task> tasks = Arrays.asList(task1, task2);

        doReturn(tasks).when(taskService).getAllTasks();

        //then
        mockMvc.perform(get("/api/tasks"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].id").value(1))
                .andExpect(jsonPath("$[0].title").value("Имя задачи 1"))
                .andExpect(jsonPath("$[0].description").value("Описание задачи 1"))
                .andExpect(jsonPath("$[0].dueDate").exists())
                .andExpect(jsonPath("$[0].completed").value(false))
                .andExpect(jsonPath("$[1].id").value(2))
                .andExpect(jsonPath("$[1].title").value("Имя задачи 2"))
                .andExpect(jsonPath("$[1].description").value("Описание задачи 2"))
                .andExpect(jsonPath("$[1].dueDate").exists())
                .andExpect(jsonPath("$[1].completed").value(true));

        verify(taskService).getAllTasks();
        verifyNoMoreInteractions(taskService);
    }

    @Test
    @DisplayName("Успешное получение задачи по id")
    void getTaskById() throws Exception {
        //given
        doReturn(task1).when(taskService).getTaskById(1L);

        //then
        mockMvc.perform(get("/api/tasks/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(1))
                .andExpect(jsonPath("$.title").value("Имя задачи 1"))
                .andExpect(jsonPath("$.description").value("Описание задачи 1"))
                .andExpect(jsonPath("$.dueDate").exists())
                .andExpect(jsonPath("$.completed").value(false));

        verify(taskService).getTaskById(1L);
        verifyNoMoreInteractions(taskService);
    }

    @Test
    @DisplayName("Успешное создание задачи")
    void createTask() throws Exception {
        //given
        TaskRequest taskRequest = TaskRequest.builder()
                .title("Имя задачи 1")
                .description("Описание задачи 1")
                .dueDate(LocalDate.now())
                .completed(false)
                .build();

        String jsonRequest = objectMapper.writeValueAsString(taskRequest);

        doReturn(task1).when(taskService).createTask(taskRequest);

        //then
        mockMvc.perform(post("/api/tasks")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(jsonRequest))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.id").value(1))
                .andExpect(jsonPath("$.title").value("Имя задачи 1"))
                .andExpect(jsonPath("$.description").value("Описание задачи 1"))
                .andExpect(jsonPath("$.dueDate").exists())
                .andExpect(jsonPath("$.completed").value(false));

        verify(taskService).createTask(taskRequest);
        verifyNoMoreInteractions(taskService);
    }

    @Test
    @DisplayName("Успешное обновление задачи")
    void updateTask() throws Exception {
        //given
        TaskRequest updatedTaskRequest = TaskRequest.builder()
                .title("Новое имя задачи")
                .description("Новое описание задачи")
                .dueDate(LocalDate.now().plusDays(1))
                .completed(true)
                .build();

        String jsonRequest = objectMapper.writeValueAsString(updatedTaskRequest);

        doReturn(new Task(1L, "Новое имя задачи", "Новое описание задачи",
                LocalDate.now().plusDays(1), true)).when(taskService).updateTask(1L, updatedTaskRequest);

        //then
        mockMvc.perform(put("/api/tasks/1")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(jsonRequest))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(1))
                .andExpect(jsonPath("$.title").value("Новое имя задачи"))
                .andExpect(jsonPath("$.description").value("Новое описание задачи"))
                .andExpect(jsonPath("$.dueDate").exists())
                .andExpect(jsonPath("$.completed").value(true));

        verify(taskService).updateTask(1L, updatedTaskRequest);
        verifyNoMoreInteractions(taskService);
    }

    @Test
    @DisplayName("Успешное удаление задачи")
    void deleteTask() throws Exception {
        //then
        mockMvc.perform(delete("/api/tasks/1"))
                .andExpect(status().isNoContent());

        verify(taskService).deleteTask(1L);
        verifyNoMoreInteractions(taskService);
    }
}
