package StudentManagementSystem;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

public class StudentManagementSystemGUI {
    private StudentManagementSystem sms;
    private JFrame frame;
    private JTextArea textArea;

    public StudentManagementSystemGUI() {
        sms = new StudentManagementSystem();
        frame = new JFrame("Student Management System");
        frame.setSize(600, 400);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(new BorderLayout());

        textArea = new JTextArea();
        textArea.setEditable(false);
        frame.add(new JScrollPane(textArea), BorderLayout.CENTER);

        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(1, 5));

        JButton addButton = new JButton("Add");
        JButton removeButton = new JButton("Remove");
        JButton searchButton = new JButton("Search");
        JButton displayButton = new JButton("Display All");
        JButton updateButton = new JButton("Update");

        panel.add(addButton);
        panel.add(removeButton);
        panel.add(searchButton);
        panel.add(displayButton);
        panel.add(updateButton);

        frame.add(panel, BorderLayout.SOUTH);

        addButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                addStudent();
            }
        });

        removeButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                removeStudent();
            }
        });

        searchButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                searchStudent();
            }
        });

        displayButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                displayAllStudents();
            }
        });

        updateButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                updateStudent();
            }
        });

        frame.setVisible(true);
    }

    private void addStudent() {
        JTextField nameField = new JTextField();
        JTextField rollNumberField = new JTextField();
        JTextField gradeField = new JTextField();

        Object[] message = {
                "Name:", nameField,
                "Roll Number:", rollNumberField,
                "Grade:", gradeField
        };

        int option = JOptionPane.showConfirmDialog(frame, message, "Add Student", JOptionPane.OK_CANCEL_OPTION);
        if (option == JOptionPane.OK_OPTION) {
            String name = nameField.getText();
            int rollNumber = Integer.parseInt(rollNumberField.getText());
            String grade = gradeField.getText();
            sms.addStudent(new Student(name, rollNumber, grade));
            displayAllStudents();
        }
    }

    private void removeStudent() {
        String rollNumberStr = JOptionPane.showInputDialog(frame, "Enter Roll Number of the student to remove:");
        if (rollNumberStr != null) {
            int rollNumber = Integer.parseInt(rollNumberStr);
            sms.removeStudent(rollNumber);
            displayAllStudents();
        }
    }

    private void searchStudent() {
        String rollNumberStr = JOptionPane.showInputDialog(frame, "Enter Roll Number of the student to search:");
        if (rollNumberStr != null) {
            int rollNumber = Integer.parseInt(rollNumberStr);
            Student student = sms.searchStudent(rollNumber);
            if (student != null) {
                JOptionPane.showMessageDialog(frame, student.toString());
            } else {
                JOptionPane.showMessageDialog(frame, "Student not found.");
            }
        }
    }

    private void updateStudent() {
        String rollNumberStr = JOptionPane.showInputDialog(frame, "Enter Roll Number of the student to update:");
        if (rollNumberStr != null) {
            int rollNumber = Integer.parseInt(rollNumberStr);
            JTextField nameField = new JTextField();
            JTextField gradeField = new JTextField();

            Object[] message = {
                    "New Name:", nameField,
                    "New Grade:", gradeField
            };

            int option = JOptionPane.showConfirmDialog(frame, message, "Update Student", JOptionPane.OK_CANCEL_OPTION);
            if (option == JOptionPane.OK_OPTION) {
                String name = nameField.getText();
                String grade = gradeField.getText();
                sms.updateStudent(rollNumber, name, grade);
                displayAllStudents();
            }
        }
    }

    private void displayAllStudents() {
        List<Student> students = sms.getStudents();
        textArea.setText("");
        for (Student student : students) {
            textArea.append("• " + student.toString() + "\n");
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new StudentManagementSystemGUI();
            }
        });
    }
}
