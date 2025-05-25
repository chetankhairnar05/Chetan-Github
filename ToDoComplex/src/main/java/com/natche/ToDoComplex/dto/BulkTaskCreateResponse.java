package com.natche.ToDoComplex.dto;

import java.util.List;

import lombok.Data;

@Data
public class BulkTaskCreateResponse {
    private List<TaskDTO>created ;
    private List<String> failed;
}
