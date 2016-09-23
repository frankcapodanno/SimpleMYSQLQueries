/*
 *Francesco Capodanno 2015(c).
 */
package connectionAndQueries;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import logger.LoggerWriter;
import logger.TypeLog;

/**
 *
 * @author guru
 */
public class DeleteFromDBs {
    
    private String stringErr;
    private boolean err;

    public boolean isErr() {
        return err;
    }
    private String ConnectionURL;
    
    public DeleteFromDBs(){
        err = false;
        stringErr = null;
    }
    
    /**
     * This eliminate a row in a DB in Local databases 
     * @param DB
     * @param table
     * @param whereColumn
     * @param whereValue
     */
    public void DeleteFromColumn(String DB, String table, String whereColumn, String whereValue)
    {
        StringBuilder q_build = new StringBuilder("DELETE FROM `");
        q_build.append(DB).append("`.`")
               .append(table).append("` ")
               .append("WHERE `")
               .append(table).append("`.`")
               .append(whereColumn).append("` ")
               .append("= ").append("'").append(whereValue).append("'");
        ConnectionURL = createConnectionURL(DB);
        System.out.println("Connection URL :" + ConnectionURL);
        System.out.println("Query: " + q_build.toString());
        try {
            Connection connection = null;  
            Class.forName(ConnectionDB.DRIVER_JDBC).newInstance();  
            connection = DriverManager.getConnection(ConnectionURL, 
            ConnectionDB.USER_DB, ConnectionDB.PASS_DB); 
            PreparedStatement ps = connection.prepareStatement(q_build.toString());
            ps.executeUpdate();            
            
            connection.close();
        }catch (Exception e)
        {
            String message = "Error in DeleteFromDBs" + e.getMessage();
            System.out.println(message);
            LoggerWriter logger = new LoggerWriter();
            logger.setLevel(TypeLog.HIGH);
            logger.write(message);  
            err = true;
        }
    }    
     private String createConnectionURL(String db) {
       StringBuilder url = new StringBuilder(ConnectionDB.CONNECTION_URL_BASIC);
       url.append(db);
       return url.toString();       
    }    
}
