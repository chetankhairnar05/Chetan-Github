package com.natche.ToDoComplex.controller;

import org.springframework.web.bind.annotation.RestController;

import com.natche.ToDoComplex.dto.BulkTaskCreateResponse;
import com.natche.ToDoComplex.dto.TaskDTO;
import com.natche.ToDoComplex.service.TaskService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;



@RestController
@RequestMapping("/api/task")
// @CrossOrigin(origins = "http://127.0.0.1:5500")
@Tag(name = "task", description = "APIs for managing tasks")

public class TaskController {
    @Autowired
    private TaskService taskService;

    @GetMapping
    @Operation(summary = "get all tasks")
    public ResponseEntity<List<TaskDTO>> getAllTasks() {
        List<TaskDTO> tasks = taskService.getAllTasks();
        return ResponseEntity.ok(tasks);
    }

    @GetMapping("/{id}")
    @Operation(summary = "Get task by id")
    public ResponseEntity<TaskDTO> getTaskById(@PathVariable Long id) {
        TaskDTO task = taskService.getTaskById(id);
        return ResponseEntity.ok(task);
    }

    @PostMapping
    @Operation(summary ="create new task")
    public ResponseEntity<TaskDTO>createTask(@RequestBody TaskDTO taskDTO){
        TaskDTO task = taskService.createTask(taskDTO);
        return ResponseEntity.ok(task);
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "delete task by id")
    public ResponseEntity<String>deleteTask(@PathVariable Long id){
        String s = taskService.deleteTaskById(id);
        return ResponseEntity.ok(s);
    }

    @PostMapping("/createMultiple")
    @Operation(summary = "create multiple tasks")
    public ResponseEntity<BulkTaskCreateResponse>createMultipleTasks(@RequestBody List<TaskDTO>list){
        BulkTaskCreateResponse bulkTaskCreateResponse = taskService.createMultipleTasks(list);
        return ResponseEntity.ok(bulkTaskCreateResponse);
    }

    @PatchMapping("/{id}")
    @Operation(summary = "update task partially")
    public ResponseEntity<TaskDTO> patchTask(@PathVariable Long id,@RequestBody Map<String,Object>updates){
        TaskDTO taskDTO = taskService.patchTask(id,updates);
        return ResponseEntity.ok(taskDTO);
    }
}
