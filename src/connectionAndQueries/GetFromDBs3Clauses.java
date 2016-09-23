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
public class GetFromDBs3Clauses {
    
    private String stringErr;
    private boolean err;
    private String ConnectionURL;
    
    public GetFromDBs3Clauses()
            {
              err = false;
              stringErr = null;
            }
    
    /**
     * This method retrieve data from DB using these values
     * @param DB the database in core DBs that you want to use
     * @param table the name of the table
     * @param column the column to take data
     * @param whereColumn1 Column to find the value
     * @param whereValue1 the value 
     * @param whereColumn2 Column 2 for the and
     * @param whereValue2 the value 2
     * @param whereColumn3
     * @param whereValue3
     * @return
     */
    public String getFromColumn(String DB, String table, String column, 
                                String whereColumn1, String whereValue1,
                                String whereColumn2, String whereValue2,
                                String whereColumn3, String whereValue3)
    {
        String result = null;
        ConnectionURL = createConnectionURL(DB);
        
        try {
            Connection connection = null;  
            Class.forName(ConnectionDB.DRIVER_JDBC).newInstance();  
            connection = DriverManager.getConnection(ConnectionURL, 
            ConnectionDB.USER_DB, ConnectionDB.PASS_DB);  
            StringBuilder queryBuild = new StringBuilder("select ");
            queryBuild.append(column).append(" from ").append(table).append(" where ( ")
                    .append(whereColumn1).append("='").append(whereValue1).append("' ")
                    .append("and ")
                    .append(whereColumn2).append("='").append(whereValue2).append("' ")
                    .append("and ")
                    .append(whereColumn3).append("='").append(whereValue3).append("' ")
                    .append(")");
            
            PreparedStatement ps = connection.prepareStatement(queryBuild.toString());  
           ResultSet rs = ps.executeQuery();  
            
            while (rs.next()) { 
                result = rs.getString(column); 
            }  
                       
         connection.close();
        } catch (Exception ex)
                { stringErr = ex.getLocalizedMessage();
                  System.out.println("Error ->" + stringErr); 
                  err = true; }
        return result;
    }
    
    
    private String createConnectionURL(String db) {
       StringBuilder url = new StringBuilder(ConnectionDB.CONNECTION_URL_BASIC);
       url.append(db);
       return url.toString();       
    }
    
}
