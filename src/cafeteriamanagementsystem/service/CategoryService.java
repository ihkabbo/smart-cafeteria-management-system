package cafeteriamanagementsystem.service;

import cafeteriamanagementsystem.Category;
import cafeteriamanagementsystem.dao.CategoryDAO;

import java.util.ArrayList;

public class CategoryService {

    private CategoryDAO categoryDAO;

    public CategoryService() {
        categoryDAO = new CategoryDAO();
    }

    // Add Category
    public boolean addCategory(Category category) {

        if (category.getCategoryName() == null ||
            category.getCategoryName().trim().isEmpty()) {

            return false;
        }

        return categoryDAO.addCategory(category);
    }

    // Get All Categories
    public ArrayList<Category> getAllCategories() {

        return categoryDAO.getAllCategories();
    }

    // Get Category By ID
    public Category getCategoryById(int categoryId) {

        return categoryDAO.getCategoryById(categoryId);
    }

    // Update Category
    public boolean updateCategory(Category category) {

        if (category.getCategoryName() == null ||
            category.getCategoryName().trim().isEmpty()) {

            return false;
        }

        return categoryDAO.updateCategory(category);
    }

    // Delete Category
    public boolean deleteCategory(int categoryId) {

        return categoryDAO.deleteCategory(categoryId);
    }

}