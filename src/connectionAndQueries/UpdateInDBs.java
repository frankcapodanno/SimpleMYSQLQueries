/*
 * Copyright Francesco Capodanno 2015. All rights reserved
 */
package connectionAndQueries;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;

/**
 *
 * @author Francesco Capodanno <Francesco at capodanno.click>
 */
public class UpdateInDBs {
    private String stringError;
    private boolean noErrors;
    public UpdateInDBs(){
        noErrors = true;        

}
    
    /**
     *This method create a simple Update with a unique where
     * @param db database
     * @param table table
     * @param column column 
     * @param value value of this column
     * @param where must be unique for not create ambiguos entry
     * @param whereValue the value of the where
     * @return
     */
    public boolean updateDB(String db, String table, String column, String value, String where, String whereValue)
    {
        boolean isok = true;
         // UPDATE `ergousersdb`.`ipNumbersErrors` SET `errors` = '2' WHERE `ipNumbersErrors`.`ip` = '127.0.0.1';
        StringBuilder buildQuery = new StringBuilder("UPDATE ");
        buildQuery.append("`").append(db).append("`").append(".")
                  .append("`").append(table)
                  .append("` ");
        buildQuery.append("SET ").append("`").append(column).append("` ")
                  .append("= ").append("'").append(value).append("' ")
                  .append("WHERE ").append("`").append(table).append("`")
                  .append(".").append("`").append(where).append("` ")
                  .append("= ").append("'").append(whereValue).append("'")
                  .append(";");
        
        try{Connection connection = null;  
            Class.forName(ConnectionDB.DRIVER_JDBC).newInstance();  
            connection = DriverManager.getConnection(ConnectionDB.CONNECTION_URL, 
            ConnectionDB.USER_DB, ConnectionDB.PASS_DB);
           
            Statement stmt = connection.createStatement();
            stmt.executeUpdate(buildQuery.toString());
            connection.close();
            noErrors = true;
        }
        catch (Exception ex)
        {
            stringError = ex.getMessage();
            System.out.println("Error ->" + stringError);
            noErrors = false;
            isok = false;
        }
        
        return isok;
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
