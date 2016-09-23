/*
 * Copyright Francesco Capodanno 2015. All rights reserved
 */
package connectionAndQueries;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;

/**
 *
 * @author Francesco Capodanno <Francesco at capodanno.click>
 */
public class Insert1ValueNullId {
    
private boolean noErrors;
 private String stringError;
    
    public Insert1ValueNullId(String val, String column, String table)
            {
             StringBuilder buildQuery = new StringBuilder("INSERT INTO ");
             buildQuery.append("`").append(ConnectionDB.DB_NAME).append("`")
                       .append(".`").append(table).append("` ")
                       .append("(`id`,`").append(column).append("`)")
                       .append(" VALUES (NULL,?);");
             
                try{         
         
            Connection connection = null;  
            Class.forName(ConnectionDB.DRIVER_JDBC).newInstance();  
            connection = DriverManager.getConnection(ConnectionDB.CONNECTION_URL, 
            ConnectionDB.USER_DB, ConnectionDB.PASS_DB);  
            // String queryInsert = "INSERT INTO `ergousersdb`.`Codes` (`id`,`email`, `code`, `date`) VALUES (NULL,?, ?, ?);";
            PreparedStatement ps = connection.prepareStatement(buildQuery.toString());
            ps.setString(1, val);
            ps.executeUpdate();
            connection.close();
            }
            catch (Exception ex)
            {
            stringError = ex.getMessage();
            System.out.println("Error ->" + stringError);  
            noErrors = false;
            }
           
          }
    
    /**
     * Return true when an Error is find
     * @return
     */
    public boolean getError()
    {
        
        return !noErrors;
    }
    
    /**
     * Return the query error in a string. 
     * @return
     */
    public String getErrorString()
    {
       return stringError;
    }
    
}
