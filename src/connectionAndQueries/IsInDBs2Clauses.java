/*
 * Francesco Capodanno (c) 2015
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
public class IsInDBs2Clauses {

/**
 *
 * @author Francesco Capodanno <Francesco at capodanno.click>
 */

    
    private boolean isErr;
    private String  errString;
    private String  ConnectionURL;
    
    public IsInDBs2Clauses() {
        
    }
    
    /**
     * This method look a "ver" value is in a specific "column" of a specific table in a local DB
     * @param ver element to search example "frank"
     * @param column column to search example uname
     * @param table table to search example myMembers
     * @return true when is find
     */
    public boolean isDatainDB(String DB, String ver, String column, 
            String ver2, String column2, String table)
    {
        boolean isData = true;
        ConnectionURL = this.createConnectionURL(DB);
        
        try {            
            Connection connection = null;  
            Class.forName(ConnectionDB.DRIVER_JDBC).newInstance();  
            connection = DriverManager.getConnection(ConnectionURL, 
                    ConnectionDB.USER_DB, ConnectionDB.PASS_DB);             
            
            /*
            StringBuilder buildQuery = new StringBuilder("select ");
            buildQuery.append(column).
            append(" from ").append(table).append(" where ")
                    .append(column).append("=?");
            */
            
            StringBuilder buildQuery = new StringBuilder("select ");
            buildQuery.append(column).append(" from ").append(table). append (" where ")
                      .append("(")
                      .append(column).append(" =? ").append(" and ")
                      .append(column2).append("=? ").append(")");                      
           
            
            //PreparedStatement ps = connection.prepareStatement("select uname from myMembers where uname=?");  
            //SELECT room_name FROM rooms WHERE (uid_creator = '1' AND r1 = '1')
            PreparedStatement ps = connection.prepareStatement(buildQuery.toString());  
            
            ps.setString(1,ver);  
            ps.setString(2, ver2);
            
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
    
    private String createConnectionURL(String db) {
       StringBuilder url = new StringBuilder(ConnectionDB.CONNECTION_URL_BASIC);
       url.append(db);
       return url.toString();       
    }    
}


