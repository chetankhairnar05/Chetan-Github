package com.natche.ToDoComplex.mapper;

import java.util.ArrayList;
import java.util.List;

import com.natche.ToDoComplex.dto.CategoryDTO;
import com.natche.ToDoComplex.dto.TaskDTO;
import com.natche.ToDoComplex.entity.Category;
import com.natche.ToDoComplex.entity.Task;


public class CategoryMapper {

    public static CategoryDTO toDTO(Category category) {
        CategoryDTO dto = new CategoryDTO();
        dto.setId(category.getId());
        dto.setName(category.getName());

        List<TaskDTO> taskDTOs = new ArrayList<>();
        if (category.getTasks() != null) {
            for (Task task : category.getTasks()) {
                taskDTOs.add(TaskMapper.toDTO(task));
            }
        }
        dto.setTasks(taskDTOs);
        return dto;
    }

    public static Category toEntity(CategoryDTO dto) {
        Category category = new Category();
        category.setId(dto.getId());
        category.setName(dto.getName());
        // category.setUser(user);

        List<Task> tasks = new ArrayList<>();
        if (dto.getTasks() != null) {
            for (TaskDTO taskDTO : dto.getTasks()) {
                tasks.add(TaskMapper.toEntity(taskDTO, category));
            }
        }
        category.setTasks(tasks);

        return category;
    }
}
