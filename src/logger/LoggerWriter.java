/*
 * Copyright Francesco Capodanno 2015. All rights reserved
 */
package logger;

import connectionAndQueries.GetFromDBs;
import connectionAndQueries.GetLastFromDBs;
import connectionAndQueries.InsertInDBs;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

/**
 * This class write several Log information on database log_ergodb simple Table
 * @author guru
 */
public class LoggerWriter {
    
    private TypeLog levelLog;
    private String date,time;
    private boolean datetime;
    private static List<String> columns_log;
    private String table;
    
    private static final String ID = "id";
    private static final String ENTRY_COLUMN = "entry";
    /**
     * low importance  : session listener 
     */
    private static final String SIMPLE_TABLE = "simple";
    /**
     * medium importance: warnings or problems of performance
     */
    private static final String SIMPLE_MEDIUM_TABLE = "medium_simple";
    /**
     * high importance : grave errors 
     */
    private static final String SIMPLE_HIGH_TABLE = "high_simple";
    
    private static final String NAME_DB_LOG ="log_ergodb";
    
    public LoggerWriter()
    {
        datetime = true;
         // default is low the level of log 
        setLevelLow();
        columns_log = new ArrayList();
        columns_log.add(ENTRY_COLUMN);        
    }
    
    private void setLevelLow()
    {
        setLevel(TypeLog.LOW);
    }
    
    public void setLevel(TypeLog l)
    {
        levelLog = l;
        switch (l)
        {
            case LOW:
                    table = SIMPLE_TABLE;
                    break;
            case MEDIUM:
                  table = SIMPLE_MEDIUM_TABLE;
                break;
            case HIGH:
                 table = SIMPLE_HIGH_TABLE;
                break;
        }
    }
    
    /**
     * Set a particolar date
     * @param s date converted in String
     */
    public void setDate(String s)
    {
        date = s;
    }
    
    /**
    * Set a particolar time    
     * @param t time in String
    */
    public void setTime(String t)
    {
        time = t;
    }
    
    public void write(String s)
    {
     
      InsertInDBs insert = new InsertInDBs();
      List<String> elements_to_insert = new ArrayList();
  
  if (datetime == false)
  { 
   elements_to_insert.add(s);         
  } else
  {
     if (date == null)
      {
          setDefaultDate();
      }
      
      if (time == null)
      {
          setDefaultTime();
      }
    StringBuilder build_s = new StringBuilder();
    build_s.append(date).append( "  ").append (time).append(": ").append(s);
    elements_to_insert.add(build_s.toString());    
  }
  insert.insertData(columns_log, elements_to_insert, NAME_DB_LOG, table); 
  
    }
    
    /**
     *no automatically date for the logger is insert
     */
    public void nodatetime()
    {
        datetime = false;
    }
    
    /**
    get all the High Urgent Logs
     * @return a List of String that rappresents all the HighLogs.
    */
    public List<String> getHighLogs()
    {     
     GetLastFromDBs getLast = new GetLastFromDBs();
     String slast = getLast.getLast(NAME_DB_LOG, SIMPLE_HIGH_TABLE, ID);
     int last = 0;
     try {last = Integer.parseInt(slast);} catch (Exception ex){
         this.write("Logger Error: " + ex.getMessage());}    
     
     return getResults(SIMPLE_HIGH_TABLE,last);
    }
    
    public List<String> getMediumLogs()
    {
      GetLastFromDBs getLast = new GetLastFromDBs();
     String slast = getLast.getLast(NAME_DB_LOG, SIMPLE_MEDIUM_TABLE, ID);
     int last = 0;
     try {last = Integer.parseInt(slast);} catch (Exception ex){
         this.write("Logger Error: " + ex.getMessage());}    
     
     return getResults(SIMPLE_MEDIUM_TABLE,last);  
    }
    
    public List<String> getLowLogs()
    {
      GetLastFromDBs getLast = new GetLastFromDBs();
     String slast = getLast.getLast(NAME_DB_LOG, SIMPLE_TABLE, ID);
     int last = 0;
     try {last = Integer.parseInt(slast);} catch (Exception ex){
         this.write("Logger Error: " + ex.getMessage());}    
     
     return getResults(SIMPLE_TABLE,last);  
    }
    
    private void setDefaultDate()
    {
        LocalDate d_date = LocalDate.now();   
        date = d_date.toString();
    }
    
    private void setDefaultTime()
    {
       LocalTime d_time = LocalTime.now();
       time = d_time.toString();
    }    
    
    private List<String> getResults(String table, int last)            
    {
     List<String> results = new ArrayList();
     if (last >= 1)
     {
         for (int i=1; i<=last; i++)
         {
          GetFromDBs get = new GetFromDBs();   
          results.add(get.getFromColumn(NAME_DB_LOG, table, ENTRY_COLUMN, ID, Integer.toString(i)));                   
         }
     } 
     return results;
        
    }
}
