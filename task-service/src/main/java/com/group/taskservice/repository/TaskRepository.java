package com.group.taskservice.repository;

import com.group.taskservice.entity.Task;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface TaskRepository extends JpaRepository<Task, Long> {

    List<Task> findByProjectId(Long projectId);

    List<Task> findByAssignedTo(Long assignedTo);

    List<Task> findByCreatedBy(Long createdBy);

    List<Task> findByStatus(Task.TaskStatus status);

    List<Task> findByPriority(Task.TaskPriority priority);

    List<Task> findByProjectIdAndStatus(Long projectId, Task.TaskStatus status);

    List<Task> findByAssignedToAndStatus(Long assignedTo, Task.TaskStatus status);

    @Query("SELECT t FROM Task t WHERE t.title LIKE %:title%")
    List<Task> findByTitleContaining(@Param("title") String title);

    @Query("SELECT t FROM Task t WHERE t.dueDate < :currentDate AND t.status != 'DONE'")
    List<Task> findOverdueTasks(@Param("currentDate") LocalDate currentDate);

    @Query("SELECT t FROM Task t WHERE t.dueDate BETWEEN :startDate AND :endDate")
    List<Task> findTasksByDueDateRange(@Param("startDate") LocalDate startDate, @Param("endDate") LocalDate endDate);

    @Query("SELECT COUNT(t) FROM Task t WHERE t.projectId = :projectId AND t.status = :status")
    Long countTasksByProjectIdAndStatus(@Param("projectId") Long projectId, @Param("status") Task.TaskStatus status);
}