package ztesting;
import java.util.Date;
import java.text.SimpleDateFormat;
import java.util.Calendar;

/**
 * SimpleDateFormat example: Convert from a Date to a formatted String
 *
 * Get today's date,
 * then convert it to a String, 
 * using the date format we specify.
 */
public class dateTest {
  public static void main(String[] args)
  {
    // (1) get today's date
    Date today = Calendar.getInstance().getTime();

    // (2) create our "formatter"
    SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");//-hh.mm.ss

    // (3) create a new String in the format we want
    String folderName = formatter.format(today);
    
    // (4) this prints "Folder Name = 2009-09-06-08.23.23"
    System.out.println("Folder Name = " + folderName);
  }
}
