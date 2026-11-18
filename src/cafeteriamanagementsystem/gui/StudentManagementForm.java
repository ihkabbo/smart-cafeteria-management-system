package cafeteriamanagementsystem.gui;

import cafeteriamanagementsystem.Student;
import cafeteriamanagementsystem.service.StudentService;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.List;

public class StudentManagementForm extends JFrame {

    private JTable studentTable;
    private DefaultTableModel tableModel;

    private JButton deleteButton;
    private JButton refreshButton;
    private JButton backButton;

    private String selectedStudentId = null;

    public StudentManagementForm() {

        setTitle("Student Management");
        setSize(750, 500);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLayout(null);

        // =========================
        // TITLE
        // =========================

        JLabel title = new JLabel("Student Management");

        title.setFont(new Font("Arial", Font.BOLD, 20));
        title.setBounds(270, 20, 250, 30);

        add(title);

        // =========================
        // DELETE BUTTON
        // =========================

        deleteButton = new JButton("Delete");

        deleteButton.setBounds(180, 70, 110, 30);

        add(deleteButton);

        // =========================
        // REFRESH BUTTON
        // =========================

        refreshButton = new JButton("Refresh");

        refreshButton.setBounds(320, 70, 110, 30);

        add(refreshButton);

        // =========================
        // BACK BUTTON
        // =========================

        backButton = new JButton("Back");

        backButton.setBounds(460, 70, 110, 30);

        add(backButton);

        // =========================
        // TABLE
        // =========================

        tableModel = new DefaultTableModel();

        tableModel.addColumn("Student ID");
        tableModel.addColumn("User ID");
        tableModel.addColumn("Department");
        tableModel.addColumn("Batch");

        studentTable = new JTable(tableModel);

        JScrollPane scrollPane = new JScrollPane(studentTable);

        scrollPane.setBounds(40, 130, 660, 280);

        add(scrollPane);

        // =========================
        // LOAD STUDENTS
        // =========================

        loadStudentTable();

        // =========================
        // TABLE SELECTION
        // =========================

        studentTable.getSelectionModel().addListSelectionListener(e -> {

            if (!e.getValueIsAdjusting()) {

                int row = studentTable.getSelectedRow();

                if (row != -1) {

                    selectedStudentId =
                            tableModel.getValueAt(row, 0).toString();
                }
            }
        });

        // =========================
        // DELETE ACTION
        // =========================

        deleteButton.addActionListener(e -> {

            if (selectedStudentId == null) {

                JOptionPane.showMessageDialog(
                        this,
                        "Please select a student first."
                );

                return;
            }

            int option = JOptionPane.showConfirmDialog(
                    this,
                    "Are you sure you want to delete this student?",
                    "Confirm Delete",
                    JOptionPane.YES_NO_OPTION
            );

            if (option == JOptionPane.YES_OPTION) {

                StudentService service =
                        new StudentService();

                if (service.deleteStudent(selectedStudentId)) {

                    JOptionPane.showMessageDialog(
                            this,
                            "Student Deleted Successfully!"
                    );

                    selectedStudentId = null;

                    loadStudentTable();

                } else {

                    JOptionPane.showMessageDialog(
                            this,
                            "Delete Failed!"
                    );
                }
            }
        });

        // =========================
        // REFRESH ACTION
        // =========================

        refreshButton.addActionListener(e -> {

            selectedStudentId = null;

            loadStudentTable();

        });

        // =========================
        // BACK ACTION
        // =========================

        backButton.addActionListener(e -> {

            dispose();

            new AdminDashboard();

        });

        CafeteriaTheme.apply(this);

        setVisible(true);
    }

    // =====================================================
    // LOAD STUDENT TABLE
    // =====================================================

    private void loadStudentTable() {

        tableModel.setRowCount(0);

        StudentService service =
                new StudentService();

        List<Student> students =
                service.getAllStudents();

        for (Student student : students) {

            tableModel.addRow(new Object[]{

                    student.getStudentId(),
                    student.getUserId(),
                    student.getDepartment(),
                    student.getBatch()

            });
        }
    }
}