
package employee_management_system;

import com.toedter.calendar.JDateChooser;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import javax.swing.*;

public class UpdateEmployee extends JFrame implements ActionListener {

    // Global declaration 
    JTextField tfeducation, tffname, tfaddress, tfphone, tfemail, tfsalary, tfdesignation, tfname;
    JDateChooser dcdob;
    JLabel lblempId;
    JButton updateButton, backButton;
    String empId;

    UpdateEmployee(String empId) {
        this.empId = empId;
        getContentPane().setBackground(Color.white);
        setLayout(null);

        JLabel heading = new JLabel("Update Employee Detail");
        heading.setBounds(320, 30, 500, 50);
        heading.setFont(new Font("SAN_SERIF", Font.BOLD, 25));
        add(heading);

        setupFormComponents();  // Setup form components

        // Fetch existing employee details
        fetchEmployeeDetails();

        updateButton = new JButton("Update Details");
        updateButton.setBounds(250, 550, 150, 40);
        updateButton.addActionListener(this);
        updateButton.setBackground(Color.BLACK);
        updateButton.setForeground(Color.white);
        add(updateButton);

        backButton = new JButton("Back");
        backButton.setBounds(450, 550, 150, 40);
        backButton.addActionListener(this);
        backButton.setBackground(Color.BLACK);
        backButton.setForeground(Color.white);
        add(backButton);

        setSize(900, 700);
        setLocation(300, 50);
        setVisible(true);
    }

    private void setupFormComponents() {
        // Labels and TextFields setup
        JLabel labelname = new JLabel("Name");
        labelname.setBounds(50, 150, 150, 30);
        labelname.setFont(new Font("serif", Font.PLAIN, 20));
        add(labelname);

        tfname = new JTextField();
        tfname.setBounds(200, 150, 150, 30);
        add(tfname);

        JLabel labelfname = new JLabel("Father's Name");
        labelfname.setBounds(400, 150, 150, 30);
        labelfname.setFont(new Font("serif", Font.PLAIN, 20));
        add(labelfname);

        tffname = new JTextField();
        tffname.setBounds(600, 150, 150, 30);
        add(tffname);

        JLabel labeldob = new JLabel("Date of Birth");
        labeldob.setBounds(50, 200, 150, 30);
        labeldob.setFont(new Font("serif", Font.PLAIN, 20));
        add(labeldob);

        dcdob = new JDateChooser();
        dcdob.setBounds(200, 200, 150, 30);
        add(dcdob);

        JLabel labelsalary = new JLabel("Salary");
        labelsalary.setBounds(400, 200, 150, 30);
        labelsalary.setFont(new Font("serif", Font.PLAIN, 20));
        add(labelsalary);

        tfsalary = new JTextField();
        tfsalary.setBounds(600, 200, 150, 30);
        add(tfsalary);

        JLabel labeladdress = new JLabel("Address");
        labeladdress.setBounds(50, 250, 150, 30);
        labeladdress.setFont(new Font("serif", Font.PLAIN, 20));
        add(labeladdress);

        tfaddress = new JTextField();
        tfaddress.setBounds(200, 250, 150, 30);
        add(tfaddress);

        JLabel labelphone = new JLabel("Phone");
        labelphone.setBounds(400, 250, 150, 30);
        labelphone.setFont(new Font("serif", Font.PLAIN, 20));
        add(labelphone);

        tfphone = new JTextField();
        tfphone.setBounds(600, 250, 150, 30);
        add(tfphone);

        JLabel labelemail = new JLabel("Email");
        labelemail.setBounds(50, 300, 150, 30);
        labelemail.setFont(new Font("serif", Font.PLAIN, 20));
        add(labelemail);

        tfemail = new JTextField();
        tfemail.setBounds(200, 300, 150, 30);
        add(tfemail);

        JLabel labeleducation = new JLabel("Highest Education");
        labeleducation.setBounds(400, 300, 150, 30);
        labeleducation.setFont(new Font("serif", Font.PLAIN, 20));
        add(labeleducation);

        tfeducation = new JTextField();
        tfeducation.setBounds(600, 300, 150, 30);
        add(tfeducation);

        JLabel labeldesignation = new JLabel("Designation");
        labeldesignation.setBounds(50, 350, 150, 30);
        labeldesignation.setFont(new Font("serif", Font.PLAIN, 20));
        add(labeldesignation);

        tfdesignation = new JTextField();
        tfdesignation.setBounds(200, 350, 150, 30);
        add(tfdesignation);

        JLabel labelempId = new JLabel("Employee Id");
        labelempId.setBounds(50, 400, 150, 30);
        labelempId.setFont(new Font("serif", Font.PLAIN, 20));
        add(labelempId);

        this.lblempId = new JLabel();
        this.lblempId.setBounds(200, 400, 150, 30);
        this.lblempId.setFont(new Font("serif", Font.PLAIN, 20));
        add(this.lblempId);
    }

    private void fetchEmployeeDetails() {
        try {
            conn c = new conn();
            String query = "SELECT * FROM employee WHERE empId = '" + empId + "'";
            ResultSet rs = c.s.executeQuery(query);
            if (rs.next()) {
                tfname.setText(rs.getString("name"));
                tffname.setText(rs.getString("fname"));
                String dobString = rs.getString("dob");
                if (dobString != null) {  // Check if dobString is not null
                    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
                    try {
                        java.util.Date dob = sdf.parse(dobString); // Parse the date string
                        dcdob.setDate(dob); // Set the date in the JDateChooser
                    } catch (ParseException e) {
                        e.printStackTrace(); // Handle parsing exception
                    }
                }
                tfaddress.setText(rs.getString("address"));
                tfsalary.setText(rs.getString("salary"));
                tfphone.setText(rs.getString("phone"));
                tfemail.setText(rs.getString("email"));
                tfeducation.setText(rs.getString("education"));
                lblempId.setText(rs.getString("empId"));
                tfdesignation.setText(rs.getString("designation"));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void actionPerformed(ActionEvent ae) {
        if (ae.getSource() == updateButton) {
            String name = tfname.getText();
            String fname = tffname.getText();

            // Get date of birth properly
            java.util.Date date = dcdob.getDate(); // Get the date from JDateChooser
            java.sql.Date sqlDate = null; // Initialize sqlDate

            if (date != null) {
                sqlDate = new java.sql.Date(date.getTime()); // Convert to java.sql.Date
            } else {
                JOptionPane.showMessageDialog(null, "Please select a valid date of birth.");
                return; // Exit if no date is selected
            }

            String salary = tfsalary.getText();
            String address = tfaddress.getText();
            String phone = tfphone.getText();
            String email = tfemail.getText();
            String education = tfeducation.getText();
            String designation = tfdesignation.getText();

            try {
                conn c = new conn();
                String query = "UPDATE employee SET name = '" + name + "', fname = '" + fname + "', dob = '" + sqlDate + "', salary = '" + salary + "', address = '" + address + "', phone = '" + phone + "', email = '" + email + "', education = '" + education + "', designation = '" + designation + "' WHERE empId = '" + empId + "'";
                c.s.executeUpdate(query);
                JOptionPane.showMessageDialog(null, "Details updated successfully");
                setVisible(false);
                new Home();
            } catch (Exception e) {
                e.printStackTrace();
            }
        } else {
            setVisible(false);
            new Home();
        }
    }

    public static void main(String[] args) {
        new UpdateEmployee("empId"); 
    }
}