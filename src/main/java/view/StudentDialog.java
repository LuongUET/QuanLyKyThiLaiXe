package view;

import entity.Student;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class StudentDialog extends JDialog {
    private JTextField txtName, txtCccd, txtRegisteredLicenseType, txtExamDate, txtResult;
    private Student student;

    public StudentDialog(Frame owner, String title, Student student) {
        super(owner, title, true);
        this.student = student;

        JPanel panel = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5);
        gbc.fill = GridBagConstraints.HORIZONTAL;

        gbc.gridx = 0;
        gbc.gridy = 0;
        panel.add(new JLabel("Họ và tên:"), gbc);
        gbc.gridx = 1;
        txtName = new JTextField(20);
        panel.add(txtName, gbc);

        gbc.gridx = 0;
        gbc.gridy = 1;
        panel.add(new JLabel("CCCD:"), gbc);
        gbc.gridx = 1;
        txtCccd = new JTextField(20);
        panel.add(txtCccd, gbc);

        gbc.gridx = 0;
        gbc.gridy = 2;
        panel.add(new JLabel("Loại bằng đăng ký thi:"), gbc);
        gbc.gridx = 1;
        txtRegisteredLicenseType = new JTextField(20);
        panel.add(txtRegisteredLicenseType, gbc);

        gbc.gridx = 0;
        gbc.gridy = 3;
        panel.add(new JLabel("Ngày thi:"), gbc);
        gbc.gridx = 1;
        txtExamDate = new JTextField(20);
        panel.add(txtExamDate, gbc);

        gbc.gridx = 0;
        gbc.gridy = 4;
        panel.add(new JLabel("Kết quả:"), gbc);
        gbc.gridx = 1;
        txtResult = new JTextField(20);
        panel.add(txtResult, gbc);

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
        panel.add(buttonPanel, gbc);

        add(panel);

        if (student != null) {
            txtName.setText(student.getName());
            txtCccd.setText(student.getCccd());
            txtRegisteredLicenseType.setText(student.getRegisteredLicenseType());
            txtExamDate.setText(student.getExamDate());
            txtResult.setText(student.getResult());
        }

        pack();
        setLocationRelativeTo(owner);
    }

    private void onOK() {
        String name = txtName.getText();
        String cccd = txtCccd.getText();
        String registeredLicenseType = txtRegisteredLicenseType.getText();
        String examDate = txtExamDate.getText();
        String result = txtResult.getText();

        student = new Student(name, cccd, registeredLicenseType, examDate, result);
        dispose();
    }

    private void onCancel() {
        student = null;
        dispose();
    }

    public Student getStudent() {
        return student;
    }
}
