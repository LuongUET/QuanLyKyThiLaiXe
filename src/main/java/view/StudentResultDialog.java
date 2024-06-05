package view;

import entity.Student;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.List;

public class StudentResultDialog extends JDialog {
    private DefaultTableModel tableModel;

    public StudentResultDialog(Frame owner, String title, List<Student> students) {
        super(owner, title, true);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        JPanel panel = new JPanel(new BorderLayout());
        String[] columnNames = {"Họ và tên", "CCCD", "Loại bằng", "Ngày thi", "Kết quả"};
        tableModel = new DefaultTableModel(columnNames, 0);
        JTable studentTable = new JTable(tableModel);

        updateTable(students);

        JScrollPane scrollPane = new JScrollPane(studentTable);
        panel.add(scrollPane, BorderLayout.CENTER);

        JButton closeButton = new JButton("Đóng");
        closeButton.addActionListener(e -> dispose());

        panel.add(closeButton, BorderLayout.SOUTH);

        add(panel);
        pack();
        setLocationRelativeTo(owner);
    }

    private void updateTable(List<Student> students) {
        tableModel.setRowCount(0);
        for (Student student : students) {
            Object[] row = {student.getName(), student.getCccd(), student.getRegisteredLicenseType(), student.getExamDate(), student.getResult()};
            tableModel.addRow(row);
        }
    }
}
