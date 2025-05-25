package com.natche.ToDoComplex.dto;

import java.time.LocalDateTime;

import com.natche.ToDoComplex.enums.TaskStatus;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class TaskDTO {
    private Long id;
    private String title;
    private String description;
    private TaskStatus status;
    private LocalDateTime created;
    private LocalDateTime due;
    private LocalDateTime started;
    private LocalDateTime ended;
    private Long categoryId;
    private String categoryName;
    private boolean isLate;
}


