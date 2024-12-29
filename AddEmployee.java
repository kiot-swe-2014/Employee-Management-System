package employee_management_system;

import java.awt.Color;
import java.awt.*;
import javax.swing.*;
import com.toedter.calendar.JDateChooser;
import java.awt.event.*;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random; // Ensure this import is present
import java.util.regex.*;

public class AddEmployee extends JFrame implements ActionListener {

    Random ran = new Random();
    int number = ran.nextInt(999999); // Generates random number for ID

    // Global declaration 
    JTextField tfname, tffname, tfaddress, tfphone, tfemail, tfsalary, tfdesignation;
    JDateChooser dcdob;
    JComboBox<String> cbeducation;
    JLabel lblempId;
    JButton add, back;

    AddEmployee() {
        getContentPane().setBackground(Color.white);
        setLayout(null);

        JLabel heading = new JLabel("Add Employee Detail");
        heading.setBounds(320, 30, 500, 50);
        heading.setFont(new Font("SAN_SERIF", Font.BOLD, 25));
        add(heading);

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

        dcdob = new JDateChooser();  // dcdob = date chooser for date of birth
        dcdob.setBounds(200, 200, 150, 30);
        add(dcdob);
        JLabel labelsalary = new JLabel("Salary");
        labelsalary.setBounds(400, 200, 150, 30);
        labelsalary.setFont(new Font("serif", Font.PLAIN, 20));
        add(labelsalary);

        tfsalary = new JTextField();
        tfsalary.setBounds(600, 200, 150, 30);
        add(tfsalary);

        JLabel labeladdres = new JLabel("Address");
        labeladdres.setBounds(50, 250, 150, 30);
        labeladdres.setFont(new Font("serif", Font.PLAIN, 20));
        add(labeladdres);

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

        String courses[] = {"BBA", "BCA", "BA", "BSC", "B.COM", "BTech", "MBA", "MCA", "MA", "MTech", "MSC", "PHD"};
        cbeducation = new JComboBox<>(courses); // cbeducation = combo box education
        cbeducation.setBackground(Color.WHITE);
        cbeducation.setBounds(600, 300, 150, 30);
        add(cbeducation);

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

        lblempId = new JLabel(" " + number);
        lblempId.setBounds(200, 400, 150, 30);
        lblempId.setFont(new Font("serif", Font.PLAIN, 20));
        add(lblempId);

        add = new JButton("Add Details");
        add.setBounds(250, 550, 150, 40);
        add.addActionListener(this);
        add.setBackground(Color.BLACK);
        add.setForeground(Color.white);
        add(add);

        back = new JButton("Back");
        back.setBounds(450, 550, 150, 40);
        back.addActionListener(this);
        back.setBackground(Color.BLACK);
        back.setForeground(Color.white);
        add(back);

        setSize(900, 700);
        setLocation(300, 50);
        setVisible(true);
    }

    public void actionPerformed(ActionEvent ae) {
        if (ae.getSource() == add) {
            String name = tfname.getText().trim();
            String fname = tffname.getText().trim();
            
            Date selectedDate = dcdob.getDate();
            String dob = null;
            
            if (selectedDate != null)
               {
                    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
                    dob = sdf.format(selectedDate);
                 } else {
                      JOptionPane.showMessageDialog(null, "Date of Birth is required.");
                       return;
                  }
            String salary = tfsalary.getText().trim();
            String address = tfaddress.getText().trim();
            String phone = tfphone.getText().trim();
            String email = tfemail.getText().trim();
            String education = (String) cbeducation.getSelectedItem();
            String designation = tfdesignation.getText().trim();
            String empId = lblempId.getText().trim();

            // Check for empty fields
            if (name.isEmpty()) {
                JOptionPane.showMessageDialog(null, "Name is required.");
                return;
                
            }
            
              if (!name.matches("[a-zA-Z\\s]+")) {
                JOptionPane.showMessageDialog(null, "Invalid Name. Only letters are allowed.");
                return;
            }
              
            if (fname.isEmpty()) {
                JOptionPane.showMessageDialog(null, "Father's Name is required.");
                return;
            }
            
               if (!fname.matches("[a-zA-Z\\s]+")) {
                JOptionPane.showMessageDialog(null, "Invalid Father's Name. Only letters are allowed.");
                return;
            }
               
            if (dob.isEmpty()) {
                JOptionPane.showMessageDialog(null, "Date of Birth is required.");
                return;
            }
            if (salary.isEmpty()) {
                JOptionPane.showMessageDialog(null, "Salary is required.");
                return;
            }
            
                if (!salary.matches("\\d+(\\.\\d+)?")) {
                JOptionPane.showMessageDialog(null, "Invalid Salary. Please enter a valid number.");
                return;
            }
                
            if (address.isEmpty()) {
                JOptionPane.showMessageDialog(null, "Address is required.");
                return;
            }
            
           if (!address.matches("[a-zA-Z\\s]+")) {
                JOptionPane.showMessageDialog(null, " Only letters are allowed.");
                return;
            } 
                           
            if (phone.isEmpty()) {
                JOptionPane.showMessageDialog(null, "Phone number is required.");
                return;
            }
              if (!phone.matches("\\d{10}")) {
                JOptionPane.showMessageDialog(null, "Invalid Phone Number. It must be 10 digits.");
                return;
            }
              
            if (email.isEmpty()) {
                JOptionPane.showMessageDialog(null, "Email is required.");
                return;
            }
            
             if (!email.matches("^[\\w-\\.]+@([\\w-]+\\.)+[\\w-]{2,4}$")) {
                JOptionPane.showMessageDialog(null, "Invalid Email Format.");
                return;
            }
             
            if (designation.isEmpty()) {
                JOptionPane.showMessageDialog(null, "Designation is required.");
                return;
            }

               if (!designation.matches("[a-zA-Z\\s]+")) {
                JOptionPane.showMessageDialog(null, "Invalid Designation. Only letters are allowed.");
                return;
            }
            // If all validations pass, proceed with adding the details
            try {
                conn conn = new conn();
                String query = "insert into employee values ('" + name + "','" + fname + "','" + dob + "','" + salary + "','" + address + "','" + phone + "','" + email + "','" + education + "','" + designation + "','" + empId + "')";
                conn.s.executeUpdate(query);
                JOptionPane.showMessageDialog(null, "Details added Successfully");
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
        new AddEmployee();
    }
}