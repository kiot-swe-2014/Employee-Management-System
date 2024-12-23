package employee_management_system;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class Home extends JFrame implements ActionListener {

    JButton view, add, update, remove, exit;

    Home() {
        setLayout(null);

        ImageIcon i1 = new ImageIcon("H:/Employee-Management-System/Employee_Management_System/src/icons/home.jpg");
        if (i1.getImageLoadStatus() == MediaTracker.ERRORED) {
            System.err.println("Image not found or failed to load: H:/Employee-Management-System/Employee_Management_System/src/icons/home.jpg");
            return; // Exit if image not found
        }

        Image i2 = i1.getImage().getScaledInstance(1120, 630, Image.SCALE_DEFAULT);
        ImageIcon i3 = new ImageIcon(i2);
        JLabel image = new JLabel(i3);
        image.setBounds(0, 0, 1120, 630);
        add(image);

        JLabel heading = new JLabel("EMPLOYEE MANAGEMENT SYSTEM");
        heading.setBounds(620, 20, 400, 40);
        heading.setFont(new Font("Raleway", Font.BOLD, 22));
        image.add(heading);

        add = new JButton("Add Employee");
        add.setBounds(650, 80, 150, 40);
        add.addActionListener(this);
        image.add(add);

        view = new JButton("View Employees");
        view.setBounds(820, 80, 150, 40);
        view.addActionListener(this);
        image.add(view);

        update = new JButton("Update Employee");
        update.setBounds(650, 140, 150, 40);
        update.addActionListener(this);
        image.add(update);

        remove = new JButton("Remove Employee");
        remove.setBounds(820, 140, 150, 40);
        remove.addActionListener(this);
        image.add(remove);

        // Adding Exit button
        exit = new JButton("Exit");
        exit.setBounds(950, 500, 100, 40); // Positioning it at the bottom right
        exit.addActionListener(this);
        image.add(exit);

        setSize(1120, 630);
        setLocation(250, 100);
        setVisible(true);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // Ensure the application exits on close
    }

    public void actionPerformed(ActionEvent ae) {
        if (ae.getSource() == add) {
            setVisible(false);
            new AddEmployee();
        } else if (ae.getSource() == view) {
            setVisible(false);
            new ViewEmployee();
        } else if (ae.getSource() == update) {
            setVisible(false);
            new ViewEmployee();
        } else if (ae.getSource() == remove) {
            setVisible(false);
            new RemoveEmployee();
        } else if (ae.getSource() == exit) {
             JOptionPane.showMessageDialog(null,"Do you want to Exit ?");
            System.exit(0); // Exit the application
        }
    }

    public static void main(String[] args) {
        new Home();
    }
}