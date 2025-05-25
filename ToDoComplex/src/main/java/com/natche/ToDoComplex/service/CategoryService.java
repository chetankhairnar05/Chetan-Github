package com.natche.ToDoComplex.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.natche.ToDoComplex.dto.BulkCategoryCreateResponse;
import com.natche.ToDoComplex.dto.CategoryDTO;
import com.natche.ToDoComplex.entity.Category;
// import com.natche.ToDoComplex.entity.User;
import com.natche.ToDoComplex.exception.ResourceNotFoundException;
import com.natche.ToDoComplex.exception.ResourseAlreadyExistsException;
import com.natche.ToDoComplex.mapper.CategoryMapper;
import com.natche.ToDoComplex.repository.CategoryRepository;
// import com.natche.ToDoComplex.repository.UserRepository;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Service
@AllArgsConstructor
@NoArgsConstructor
@Data
public class CategoryService {
    @Autowired
    private CategoryRepository categoryRepository;
    // @Autowired
    // private UserRepository userRepository;

    public CategoryDTO createCategory(CategoryDTO categoryDTO) {
        // Optional<User> user = userRepository.findById(userId);
        // if (!user.isPresent()) {
        //     throw new ResourceNotFoundException("User not found with id " + userId);
        // }
        // boolean exist = categoryRepository.existsByUserIdAndName(userId,categoryDTO.getName());
        Optional<Category> cate = categoryRepository.findByName(categoryDTO.getName());

        if(cate.isPresent()){
            throw new ResourseAlreadyExistsException("category already exists with category name: "+categoryDTO.getName());
        }
        Category category = CategoryMapper.toEntity(categoryDTO);
        category = categoryRepository.save(category);
        return CategoryMapper.toDTO(category);
    }

    public List<CategoryDTO> getAllCategories() {
        List<Category> categories = categoryRepository.findAll();
        return categories.stream().map(CategoryMapper::toDTO).toList();
    }

    public CategoryDTO getCategoryById(Long id) {
        Category category = categoryRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Category Not exists with id: " + id));
        return CategoryMapper.toDTO(category);
    }

    public String deleteCategoryById(Long id) {
        categoryRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Category Not exists with id: " + id));
        categoryRepository.deleteById(id);
        return "Category Deleted";
    }

    public BulkCategoryCreateResponse createMultipleCategories(List<CategoryDTO> categoryDTOs) {
        List<CategoryDTO> created = new ArrayList<>();
        List<String> failed = new ArrayList<>();

        for (CategoryDTO dto : categoryDTOs) {
            try {
                created.add(createCategory(dto));
            } catch (ResourseAlreadyExistsException ex) {
                failed.add(dto.getName());
            }
        }

        BulkCategoryCreateResponse response = new BulkCategoryCreateResponse();
        response.setCreated(created);
        response.setFailed(failed);
        return response;
    }

    public CategoryDTO patchCategory(Long id, Map<String, Object> updates) {
        Category category = categoryRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Category not found with id: " + id));

        if (updates.containsKey("name")) {
            String newName = updates.get("name").toString();

            // Optional: check if new name already exists
            if (categoryRepository.findByName(newName).isPresent()) {
                throw new ResourseAlreadyExistsException("Category already exists with name: " + newName);
            }
            category.setName(newName);
        }
        Category updated = categoryRepository.save(category);
        return CategoryMapper.toDTO(updated);
    }

}
