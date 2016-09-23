/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package connectionAndQueries;

/**
 *
 * @author guru
 */
public class IsDBfull {
    
    public boolean is;
    
    private static final String COLUMN_STATE = "state";
    private static final String TABLE_STATE = "stateDB";
    
    /**
     * the name of the database to find stateDB and state column. SEE DOCUMENTATION
     * @param name_database The database to verify. Is full when in a DB there is a table "stateDB" 
     * with a column "state" where the value is 1.
     */
    public IsDBfull(String name_database)
            {               
              IsInDBs isIn = new IsInDBs();
              is = isIn.isDatainDB(name_database, "1", COLUMN_STATE, TABLE_STATE);                                       
            }     
}
