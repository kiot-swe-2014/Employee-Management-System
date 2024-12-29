
package employee_management_system;

import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
import java.sql.*;

public class login extends JFrame implements ActionListener {
    
    JTextField tfusername, tfpassword;
    
    login() {
        getContentPane().setBackground(Color.white);
        setLayout(null);
        
        JLabel lblusername = new JLabel("Username");
        lblusername.setBounds(40, 20, 100, 30);
        add(lblusername);
        
        tfusername = new JTextField();
        tfusername.setBounds(150, 20, 150, 30);
        add(tfusername);
        
        JLabel lblpassword = new JLabel("Password");
        lblpassword.setBounds(40, 70, 100, 30);
        add(lblpassword);
        
        tfpassword = new JPasswordField();
        tfpassword.setBounds(150, 70, 150, 30);
        add(tfpassword);
        
        JButton login = new JButton("LOGIN");
        login.setBounds(150, 140, 150, 30);
        login.setBackground(Color.black);
        login.setForeground(Color.white);
        login.addActionListener(this);
        add(login);
        
        ImageIcon i1 = new ImageIcon("H:/Employee-Management-System/Employee_Management_System/src/icons/second.jpg");
        if (i1.getImageLoadStatus() == MediaTracker.ERRORED) {
            System.err.println("Image not found or failed to load: icons/second.jpg");
            return; // Exit if image not found
        }
        
        Image i2 = i1.getImage().getScaledInstance(200, 200, Image.SCALE_DEFAULT);
        ImageIcon i3 = new ImageIcon(i2);
        JLabel image = new JLabel(i3);
        image.setBounds(350, 0, 200, 200);
        add(image);
        
        setSize(600, 300);
        setLocation(450, 200);
        setVisible(true);
    }
    
    public void actionPerformed(ActionEvent ae) {
        try {
            String username = tfusername.getText();
            String password = tfpassword.getText();
            
            conn c = new conn();
            
            // Use a case-sensitive query
            String query = "SELECT * FROM login WHERE BINARY username = '" + username + "' AND BINARY password = '" + password + "'";
            
            ResultSet rs = c.s.executeQuery(query);
            
            if (rs.next()) {
                setVisible(false); // Close login window
                new Home(); // Open home window
            } else {
                JOptionPane.showMessageDialog(null, "Invalid username or password");
                // Keep the login window open for user to try again
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    public static void main(String[] args) {
        new login();
    }
}