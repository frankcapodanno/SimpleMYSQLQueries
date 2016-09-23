/*
 * Copyright Francesco Capodanno 2015. All rights reserved
 */
package connectionAndQueries;

/**
 * This define the Core DB and Core Table of the web application
 * @author Francesco Capodanno <Francesco at capodanno.click>
 */
public final class ConnectionDB {    
    static public final String CONNECTION_URL = "jdbc:mysql://localhost:3306/ergousersdb";
    static public final String CONNECTION_URL_BASIC = "jdbc:mysql://localhost:3306/";
    static public final String USER_DB = "testdb";
    static public final String PASS_DB = "simpletest1";      
    static public final String DRIVER_JDBC = "com.mysql.jdbc.Driver";
    static public final String DB_NAME = "test_connections";  
    static public final String MAIN_TABLE_USERS = "simple_data";
    static public final String MAIN_USERNAME_COLUMN = "uname";
    static public final String MAIN_ID_COLUMN = "id";
}
