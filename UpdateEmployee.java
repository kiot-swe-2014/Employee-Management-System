

package employee_management_system;

import com.toedter.calendar.JDateChooser;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.*;
import java.util.Date;

public class UpdateEmployee extends JFrame implements ActionListener {

    JTextField tfname, tffname, tfsalary, tfaddress, tfphone, tfemail, tfdesignation;
    JComboBox<String> tfeducation;  // Changed to JComboBox
    JButton updateButton, backButton;
    JDateChooser dcdob;
    String empId;

    public UpdateEmployee(String empId) {
        this.empId = empId;

        // Frame setup
        setLayout(null);
        setTitle("Update Employee Details");
        setBounds(300, 50, 900, 700);
        getContentPane().setBackground(Color.WHITE);

        JLabel heading = new JLabel("Update Employee Details");
        heading.setBounds(320, 30, 500, 50);
        heading.setFont(new Font("Raleway", Font.BOLD, 25));
        add(heading);

        // Form Fields
        JLabel lblname = new JLabel("Name:");
        lblname.setBounds(50, 150, 150, 30);
        lblname.setFont(new Font("Serif", Font.PLAIN, 20));
        add(lblname);

        tfname = new JTextField();
        tfname.setBounds(200, 150, 200, 30);
        add(tfname);

        JLabel lblfname = new JLabel("Father's Name:");
        lblfname.setBounds(450, 150, 150, 30);
        lblfname.setFont(new Font("Serif", Font.PLAIN, 20));
        add(lblfname);

        tffname = new JTextField();
        tffname.setBounds(650, 150, 200, 30);
        add(tffname);

        JLabel lbldob = new JLabel("Date of Birth:");
        lbldob.setBounds(50, 200, 150, 30);
        lbldob.setFont(new Font("Serif", Font.PLAIN, 20));
        add(lbldob);

        dcdob = new JDateChooser();
        dcdob.setBounds(200, 200, 200, 30);
        add(dcdob);

        JLabel lblsalary = new JLabel("Salary:");
        lblsalary.setBounds(450, 200, 150, 30);
        lblsalary.setFont(new Font("Serif", Font.PLAIN, 20));
        add(lblsalary);

        tfsalary = new JTextField();
        tfsalary.setBounds(650, 200, 200, 30);
        add(tfsalary);

        JLabel lbladdress = new JLabel("Address:");
        lbladdress.setBounds(50, 250, 150, 30);
        lbladdress.setFont(new Font("Serif", Font.PLAIN, 20));
        add(lbladdress);

        tfaddress = new JTextField();
        tfaddress.setBounds(200, 250, 200, 30);
        add(tfaddress);

        JLabel lblphone = new JLabel("Phone:");
        lblphone.setBounds(450, 250, 150, 30);
        lblphone.setFont(new Font("Serif", Font.PLAIN, 20));
        add(lblphone);

        tfphone = new JTextField();
        tfphone.setBounds(650, 250, 200, 30);
        add(tfphone);

        JLabel lblemail = new JLabel("Email:");
        lblemail.setBounds(50, 300, 150, 30);
        lblemail.setFont(new Font("Serif", Font.PLAIN, 20));
        add(lblemail);

        tfemail = new JTextField();
        tfemail.setBounds(200, 300, 200, 30);
        add(tfemail);

        JLabel lbleducation = new JLabel("Highest Education:");
        lbleducation.setBounds(450, 300, 150, 30);
        lbleducation.setFont(new Font("Serif", Font.PLAIN, 20));
        add(lbleducation);
        
        String[] courses = {"BBA", "BCA", "BA", "BSC", "B.COM", "BTech", "MBA", "MCA", "MA", "MTech", "MSC", "PHD"};
        
        // Create JComboBox and populate it with courses
        tfeducation = new JComboBox<>(courses);
        tfeducation.setBounds(650, 300, 200, 30);
        add(tfeducation);

        JLabel lbldesignation = new JLabel("Designation:");
        lbldesignation.setBounds(50, 350, 150, 30);
        lbldesignation.setFont(new Font("Serif", Font.PLAIN, 20));
        add(lbldesignation);

        tfdesignation = new JTextField();
        tfdesignation.setBounds(200, 350, 200, 30);
        add(tfdesignation);

        // Buttons
        updateButton = new JButton("Update Details");
        updateButton.setBounds(200, 500, 200, 50);
        updateButton.setBackground(Color.BLACK);
        updateButton.setForeground(Color.WHITE);
        updateButton.setFont(new Font("Serif", Font.PLAIN, 20));
        updateButton.addActionListener(this);
        add(updateButton);

        backButton = new JButton("Back");
        backButton.setBounds(450, 500, 200, 50);
        backButton.setBackground(Color.BLACK);
        backButton.setForeground(Color.WHITE);
        backButton.setFont(new Font("Serif", Font.PLAIN, 20));
        backButton.addActionListener(this);
        add(backButton);

        // Fetch existing data
        try {
            conn c = new conn();
            String query = "SELECT * FROM employee WHERE empId=?";
            PreparedStatement ps = c.c.prepareStatement(query);
            ps.setString(1, empId);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                tfname.setText(rs.getString("name"));
                tffname.setText(rs.getString("fname"));
                dcdob.setDate(rs.getDate("dob"));
                tfsalary.setText(rs.getString("salary"));
                tfaddress.setText(rs.getString("address"));
                tfphone.setText(rs.getString("phone"));
                tfemail.setText(rs.getString("email"));
                tfeducation.setSelectedItem(rs.getString("education"));  // Use setSelectedItem instead of setText
                tfdesignation.setText(rs.getString("designation"));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        setVisible(true);
    }

    public void actionPerformed(ActionEvent ae) {
        if (ae.getSource() == updateButton) {
            try {
                String name = tfname.getText();
                String fname = tffname.getText();
                String salary = tfsalary.getText();
                String address = tfaddress.getText();
                String phone = tfphone.getText();
                String email = tfemail.getText();
                String education = (String) tfeducation.getSelectedItem();  // Get the selected item from JComboBox
                String designation = tfdesignation.getText();
                Date date = dcdob.getDate();
                java.sql.Date sqlDate = (date != null) ? new java.sql.Date(date.getTime()) : null;

                // Validation Logic
                if (sqlDate == null || salary.isEmpty() || !salary.matches("\\d+") || !phone.matches("\\d{10}")) {
                    JOptionPane.showMessageDialog(null, "Validation Failed! Please correct the fields.");
                    return;
                }

                conn c = new conn();
                String query = "UPDATE employee SET name=?, fname=?, dob=?, salary=?, address=?, phone=?, email=?, education=?, designation=? WHERE empId=?";
                PreparedStatement ps = c.c.prepareStatement(query);
                ps.setString(1, name);
                ps.setString(2, fname);
                ps.setDate(3, sqlDate);
                ps.setString(4, salary);
                ps.setString(5, address);
                ps.setString(6, phone);
                ps.setString(7, email);
                ps.setString(8, education);
                ps.setString(9, designation);
                ps.setString(10, empId);

                int result = ps.executeUpdate();
                if (result > 0) {
                    JOptionPane.showMessageDialog(null, "Details Updated Successfully!");
                    setVisible(false);
                    new Home();
                } else {
                    JOptionPane.showMessageDialog(null, "Failed to Update Details!");
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        } else if (ae.getSource() == backButton) {
            int confirmation = JOptionPane.showConfirmDialog(
                null, 
                "Are you sure you want to go back? Unsaved changes will be lost.", 
                "Confirm Back", 
                JOptionPane.YES_NO_OPTION
            );
            if (confirmation == JOptionPane.YES_OPTION) {
                setVisible(false);
                new Home();
            }
        }
    }

    public static void main(String[] args) {
        new UpdateEmployee("empId"); // Pass Employee ID for testing
    }
}
