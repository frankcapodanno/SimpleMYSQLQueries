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
 * @author guru
 */
public class GetLastFromDBs {
    private String stringErr;
    private boolean err;
    private String ConnectionURL;
    
    
    public GetLastFromDBs()
            {
              err = false;
              stringErr = null;
            }
    
    /**
     * This method retrieve data from DB using these values
     * @param DB the database in core DBs that you want to use
     * @param table the name of the table
     * @param column the column to take data
     * @param where the where clause column label
     * @param whereValue the value without ''
     * @return
     */
    public String getLast(String DB, String table, String column)
    {
       // query last SELECT fields FROM table ORDER BY id DESC LIMIT 1;
       // query example SELECT id FROM Countries ORDER BY id DESC LIMIT 1  
        
        String result = null;
        ConnectionURL = createConnectionURL(DB);
        try {
            Connection connection = null;  
            Class.forName(ConnectionDB.DRIVER_JDBC).newInstance();  
            connection = DriverManager.getConnection(ConnectionURL, 
            ConnectionDB.USER_DB, ConnectionDB.PASS_DB);  
            StringBuilder Querybuild = new StringBuilder("select ");
            Querybuild.append(column).append(" from ").append(table).append(" ORDER BY ")
                      .append(column).append(" DESC LIMIT 1");
           // PreparedStatement ps = connection.prepareStatement("select text from ServerEmails where type='TEST_HTML_EN_EN'");  
            PreparedStatement ps = connection.prepareStatement(Querybuild.toString());  
            ResultSet rs = ps.executeQuery();  
            
            while (rs.next()) { 
                result = rs.getString(column);           
            }                         
           connection.close();
        } catch (Exception ex) {  
            stringErr = ex.getLocalizedMessage();
            System.out.println("Error ->" + stringErr); 
            err = true;
            
            
        } 
        
        return result;
    }
    
    
    public boolean getErr()
            {
                return err;
            }
    
    public String getStringErr()
    {
        return stringErr;
    }

    private String createConnectionURL(String db) {
       StringBuilder url = new StringBuilder("jdbc:mysql://localhost:3306/");
       url.append(db);
       return url.toString();       
    }
    
}
