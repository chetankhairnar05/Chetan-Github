package com.natche.ToDoComplex.controller;

import org.springframework.web.bind.annotation.RestController;

import com.natche.ToDoComplex.dto.BulkCategoryCreateResponse;
import com.natche.ToDoComplex.dto.CategoryDTO;
import com.natche.ToDoComplex.service.CategoryService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;




@RestController
@RequestMapping("/api/category")
// @CrossOrigin(origins = "http://127.0.0.1:5500")
@Tag(name = "Category", description = "APIs for managing categories")


public class CategoryController {
    @Autowired
    private CategoryService categoryService;

    

    @PostMapping()
    @Operation(summary = "create a new category")
    public ResponseEntity<CategoryDTO> createCategory(@RequestBody CategoryDTO categoryDTO){
        return new ResponseEntity<>(categoryService.createCategory(categoryDTO),HttpStatus.CREATED);
    }

    @GetMapping()
    @Operation(summary = "Get all categories")
    public ResponseEntity<List<CategoryDTO>> getAllCategories() {
        return new ResponseEntity<>(categoryService.getAllCategories(),HttpStatus.OK);
    }

    @GetMapping("/{id}")
    @Operation(summary = "Get category by id")
    public ResponseEntity<CategoryDTO> getCategoryById(@PathVariable Long id){
        return new ResponseEntity<>(categoryService.getCategoryById(id),HttpStatus.OK);
    }
    
    @DeleteMapping("/{id}")
    @Operation(summary = "delete category by id")
    public ResponseEntity<String> deleteCategory(@PathVariable Long id){
        return ResponseEntity.ok(categoryService.deleteCategoryById(id));
    }

    @PostMapping("/createMultiple")
    @Operation(summary = "create multiple categories")
    public ResponseEntity<BulkCategoryCreateResponse> createMultipleCategories(@RequestBody List<CategoryDTO> list){
        return ResponseEntity.ok(categoryService.createMultipleCategories(list));
    }

    @PatchMapping("/{id}")
    @Operation(summary = "update category name")
    public ResponseEntity<CategoryDTO> patchCategory(@PathVariable Long id, @RequestBody Map<String,Object>updates) {
        CategoryDTO updatedCategory = categoryService.patchCategory(id,updates);
        return ResponseEntity.ok(updatedCategory);
    }
}
