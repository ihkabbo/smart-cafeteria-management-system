package cafeteriamanagementsystem.dao;

import cafeteriamanagementsystem.DatabaseConnection;
import cafeteriamanagementsystem.Student;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class StudentDAO {

    // =====================================================
    // ADD STUDENT
    // =====================================================

    public boolean addStudent(Student student) {

        String sql =
                "INSERT INTO students " +
                "(student_id, user_id, department, batch) " +
                "VALUES (?, ?, ?, ?)";

        try (Connection con = DatabaseConnection.getConnection();
             PreparedStatement pst = con.prepareStatement(sql)) {

            pst.setString(1, student.getStudentId());
            pst.setInt(2, student.getUserId());
            pst.setString(3, student.getDepartment());
            pst.setString(4, student.getBatch());

            return pst.executeUpdate() > 0;

        } catch (SQLException e) {

            e.printStackTrace();
        }

        return false;
    }


    // =====================================================
    // GET STUDENT BY STUDENT ID
    // =====================================================

    public Student getStudentById(String studentId) {

        String sql =
                "SELECT " +
                "s.student_id, " +
                "s.user_id, " +
                "s.department, " +
                "s.batch, " +
                "u.name, " +
                "u.email, " +
                "u.phone, " +
                "u.password, " +
                "u.role " +
                "FROM students s " +
                "JOIN users u ON s.user_id = u.user_id " +
                "WHERE s.student_id = ?";

        try (Connection con = DatabaseConnection.getConnection();
             PreparedStatement pst = con.prepareStatement(sql)) {

            pst.setString(1, studentId);

            try (ResultSet rs = pst.executeQuery()) {

                if (rs.next()) {

                    Student student = new Student();

                    // Student information
                    student.setStudentId(
                            rs.getString("student_id")
                    );

                    student.setUserId(
                            rs.getInt("user_id")
                    );

                    student.setDepartment(
                            rs.getString("department")
                    );

                    student.setBatch(
                            rs.getString("batch")
                    );


                    // User information
                    student.setName(
                            rs.getString("name")
                    );

                    student.setEmail(
                            rs.getString("email")
                    );

                    student.setPhone(
                            rs.getString("phone")
                    );

                    student.setPassword(
                            rs.getString("password")
                    );

                    student.setRole(
                            rs.getString("role")
                    );

                    return student;
                }
            }

        } catch (SQLException e) {

            e.printStackTrace();
        }

        return null;
    }


    // =====================================================
    // GET ALL STUDENTS
    // =====================================================

    public List<Student> getAllStudents() {

        List<Student> students = new ArrayList<>();

        String sql =
                "SELECT " +
                "s.student_id, " +
                "s.user_id, " +
                "s.department, " +
                "s.batch, " +
                "u.name, " +
                "u.email, " +
                "u.phone, " +
                "u.password, " +
                "u.role " +
                "FROM students s " +
                "JOIN users u ON s.user_id = u.user_id " +
                "ORDER BY s.student_id";

        try (Connection con = DatabaseConnection.getConnection();
             Statement st = con.createStatement();
             ResultSet rs = st.executeQuery(sql)) {

            while (rs.next()) {

                Student student = new Student();


                // Student information
                student.setStudentId(
                        rs.getString("student_id")
                );

                student.setUserId(
                        rs.getInt("user_id")
                );

                student.setDepartment(
                        rs.getString("department")
                );

                student.setBatch(
                        rs.getString("batch")
                );


                // User information
                student.setName(
                        rs.getString("name")
                );

                student.setEmail(
                        rs.getString("email")
                );

                student.setPhone(
                        rs.getString("phone")
                );

                student.setPassword(
                        rs.getString("password")
                );

                student.setRole(
                        rs.getString("role")
                );


                students.add(student);
            }

        } catch (SQLException e) {

            e.printStackTrace();
        }

        return students;
    }


    // =====================================================
    // DELETE STUDENT
    // =====================================================

    public boolean deleteStudent(String studentId) {

        String sql =
                "DELETE FROM students WHERE student_id = ?";

        try (Connection con = DatabaseConnection.getConnection();
             PreparedStatement pst = con.prepareStatement(sql)) {

            pst.setString(1, studentId);

            return pst.executeUpdate() > 0;

        } catch (SQLException e) {

            e.printStackTrace();
        }

        return false;
    }
}