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
public class GetFromDBs {
    
    private String stringErr;
    private boolean err;
    private String ConnectionURL;
    
    
    public GetFromDBs()
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
    public String getFromColumn(String DB, String table, String column, 
                                String where, String whereValue)
    {
        String result = null;
        ConnectionURL = createConnectionURL(DB);
        try {
            Connection connection = null;  
            Class.forName(ConnectionDB.DRIVER_JDBC).newInstance();  
            connection = DriverManager.getConnection(ConnectionURL, 
            ConnectionDB.USER_DB, ConnectionDB.PASS_DB);  
            StringBuilder queryBuild = new StringBuilder("select ");
            queryBuild.append(column).append(" from ").append(table).append(" where ")
                    .append(where).append("='").append(whereValue).append("'");
           // PreparedStatement ps = connection.prepareStatement("select text from ServerEmails where type='TEST_HTML_EN_EN'");  
           PreparedStatement ps = connection.prepareStatement(queryBuild.toString());  
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
       StringBuilder url = new StringBuilder(ConnectionDB.CONNECTION_URL_BASIC);
       url.append(db);
       return url.toString();       
    }
    
}
