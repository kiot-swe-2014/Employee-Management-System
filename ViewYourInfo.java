package employee_management_system;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.*;
import java.awt.print.*;
import java.sql.*;

public class ViewYourInfo extends JFrame implements ActionListener {
    JTextField tfEmpId;
    JButton viewButton, backButton, printButton;
    JTable employeeTable;

    ViewYourInfo() {
        setLayout(null);

        // Label and Text Field for Employee ID
        JLabel lblEmpId = new JLabel("Enter Employee ID:");
        lblEmpId.setBounds(20, 20, 150, 30);
        add(lblEmpId);

        tfEmpId = new JTextField();
        tfEmpId.setBounds(180, 20, 150, 30);
        add(tfEmpId);

        // View Button to fetch the employee information
        viewButton = new JButton("View Info");
        viewButton.setBounds(350, 20, 100, 30);
        viewButton.addActionListener(this);
        add(viewButton);

        // Table to display employee information
        employeeTable = new JTable();
        JScrollPane sp = new JScrollPane(employeeTable);
        sp.setBounds(20, 70, 800, 300);
        add(sp);

        // Back Button to return to login page
        backButton = new JButton("Back");
        backButton.setBounds(470, 20, 100, 30); // Positioning it at the bottom right
        backButton.addActionListener(this);
        add(backButton);

        // Print Button to print employee information
        printButton = new JButton("Print Info");
        printButton.setBounds(580, 20, 100, 30);
        printButton.addActionListener(this);
        add(printButton);

        // Frame settings
        setSize(850, 400);
        setLocation(450, 200);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
    }

    public void actionPerformed(ActionEvent ae) {
        // View Info button action
        if (ae.getSource() == viewButton) {
            String empId = tfEmpId.getText().trim();
            if (empId.isEmpty()) {
                JOptionPane.showMessageDialog(null, "Please enter Employee ID!");
                return;
            }

            try {
                // Database connection
                conn c = new conn(); // Ensure the 'conn' class exists and handles DB connection
                String query = "SELECT * FROM employee WHERE empId = '" + empId + "'";
                ResultSet rs;
                rs = c.s.executeQuery(query);

                // Table columns
                String[] columns = {"Name", "Father's Name", "DOB", "Salary", "Address", "Phone", "Email", "Education", "Designation", "Employee ID"};
                DefaultTableModel model = new DefaultTableModel(columns, 0);

                // Fetch data and populate table
                if (rs.next()) {
                    Object[] rowData = {
                        rs.getString("name"),
                        rs.getString("fname"),
                        rs.getString("dob"),
                        rs.getString("salary"),
                        rs.getString("address"),
                        rs.getString("phone"),
                        rs.getString("email"),
                        rs.getString("education"),
                        rs.getString("designation"),
                        rs.getString("empId")
                    };
                    model.addRow(rowData);
                } else {
                    JOptionPane.showMessageDialog(null, "No record found for Employee ID: " + empId);
                    return;
                }

                // Set model to table
                employeeTable.setModel(model);

            } catch (SQLException e) {
                e.printStackTrace();
                JOptionPane.showMessageDialog(null, "Error fetching data from database!");
            }
        }

        // Back button action to go back to login page
        if (ae.getSource() == backButton) {
            setVisible(false); 
            new login(); 
        }

        // Print button action to print the employee info
        if (ae.getSource() == printButton) {
            try {
                boolean printed = employeeTable.print();
                if (printed) {
                    JOptionPane.showMessageDialog(null, "Employee Information Printed Successfully!");
                } else {
                    JOptionPane.showMessageDialog(null, "Print operation was cancelled.");
                }
            } catch (PrinterException e) {
                JOptionPane.showMessageDialog(null, "Error printing the data: " + e.getMessage());
            }
        }
    }

    public static void main(String[] args) {
        new ViewYourInfo(); 
    }
}
