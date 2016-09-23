/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package connectionAndQueries;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

/**
 *
 * @author guru
 */
public class IsInDB_generic {
    private boolean isErr;
    private String  errString;
    
    public IsInDB_generic()
    {
        
    }
    
    /**
     * This method look a "ver" value is in a specific "column" of a specific table in standard DB "ergousersdb"
     * @param ver element to search example "frank"
     * @param column column to search example uname
     * @param table table to search example myMembers
     * @return true when is find
     */
    public boolean isDatainDB(String DB, String ver, String column, String table)
    {
        boolean isData = true;
          
        try {
            String ConnectionURL = ConnectionDB.CONNECTION_URL_BASIC + DB;
            Connection connection = null;  
            Class.forName(ConnectionDB.DRIVER_JDBC).newInstance();  
            connection = DriverManager.getConnection(ConnectionURL, 
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

