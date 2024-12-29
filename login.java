
package employee_management_system;

import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
import java.sql.*;

public class login extends JFrame implements ActionListener {

    JTextField tfusername;
    JPasswordField pfpassword;
    JRadioButton rbAdmin, rbEmployee;
    ButtonGroup roleGroup;
    JButton togglePasswordButton;
    ImageIcon eyeIcon;

    login() {
        getContentPane().setBackground(Color.white);
        setLayout(null);

        // Username Label and TextField
        JLabel lblusername = new JLabel("Username");
        lblusername.setBounds(40, 20, 100, 30);
        add(lblusername);

        tfusername = new JTextField();
        tfusername.setBounds(150, 20, 150, 30);
        add(tfusername);

        // Password Label
        JLabel lblpassword = new JLabel("Password");
        lblpassword.setBounds(40, 70, 100, 30);
        add(lblpassword);

        // Eye icon for showing/hiding password
        eyeIcon = new ImageIcon("H:/Employee-Management-System/Employee_Management_System/src/icons/pasVisible.jpg");

        // Create a JPanel to hold password field and eye button
        JPanel passwordPanel = new JPanel();
        passwordPanel.setLayout(null);
        passwordPanel.setBounds(150, 70, 150, 30); // Set bounds to position the password panel
        add(passwordPanel);

        // JPasswordField inside JPanel
        pfpassword = new JPasswordField();
        pfpassword.setBounds(0, 0, 120, 30); // Password field inside the panel
        passwordPanel.add(pfpassword);

        
        Image scaledImage = eyeIcon.getImage().getScaledInstance(30, 30, Image.SCALE_SMOOTH);
        ImageIcon scaledIcon = new ImageIcon(scaledImage);

        // Button to toggle password visibility
        togglePasswordButton = new JButton(scaledIcon);
        togglePasswordButton.setBounds(120, 0, 30, 30); // Position the eye icon button inside the password field
        togglePasswordButton.setBackground(Color.black);
        togglePasswordButton.setBorder(BorderFactory.createEmptyBorder()); // Remove border
        togglePasswordButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                // Toggle visibility of password
                if (pfpassword.getEchoChar() == '\u0000') {
                    pfpassword.setEchoChar('*'); // Hide password
                } else {
                    pfpassword.setEchoChar('\u0000'); // Show password
                }
            }
        });
        passwordPanel.add(togglePasswordButton);

        // Role Label
        JLabel lblRole = new JLabel("Select Role");
        lblRole.setBounds(40, 120, 100, 30);
        add(lblRole);

        // Radio Buttons for Role Selection
        rbAdmin = new JRadioButton("Admin");
        rbAdmin.setBounds(150, 120, 80, 30);
        rbAdmin.setSelected(true); // Default to Admin
        add(rbAdmin);

        rbEmployee = new JRadioButton("Employee");
        rbEmployee.setBounds(230, 120, 100, 30);
        add(rbEmployee);

        // Grouping the radio buttons
        roleGroup = new ButtonGroup();
        roleGroup.add(rbAdmin);
        roleGroup.add(rbEmployee);

        // Login Button
        JButton login = new JButton("LOGIN");
        login.setBounds(150, 170, 150, 30);
        login.setBackground(Color.black);
        login.setForeground(Color.white);
        login.addActionListener(this);
        add(login);

        // Frame settings
        setSize(600, 300);
        setLocation(450, 200);
        setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent ae) {
        try {
            String username = tfusername.getText();
            String password = new String(pfpassword.getPassword()); 
            String role = rbAdmin.isSelected() ? "admin" : "employee"; 

            conn c = new conn();

            // Modified query to include the role
            String query = "SELECT * FROM login WHERE BINARY username = '" + username + "' AND BINARY password = '" + password + "' AND BINARY role = '" + role + "'";
            ResultSet rs = c.s.executeQuery(query);

            if (rs.next()) {
                if (role.equalsIgnoreCase("admin")) {
                    setVisible(false);
                    new Home(); 
                } else if (role.equalsIgnoreCase("employee")) {
                    setVisible(false);
                    new ViewYourInfo();
                }
            } else {
                JOptionPane.showMessageDialog(null, "Invalid username, password, or role");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        new login();
    }
}
