package view;

import entity.Exam;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ExamDialog extends JDialog {
    private JTextField txtDate, txtLocation, txtVehicleCount, txtLicenseType, txtExamForm;
    private Exam exam;

    public ExamDialog(Frame owner, String title, Exam exam) {
        super(owner, title, true);
        this.exam = exam;

        setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);
        gbc.fill = GridBagConstraints.HORIZONTAL;

        // Ngày thi
        gbc.gridx = 0;
        gbc.gridy = 0;
        add(new JLabel("Ngày thi:"), gbc);
        gbc.gridx = 1;
        txtDate = new JTextField(20);
        add(txtDate, gbc);

        // Địa điểm
        gbc.gridx = 0;
        gbc.gridy = 1;
        add(new JLabel("Địa điểm:"), gbc);
        gbc.gridx = 1;
        txtLocation = new JTextField(20);
        add(txtLocation, gbc);

        // Số lượng xe
        gbc.gridx = 0;
        gbc.gridy = 2;
        add(new JLabel("Số lượng xe:"), gbc);
        gbc.gridx = 1;
        txtVehicleCount = new JTextField(20);
        add(txtVehicleCount, gbc);

        // Loại bằng
        gbc.gridx = 0;
        gbc.gridy = 3;
        add(new JLabel("Loại bằng:"), gbc);
        gbc.gridx = 1;
        txtLicenseType = new JTextField(20);
        add(txtLicenseType, gbc);

        // Hình thức thi
        gbc.gridx = 0;
        gbc.gridy = 4;
        add(new JLabel("Hình thức thi:"), gbc);
        gbc.gridx = 1;
        txtExamForm = new JTextField(20);
        add(txtExamForm, gbc);

        // Buttons
        gbc.gridx = 0;
        gbc.gridy = 5;
        gbc.gridwidth = 2;
        gbc.anchor = GridBagConstraints.CENTER;
        JPanel buttonPanel = new JPanel();
        JButton btnOk = new JButton("OK");
        btnOk.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                onOK();
            }
        });
        buttonPanel.add(btnOk);

        JButton btnCancel = new JButton("Hủy");
        btnCancel.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                onCancel();
            }
        });
        buttonPanel.add(btnCancel);
        add(buttonPanel, gbc);

        if (exam != null) {
            txtDate.setText(exam.getDate());
            txtLocation.setText(exam.getLocation());
            txtVehicleCount.setText(String.valueOf(exam.getVehicleCount()));
            txtLicenseType.setText(exam.getLicenseType());
            txtExamForm.setText(exam.getExamForm());
        }

        setPreferredSize(new Dimension(400, 400));
        pack();
        setLocationRelativeTo(owner);
    }

    private void onOK() {
        String date = txtDate.getText();
        String location = txtLocation.getText();
        int vehicleCount = Integer.parseInt(txtVehicleCount.getText());
        String licenseType = txtLicenseType.getText();
        String examForm = txtExamForm.getText();

        exam = new Exam(date, location, vehicleCount, licenseType, examForm);
        dispose();
    }

    private void onCancel() {
        exam = null;
        dispose();
    }

    public Exam getExam() {
        return exam;
    }
}
