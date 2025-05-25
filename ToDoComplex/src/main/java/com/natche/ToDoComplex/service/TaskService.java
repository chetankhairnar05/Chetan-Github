package com.natche.ToDoComplex.service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.natche.ToDoComplex.dto.BulkTaskCreateResponse;
import com.natche.ToDoComplex.dto.TaskDTO;
import com.natche.ToDoComplex.entity.Category;
import com.natche.ToDoComplex.entity.Task;
import com.natche.ToDoComplex.enums.TaskStatus;
import com.natche.ToDoComplex.exception.ResourceNotFoundException;
import com.natche.ToDoComplex.exception.ResourseAlreadyExistsException;
import com.natche.ToDoComplex.mapper.TaskMapper;
import com.natche.ToDoComplex.repository.CategoryRepository;
import com.natche.ToDoComplex.repository.TaskRepository;
import com.natche.ToDoComplex.utils.TaskTimestampUtil;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Service
@AllArgsConstructor
@NoArgsConstructor
@Data
public class TaskService {
    @Autowired
    private TaskRepository taskRepository;
    @Autowired
    private CategoryRepository categoryRepository;


    public List<TaskDTO> getAllTasks() {
        List<Task> tasks = taskRepository.findAll();
        return tasks.stream().map(TaskMapper::toDTO).toList();
    }

    public TaskDTO getTaskById(Long id) {
        Task task = taskRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Task not found with id: " + id));
        return TaskMapper.toDTO(task);
    }

    public TaskDTO createTask(TaskDTO taskDTO) {
        Optional<Category> categoryOptional = categoryRepository.findById(taskDTO.getCategoryId());
        if (!categoryOptional.isPresent()) {
            throw new ResourceNotFoundException("category with id not exists: " + taskDTO.getCategoryId());
        }
        boolean exist = taskRepository.findByTitleAndCategoryId(taskDTO.getTitle(), taskDTO.getCategoryId()).isPresent();
        if (exist) {
            throw new ResourseAlreadyExistsException("task already exist with title: " + taskDTO.getTitle());
        }

        Task task = TaskMapper.toEntity(taskDTO, categoryOptional.get());
        TaskTimestampUtil.setTimestampsForCreation(task);
        task = taskRepository.save(task);
        return TaskMapper.toDTO(task);

    }

    public String deleteTaskById(Long id) {
        taskRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Task not found with id: " + id));
        taskRepository.deleteById(id);
        return "Task deleted";
    }

    public BulkTaskCreateResponse createMultipleTasks(List<TaskDTO> list) {
        List<TaskDTO> created = new ArrayList<>();
        List<String> failed = new ArrayList<>();

        for (TaskDTO dto : list) {
            try {
                created.add(createTask(dto));
            } catch (ResourseAlreadyExistsException e) {
                failed.add(dto.getTitle());
            } catch (ResourceNotFoundException e) {
                failed.add(dto.getTitle());
            }
        }
        BulkTaskCreateResponse bulkTaskCreateResponse = new BulkTaskCreateResponse();
        bulkTaskCreateResponse.setCreated(created);
        bulkTaskCreateResponse.setFailed(failed);
        return bulkTaskCreateResponse;

    }

    public TaskDTO patchTask(Long id, Map<String, Object> updates) {
        Task task = taskRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Task not found with id: " + id));

        if (updates.containsKey("categoryId")) {
            Long categoryId = Long.parseLong(updates.get("categoryId").toString());
            Category category = categoryRepository.findById(categoryId)
                    .orElseThrow(() -> new ResourceNotFoundException("Category not found with id: " + categoryId));
            task.setCategory(category);
        }

        if (updates.containsKey("title")) {
            task.setTitle(updates.get("title").toString());
        }

        if (updates.containsKey("description")) {
            task.setDescription(updates.get("description").toString());
        }

        if (updates.containsKey("status")) {
            TaskStatus newStatus = TaskStatus.valueOf(updates.get("status").toString().toUpperCase());
            task.setStatus(newStatus);
        }

        if (updates.containsKey("due")) {
            task.setDue(LocalDateTime.parse(updates.get("due").toString()));
        }
        TaskTimestampUtil.updateTimestampsOnStatusChange(task,task.getStatus());
        
        Task updated = taskRepository.save(task);
        return TaskMapper.toDTO(updated);
    }

}

