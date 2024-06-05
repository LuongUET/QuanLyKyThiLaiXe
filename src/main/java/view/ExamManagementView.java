package view;

import entity.Exam;
import func.ExamManager;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
import java.util.Map;

public class ExamManagementView extends JFrame {
    private ExamManager examManager;
    private JTable examTable;
    private DefaultTableModel tableModel;

    public ExamManagementView(ExamManager examManager) {
        this.examManager = examManager;

        setTitle("Quản lý kỳ thi");
        setSize(800, 600);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);

        setupUI();
    }

    private void setupUI() {
        JPanel panel = new JPanel(new BorderLayout());

        String[] columnNames = {"Ngày thi", "Địa điểm", "Số lượng xe", "Loại bằng", "Hình thức thi"};
        tableModel = new DefaultTableModel(columnNames, 0);
        examTable = new JTable(tableModel);

        updateTable();

        JScrollPane scrollPane = new JScrollPane(examTable);
        panel.add(scrollPane, BorderLayout.CENTER);

        JPanel controlPanel = new JPanel();
        JButton btnAdd = new JButton("Thêm");
        JButton btnEdit = new JButton("Sửa");
        JButton btnDelete = new JButton("Xóa");

        JLabel lblSearch = new JLabel("Tìm kiếm theo ngày:");

        JTextField txtSearch = new JTextField();
        txtSearch.setPreferredSize(new Dimension(150, 25));

        JButton btnSearch = new JButton("Tìm kiếm");

        JButton btnStatistic = new JButton("Thống kê");

        // Xử lý sự kiện thống kê
        btnStatistic.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                statisticVehicleCountByLicenseType();
            }
        });

        btnAdd.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                addExam();
            }
        });

        btnEdit.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                editExam();
            }
        });

        btnDelete.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                deleteExam();
            }
        });

        btnSearch.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String date = txtSearch.getText();
                if (!date.isEmpty()) {
                    searchByDate(date);
                } else {
                    updateTable(); // Nếu không có ngày được nhập, hiển thị lại toàn bộ dữ liệu
                }
            }
        });

        controlPanel.add(lblSearch);
        controlPanel.add(txtSearch);
        controlPanel.add(btnSearch);
        controlPanel.add(btnStatistic);
        controlPanel.add(btnAdd);
        controlPanel.add(btnEdit);
        controlPanel.add(btnDelete);

        panel.add(controlPanel, BorderLayout.SOUTH);

        add(panel);
    }

    private void statisticVehicleCountByLicenseType() {
        Map<String, Integer> statistics = examManager.statisticVehicleCountByLicenseType();
        StringBuilder message = new StringBuilder("Thống kê số lượng xe theo loại bằng:\n");
        for (Map.Entry<String, Integer> entry : statistics.entrySet()) {
            message.append(entry.getKey()).append(": ").append(entry.getValue()).append("\n");
        }
        JOptionPane.showMessageDialog(null, message.toString(), "Thống kê", JOptionPane.INFORMATION_MESSAGE);
    }

    private void searchByDate(String date) {
        List<Exam> results = examManager.searchByDate(date);
        if (!results.isEmpty()) {
            ExamResultDialog resultDialog = new ExamResultDialog(this, "Kết quả tìm kiếm", results);
            resultDialog.setVisible(true);
        } else {
            JOptionPane.showMessageDialog(null, "Không tìm thấy kỳ thi vào ngày " + date, "Kết quả tìm kiếm", JOptionPane.INFORMATION_MESSAGE);
        }
    }


    private void updateTable() {
        tableModel.setRowCount(0);
        for (Exam exam : examManager.getExams()) {
            Object[] row = {exam.getDate(), exam.getLocation(), exam.getVehicleCount(), exam.getLicenseType(), exam.getExamForm()};
            tableModel.addRow(row);
        }
    }

    private void addExam() {
        ExamDialog dialog = new ExamDialog(this, "Thêm kỳ thi mới", null);
        dialog.setVisible(true);
        Exam newExam = dialog.getExam();
        if (newExam != null) {

            examManager.addExam(newExam);
            updateTable();
        }
    }

    private void editExam() {
        int selectedRow = examTable.getSelectedRow();
        if (selectedRow >= 0) {
            Exam existingExam = examManager.getExams().get(selectedRow);
            ExamDialog dialog = new ExamDialog(this, "Sửa kỳ thi", existingExam);
            dialog.setVisible(true);
            Exam updatedExam = dialog.getExam();
            if (updatedExam != null) {
                examManager.updateExam(selectedRow, updatedExam);
                updateTable();
            }
        } else {
            JOptionPane.showMessageDialog(this, "Vui lòng chọn kỳ thi để sửa");
        }
    }

    private void deleteExam() {
        int selectedRow = examTable.getSelectedRow();
        if (selectedRow >= 0) {
            examManager.deleteExam(selectedRow);
            updateTable();
        } else {
            JOptionPane.showMessageDialog(this, "Vui lòng chọn kỳ thi để xóa");
        }
    }
}
