/*
 * Copyright Francesco Capodanno 2015. All rights reserved
 */
package connectionAndQueries;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;

/**
 *This class manage the insert of three values in a database with 4 column ( 1 is an id wiht autoincrement)
 *
 * @author Francesco Capodanno <Francesco at capodanno.click>
 */
public class Insert3ValuesNullId {
    
    private boolean noErrors;
    private String stringError;
    
    /**
     * This class manage the insert of three values in a database with 4 column ( 1 is an id with autoincrement)
     * @param val1 value 1 to insert
     * @param val2 value 2 to insert
     * @param val3 value 3 to insert
     * @param DB name of database
     * @param Table name of table
     * @param columns an array [4] of string that contain the name of columns 
     */
    public Insert3ValuesNullId(String val1, String val2, String val3, String DB, String Table,  String[] columns)
    { 
        noErrors = true;
        StringBuilder buildQuery = new StringBuilder("INSERT INTO `");
        buildQuery.append(DB).append("`.`").append(Table).append("` (`").
         append(columns[0]).append("`,`").append(columns[1]).append("`,`").
         append(columns[2]).append("`,`").append(columns[3]).append("`) ").
         append("VALUES (NULL,?, ?, ?);");
       // System.out.println("Query: "+ buildQuery.toString());
        
        // insert in database code generated and email.
        try{
           
         
            Connection connection = null;  
            Class.forName(ConnectionDB.DRIVER_JDBC).newInstance();  
            connection = DriverManager.getConnection(ConnectionDB.CONNECTION_URL, 
                    ConnectionDB.USER_DB, ConnectionDB.PASS_DB);  
            // String queryInsert = "INSERT INTO `ergousersdb`.`Codes` (`id`,`email`, `code`, `date`) VALUES (NULL,?, ?, ?);";
            PreparedStatement ps = connection.prepareStatement(buildQuery.toString());
            ps.setString(1, val1);
            ps.setString(2, val2);  
            ps.setString(3, val3);
            ps.executeUpdate();
            connection.close();
            
        } catch (Exception ex) {  
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
