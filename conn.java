
package employee_management_system;

import java.sql.*;

    
public class conn {
    
  
    public Connection c;
    public Statement s;
    
    public conn(){
        
        try{
            Class.forName("com.mysql.cj.jdbc.Driver");
            
            c= DriverManager.getConnection("jdbc:mysql://localhost:3306/employeemanagementsystem","root","");
            
               s = c.createStatement();  //create initialization
        }catch(Exception e){
            e.printStackTrace();
        }
    }
}
