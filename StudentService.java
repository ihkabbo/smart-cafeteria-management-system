package cafeteriamanagementsystem.service;

import cafeteriamanagementsystem.Student;
import cafeteriamanagementsystem.dao.StudentDAO;

import java.util.List;

public class StudentService {

    private StudentDAO studentDAO;

    public StudentService() {
        studentDAO = new StudentDAO();
    }

    // =====================================================
    // ADD STUDENT
    // =====================================================

    public boolean addStudent(Student student) {

        if (student == null) {
            return false;
        }

        if (student.getStudentId() == null ||
                student.getStudentId().trim().isEmpty()) {
            return false;
        }

        if (student.getUserId() <= 0) {
            return false;
        }

        if (student.getDepartment() == null ||
                student.getDepartment().trim().isEmpty()) {
            return false;
        }

        if (student.getBatch() == null ||
                student.getBatch().trim().isEmpty()) {
            return false;
        }

        return studentDAO.addStudent(student);
    }


    // =====================================================
    // GET STUDENT BY STUDENT ID
    // =====================================================

    public Student getStudentById(String studentId) {

        if (studentId == null ||
                studentId.trim().isEmpty()) {
            return null;
        }

        return studentDAO.getStudentById(studentId);
    }


    // =====================================================
    // GET ALL STUDENTS
    // =====================================================

    public List<Student> getAllStudents() {

        return studentDAO.getAllStudents();
    }


    // =====================================================
    // DELETE STUDENT
    // =====================================================

    public boolean deleteStudent(String studentId) {

        if (studentId == null ||
                studentId.trim().isEmpty()) {
            return false;
        }

        return studentDAO.deleteStudent(studentId);
    }
}