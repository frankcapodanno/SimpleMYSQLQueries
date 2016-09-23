/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package connectionAndQueries;

/**
 * This class return the uid for a data username
 * @author guru
 */
public class GetIdFromDB {
    
    public GetIdFromDB()
    {
    }
    
    /**
     * This method return the id From an Username
     * @param u
     * @return 
     */
    public String getIdFromUsername(String u)
    {
     GetFromDB gdb = new GetFromDB();
     return gdb.GetFromColumn(ConnectionDB.MAIN_TABLE_USERS, ConnectionDB.MAIN_ID_COLUMN
             , ConnectionDB.MAIN_USERNAME_COLUMN, u);    
    }    
}
