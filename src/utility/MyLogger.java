package utility;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.logging.FileHandler;
import java.util.logging.Level;
import java.util.logging.Logger;

public class MyLogger {
	public static void log(Exception e)
	 {
		//Logger object create
		Logger logger = Logger.getLogger(MyLogger.class.getName());
		FileHandler fh = null;
		 try 
		 { 
			 //Date and Time formatter
			 SimpleDateFormat format = new SimpleDateFormat("yyy-MM-dd_HH-mm-ss");
			 //File name create
			 fh = new FileHandler("MyLogFile_"
					 + format.format(Calendar.getInstance().getTime()) 
					 +".log", true);
			 logger.addHandler(fh);
		     fh.setFormatter(new MyFormatter());
			 //Setup ALL type
			 logger.setLevel(Level.ALL);
			 logger.log(Level.ALL,e.toString());
			 //File close
			 fh.close(); 
		 } 
		 catch (Exception ex) 
		 { 
			 System.out.println("Exception thrown: " + ex); 
			 ex.printStackTrace(); 
		 }
	 }
}
