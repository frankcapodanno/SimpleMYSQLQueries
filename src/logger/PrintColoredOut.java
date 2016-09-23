/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package logger;

/**
 *
 * @author guru
 */


public class PrintColoredOut {
    
public static final String ANSI_RESET = "\033[0m";
public static final String ANSI_BLACK = "\u001B[30m ";
public static final String ANSI_RED = "\033[32;1m";
public static final String ANSI_GREEN = "\033[32;1m";
public static final String ANSI_YELLOW = "\u001B[33m ";
public static final String ANSI_BLUE = "\u001B[34m ";
public static final String ANSI_PURPLE = "\u001B[35m ";
public static final String ANSI_CYAN = "\u001B[36m ";
public static final String ANSI_WHITE = "\u001B[37m ";

private String start;

public PrintColoredOut(String s)
{ 
    start = ANSI_RESET;
   
}

    public PrintColoredOut() {
        start = ANSI_RESET;
    }

public void outln(String s)
{
  System.out.println(start + s + ANSI_RESET);
}

public void out(String s)
{
  System.out.print(start + s + ANSI_RESET);
}

public void setColor(Colors c)
{
  switch (c){case BLACK:
      start = ANSI_BLACK;
            break;
        case RED:
      start = ANSI_RED;
      System.out.println("Ansi RED selected");
            break;
        case GREEN:
       start = ANSI_GREEN; 
       
            break;
        case YELLOW:
       start = ANSI_YELLOW;
            break;
        case PURPLE:
        start = ANSI_PURPLE;
            break;
        case CYAN:
        start = ANSI_CYAN;
            break;
        case WHITE:
            start = ANSI_WHITE;  
           
            break;
        default:
            start = ANSI_RESET;
}

}
    
}
