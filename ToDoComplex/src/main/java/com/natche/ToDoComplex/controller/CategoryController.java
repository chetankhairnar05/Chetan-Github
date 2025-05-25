package com.natche.ToDoComplex.controller;

import org.springframework.web.bind.annotation.RestController;

import com.natche.ToDoComplex.dto.BulkCategoryCreateResponse;
import com.natche.ToDoComplex.dto.CategoryDTO;
import com.natche.ToDoComplex.service.CategoryService;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;




@RestController
@RequestMapping("/api/category")

public class CategoryController {
    @Autowired
    private CategoryService categoryService;

    @GetMapping("/getR")
    public String getMethodName() {
        return new String("Hi this is something");
    }
    

    @PostMapping()
    public ResponseEntity<CategoryDTO> createCategory(@RequestBody CategoryDTO categoryDTO){
        return new ResponseEntity<>(categoryService.createCategory(categoryDTO),HttpStatus.CREATED);
    }

    @GetMapping()
    public ResponseEntity<List<CategoryDTO>> getAllCategories() {
        return new ResponseEntity<>(categoryService.getAllCategories(),HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<CategoryDTO> getCategoryById(@PathVariable Long id){
        return new ResponseEntity<>(categoryService.getCategoryById(id),HttpStatus.OK);
    }
    
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteCategory(@PathVariable Long id){
        return ResponseEntity.ok(categoryService.deleteCategoryById(id));
    }

    @PostMapping("/createMultiple")
    public ResponseEntity<BulkCategoryCreateResponse> createMultipleCategories(@RequestBody List<CategoryDTO> list){
        return ResponseEntity.ok(categoryService.createMultipleCategories(list));
    }

    @PatchMapping("/{id}")
    public ResponseEntity<CategoryDTO> patchCategory(@PathVariable Long id, @RequestBody Map<String,Object>updates) {
        CategoryDTO updatedCategory = categoryService.patchCategory(id,updates);
        return ResponseEntity.ok(updatedCategory);
    }
}
