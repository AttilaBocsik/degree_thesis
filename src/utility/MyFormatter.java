package utility;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.logging.Formatter;
import java.util.logging.LogRecord;

public class MyFormatter extends Formatter{
	@Override
	public String format(LogRecord record) {
		//Date and Time format
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy.MM.dd HH:mm:ss");
		return (dateFormat.format(new Date()) + " - " +//Date and Time format +
		record.getMessage()+ "\n"); //Message + new row		
	}
}
