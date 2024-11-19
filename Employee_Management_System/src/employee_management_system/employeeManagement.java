
package employee_management_system;

import java.awt.Color;
import java.awt.Font;
import java.awt.Image;
import java.awt.MediaTracker;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;

public class employeeManagement extends JFrame implements ActionListener {
    employeeManagement(){
      
        getContentPane().setBackground(Color.white);
        setLayout(null);
       
        JLabel heading = new JLabel("EMPLOYEE MANAGEMENT SYSTEM");
        heading.setBounds(80,30,1200,60);
      heading.setFont(new Font("serif",Font.PLAIN,60));
      heading.setForeground(Color.red);
      
       
        add(heading);
        
        
//        ImageIcon i1 = new ImageIcon(getClass().getClassLoader().getResource("icons/front.jpg"));
          ImageIcon i1 = new ImageIcon("H:/Employee-Management-System/Employee_Management_System/src/icons/front.jpg");

         if (i1.getImageLoadStatus() == MediaTracker.ERRORED) {
           System.err.println("Image not found or failed to load: icons/front.jpg");
            return; // Exit if image not found
        }
             
        Image i2 = i1.getImage().getScaledInstance(1100, 700, Image.SCALE_DEFAULT);
        ImageIcon i3 = new ImageIcon(i2);
        JLabel image =new JLabel(i3);
        image.setBounds(50,100,1050,500);
       add(image);
       
      
        JButton clickhere =new JButton("CLICK HERE TO CONTINUE");
        clickhere.setBounds(400,400,300,70);
        clickhere.setBackground(Color.black);
        clickhere.setForeground(Color.white);
        clickhere.addActionListener(this);
        image.add(clickhere);
        
          setSize(1170,650);      
       setLocation(200,50);
          setVisible(true);
          
          while(true){
              heading.setVisible(false);
              try{
                  Thread.sleep(500);
             }catch(Exception e){
                 
              }
              
            heading.setVisible(true);
            try{
                 Thread.sleep(1500);
              }catch(Exception e){
                  
              }
          }  
          
    }
    
    public void actionPerformed(ActionEvent ae){
        setVisible(false);
        new login();
    }
    
    public static void main(String args[]){
     //   employeeManagement s =new employeeManagement(); or use anonymus function
    new employeeManagement();
    }
}


