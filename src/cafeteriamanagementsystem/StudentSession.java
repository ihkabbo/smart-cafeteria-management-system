package cafeteriamanagementsystem;

public class StudentSession {

    private static User currentUser;
    private static Student currentStudent;

    // Set current logged-in user
    public static void setCurrentUser(User user) {
        currentUser = user;
    }

    // Get current logged-in user
    public static User getCurrentUser() {
        return currentUser;
    }

    // Set current logged-in student
    public static void setCurrentStudent(Student student) {
        currentStudent = student;
    }

    // Get current logged-in student
    public static Student getCurrentStudent() {
        return currentStudent;
    }

    // Get current student's ID
    public static String getStudentId() {
        if (currentStudent != null) {
            return currentStudent.getStudentId();
        }

        return null;
    }

    // Clear logged-in session
    public static void clearSession() {
        currentUser = null;
        currentStudent = null;
    }
}