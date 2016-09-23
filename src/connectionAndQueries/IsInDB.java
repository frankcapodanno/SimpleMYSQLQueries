/*
 * Copyright Francesco Capodanno 2015. All rights reserved
 */
package connectionAndQueries;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

/**
 *
 * @author Francesco Capodanno <Francesco at capodanno.click>
 */
public class IsInDB {
    
    private boolean isErr;
    private String  errString;
    
    public IsInDB()
    {
        
    }
    
    /**
     * This method look a "ver" value is in a specific "column" of a specific table in standard DB "ergousersdb"
     * @param ver element to search example "frank"
     * @param column column to search example uname
     * @param table table to search example myMembers
     * @return true when is find
     */
    public boolean isDatainDB(String ver, String column, String table)
    {
        boolean isData = true;
          
        try {
            Connection connection = null;  
            Class.forName(ConnectionDB.DRIVER_JDBC).newInstance();  
            connection = DriverManager.getConnection(ConnectionDB.CONNECTION_URL, 
                    ConnectionDB.USER_DB, ConnectionDB.PASS_DB);             
            
            StringBuilder buildQuery = new StringBuilder("select ");
            buildQuery.append(column).
            append(" from ").append(table).append(" where ")
                    .append(column).append("=?");
            
            //PreparedStatement ps = connection.prepareStatement("select uname from myMembers where uname=?");  
          
            PreparedStatement ps = connection.prepareStatement(buildQuery.toString());  
            
            ps.setString(1,ver);  
            ResultSet rs = ps.executeQuery();                 
            if (!rs.next()) { 
                isData = false;                
            }  
            else{  
                isData = true;            
            }             
           connection.close();
        } catch (Exception ex) {  
            errString  = ex.getLocalizedMessage();
            System.out.println("Error ->" + errString);              
            isErr = true; 
            isData = false;
        } 
       
        return isData;
    }
    
    /**
     * Return if an error is encountred
     * @return
     */
    public boolean get_isErr()
    {
        return isErr;
    }
    
    /**
     * If an error is encountred return the error in a String
     * @return
     */
    public String get_errString()
    {
        return errString;
    }
    
}
