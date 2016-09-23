/*
 * Copyright Francesco Capodanno 2015. All rights reserved
 */
package connectionAndQueries;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;;

/**
 *
 * @author guru
 */
public class InsertSignUp {
    
    private String error;
    private Boolean isError;
    
    public InsertSignUp(String title, String name, String sex, LocalDate converted_bornDate, 
            String country,String email, String username, String password, LocalDate currentTimeStamp){
        error = null;
        isError = false;             
      try {    
            
            Connection connection = null;  
            Class.forName(ConnectionDB.DRIVER_JDBC).newInstance();  
            connection = DriverManager.getConnection(ConnectionDB.CONNECTION_URL, 
                    ConnectionDB.USER_DB, ConnectionDB.PASS_DB);  
            String queryInsert = "INSERT INTO `ergousersdb`.`myMembers` (`id`, `title`, "
                    + "`name`, `last_name`, `sex`, `borndate`, `country`, "
                    + "`email`, `uname`, `pass`, `regdate`, `isstaff`, "
                    + "`activation`) "
                    + "VALUES (NULL, ?, ?, '', ?, ?, ?, ?, ?, ?, ?, '0', '0');";
            String etitle = title;
            String ename = name; 
            String esex = sex.substring(0, 1);
            LocalDate ebornDate = converted_bornDate;
            String ecountry = country;
            String eemail = email;
            String eusername = username;
            String epassword = password;
            LocalDate eregistration =  currentTimeStamp;            
            PreparedStatement ps = connection.prepareStatement(queryInsert);
            ps.setString(1,etitle);
            ps.setString(2,ename);             
            ps.setString(3, esex);
            ps.setString(4, ebornDate.format(DateTimeFormatter.ISO_LOCAL_DATE));
            ps.setString(5, ecountry);
            ps.setString(6, eemail);
            ps.setString(7, eusername);
            ps.setString(8, epassword);
            ps.setString(9, eregistration.format(DateTimeFormatter.ISO_LOCAL_DATE));                   
            ps.executeUpdate();       
            connection.close();                  
  
        } catch (Exception ex) {
          
            System.out.println("Error insert final !->" + ex.getMessage());  
           error = ex.getLocalizedMessage();          
           isError = true;
        }       
    }
    
    public String getError()
    {
        return error;
    }
    public boolean isError()
    {
        return isError;
    }
}
