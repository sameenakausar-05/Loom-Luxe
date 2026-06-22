package com.loomandluxe.dao;

import java.util.List;

import com.loomandluxe.model.Category;

public interface CategoryDAO {

    boolean addCategory(Category category);

    boolean updateCategory(Category category);

    boolean deleteCategory(int categoryId);

    Category getCategoryById(int categoryId);

    Category getCategoryByName(String categoryName);

    List<Category> getAllCategories();

    boolean isCategoryExists(String categoryName);
}