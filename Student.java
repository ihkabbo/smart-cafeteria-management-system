package cafeteriamanagementsystem;

public class Student extends User {

    private String studentId;
    private String department;
    private String batch;

    public Student() {
        super();
    }

    public Student(int userId,
                   String name,
                   String email,
                   String phone,
                   String password,
                   String role,
                   String studentId,
                   String department,
                   String batch) {

        super(userId, name, email, phone, password, role);

        this.studentId = studentId;
        this.department = department;
        this.batch = batch;
    }

    public String getStudentId() {
        return studentId;
    }

    public void setStudentId(String studentId) {
        this.studentId = studentId;
    }

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

    public String getBatch() {
        return batch;
    }

    public void setBatch(String batch) {
        this.batch = batch;
    }

    public void displayStudentInfo() {

        displayInfo();

        System.out.println("Student ID : " + studentId);
        System.out.println("Department : " + department);
        System.out.println("Batch : " + batch);
    }
}