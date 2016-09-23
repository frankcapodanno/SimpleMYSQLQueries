/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package simplemysqlqueries;

import connectionAndQueries.Insert1ValueNullId;
import models.SimpleData;

/**
 *
 * @author guru
 */
public class SimpleMYSQLQueries {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        System.out.println("This program collect a simple library for JDBC Connection on DB");
        System.out.println("Please before initalize the DB and change connectionAndQueries.ConnectionDB ");
        
        System.out.println("Test inseriment of utente_test ...");
        // new User Insert
        Insert1ValueNullId newUserInDb = new Insert1ValueNullId("utente_test",SimpleData.UNAME_COL, SimpleData.TABLE);
        if (newUserInDb.getError())
        {
            System.out.println("This query find an Error....");
            System.out.println(newUserInDb.getErrorString());
        } else { System.out.print("Nessun errore in questa query:");
        System.out.println(newUserInDb.getQuery());}
    }
    
}
