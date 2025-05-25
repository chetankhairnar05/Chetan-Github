package com.natche.ToDoComplex.mapper;

import com.natche.ToDoComplex.dto.TaskDTO;
import com.natche.ToDoComplex.entity.Category;
import com.natche.ToDoComplex.entity.Task;


public class TaskMapper {

    public static TaskDTO toDTO(Task task) {
        TaskDTO dto = new TaskDTO();
        dto.setId(task.getId());
        dto.setTitle(task.getTitle());
        dto.setDescription(task.getDescription());
        dto.setStatus(task.getStatus());
        dto.setCreated(task.getCreated());
        dto.setDue(task.getDue());
        dto.setStarted(task.getStarted());
        dto.setEnded(task.getEnded());

        if (task.getCategory() != null) {
            dto.setCategoryId(task.getCategory().getId());
            dto.setCategoryName(task.getCategory().getName());
        }

        if (task.getEnded() != null && task.getDue() != null) {
            dto.setLate(task.getEnded().isAfter(task.getDue()));
        } else {
            dto.setLate(false);
        }

        return dto;
    }

    public static Task toEntity(TaskDTO dto, Category category) {
        Task task = new Task();
        task.setId(dto.getId());
        task.setTitle(dto.getTitle());
        task.setDescription(dto.getDescription());
        task.setStatus(dto.getStatus());
        task.setCreated(dto.getCreated());
        task.setDue(dto.getDue());
        task.setStarted(dto.getStarted());
        task.setEnded(dto.getEnded());
        task.setCategory(category);
        return task;
    }
}

