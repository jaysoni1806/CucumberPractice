package exmple;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class GetDate {

   public static void main(String[] abc){
       SimpleDateFormat format = new SimpleDateFormat("MM/dd/yyyy");
       Date date = new Date();
      // String currentDate = format.format(date);

       // Create Calendar instance and add 2 days
       Calendar cal = Calendar.getInstance();
       cal.setTime(date);
       cal.add(Calendar.DAY_OF_MONTH, 6);

       // Format the new date
       String dateAfterTwoDays = format.format(cal.getTime());
       System.out.println(dateAfterTwoDays);
   }


}
