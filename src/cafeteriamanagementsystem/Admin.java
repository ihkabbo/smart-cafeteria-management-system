package cafeteriamanagementsystem;

public class Admin extends User {

    // Admin specific attributes
    private String adminId;
    private String designation;
    private String accessLevel;

    // Default Constructor
    public Admin() {
        super();
    }

    // Parameterized Constructor
    public Admin(int userId, String name, String email, String phone,
                 String password, String role,
                 String adminId, String designation, 
                 String accessLevel) {

        super(userId, name, email, phone, password, role);

        this.adminId = adminId;
        this.designation = designation;
        this.accessLevel = accessLevel;
    }

    // Getters and Setters

    public String getAdminId() {
        return adminId;
    }

    public void setAdminId(String adminId) {
        this.adminId = adminId;
    }

    public String getDesignation() {
        return designation;
    }

    public void setDesignation(String designation) {
        this.designation = designation;
    }

    public String getAccessLevel() {
        return accessLevel;
    }

    public void setAccessLevel(String accessLevel) {
        this.accessLevel = accessLevel;
    }

    // Display admin information
    public void displayAdminInfo() {
        displayInfo();
        System.out.println("Admin ID: " + adminId);
        System.out.println("Designation: " + designation);
        System.out.println("Access Level: " + accessLevel);
    }
}