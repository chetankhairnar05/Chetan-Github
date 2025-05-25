package com.natche.ToDoComplex.dto;

import java.util.List;

import lombok.Data;

@Data
public class BulkCategoryCreateResponse {
    private List<CategoryDTO> created;
    private List<String> failed; // e.g. category names that already exist
}