/*
 * Copyright Francesco Capodanno 2015. All rights reserved
 */
package connectionAndQueries;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.util.List;

/**
 * This is an Insert class for any databases in Cores DBs the ergo user
 * @author Francesco Capodanno <Francesco at capodanno.click>
 */
public class InsertInDBs {
    
    private String stringError;
    private boolean noErrors;
    private boolean showQuery;
    private final String ClassName;

   
    
    public InsertInDBs()
    {
        showQuery = false;
        ClassName = getClass().getName();
    }
    
    /**
     *  This show the Query useful during debug
     * @param showQuery 
     */
    public void setShowQuery(boolean showQuery) {
        this.showQuery = showQuery;
    }
    
    /**
     * This method make an Insert query to the mysql DB. 
     * remember the columns and values are list so started from 0
     * 
     * @param columns
     * @param values
     * @param DB
     * @param Table
     * @return
     */
    public boolean insertData(List<String> columns, List<String> values, String DB, String Table)
    {
        if (showQuery)
        {
        System.out.println(ClassName + 
                " columns : " + columns.toString() +  
                "\n\n values : " + values.toString());
        }
        int nStatements = 0;
        boolean isInsertOk = true;
         StringBuilder buildQuery = new StringBuilder("INSERT INTO ");
         buildQuery.append("`").append(DB).append("`").append(".").append("`")
                   .append(Table).append("`");
         buildQuery.append(" ");
         buildQuery.append("(");
         for (String e  : columns )
         {
             buildQuery.append("`").append(e).append("`").append(",");
         }
         buildQuery.deleteCharAt(buildQuery.length()- 1);// remove the last ,
         buildQuery.append(")"); // close the parenthesis
         buildQuery.append(" ").append("VALUES").append(" ");
         buildQuery.append("(");
         for(String e : values )
         {
          if (e.equals("NULL")) {buildQuery.append("NULL").append(","); }
          else {buildQuery.append("?").append(",");nStatements++;}
         }
         buildQuery.deleteCharAt(buildQuery.length()- 1);// remove the last ,
         buildQuery.append(");"); // close the parenthesis and the query
          // String queryInsert = "INSERT INTO `ergousersdb`.`Codes` (`id`,`email`, `code`, `date`) VALUES (NULL,?, ?, ?);";
         
         try{         
         
            Connection connection = null;  
            Class.forName(ConnectionDB.DRIVER_JDBC).newInstance();  
            connection = DriverManager.getConnection(ConnectionDB.CONNECTION_URL, 
            ConnectionDB.USER_DB, ConnectionDB.PASS_DB);
            if (this.showQuery)
            {
            System.out.println(this.getClass().getName() + " --> QUERY INSERT : " + buildQuery.toString());
            }
            PreparedStatement ps = connection.prepareStatement(buildQuery.toString());
            
            for (int i = 1; i <= nStatements; i++)
            {
                ps.setString(i, values.get(i-1));               
            }
            
            ps.executeUpdate();
            isInsertOk = true;
            connection.close();
                }
            
            catch (Exception ex)
                    {
                     stringError = ex.getMessage();
            System.out.println(this.getClass().getName() + ": Query Error ->" + stringError);  
            noErrors = false;
            isInsertOk = false;
                    }
                
        return isInsertOk;        
        
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
