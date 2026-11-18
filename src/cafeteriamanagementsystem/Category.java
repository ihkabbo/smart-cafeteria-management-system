package cafeteriamanagementsystem;

public class Category {

    // Attributes
    private int categoryId;
    private String categoryName;
    private String description;

    // Default Constructor
    public Category() {
    }

    // Parameterized Constructor
    public Category(int categoryId, String categoryName, String description) {
        this.categoryId = categoryId;
        this.categoryName = categoryName;
        this.description = description;
    }

    // Getters and Setters

    public int getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(int categoryId) {
        this.categoryId = categoryId;
    }

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    // Display category information
    public void displayCategoryInfo() {
        System.out.println("Category ID: " + categoryId);
        System.out.println("Category Name: " + categoryName);
        System.out.println("Description: " + description);
    }

    // Display category name in JComboBox
    @Override
    public String toString() {
        return categoryName;
    }
}