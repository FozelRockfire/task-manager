package com.t1study.taskmanager.repository;

import com.t1study.taskmanager.model.Task;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;

@Repository
public interface TaskRepository extends JpaRepository<Task, Long> {

    @Modifying
    @Transactional
    @Query("""
           UPDATE Task t
           SET t.title = :title,
               t.description = :description,
               t.dueDate = :dueDate,
               t.completed = :completed
           WHERE t.id = :taskId
           """)
    void updateTaskById(Long taskId, String title, String description, LocalDate dueDate, boolean completed);

}
