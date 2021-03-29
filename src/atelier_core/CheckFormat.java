package atelier_core;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class CheckFormat {
    public static boolean isDateValid(String dateToValidate){
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        sdf.setLenient(false);

        try {
            @SuppressWarnings("unused")
            Date date = sdf.parse(dateToValidate);

        } catch (ParseException e) {

            return false;
        }
        return true;
    }
    public static boolean isInteger( String input )
    {
        try
        {
            Integer.parseInt(input);
            return true;
        }
        catch( Exception e )
        {
            return false;
        }
    }
    public static boolean isFloat( String input )
    {
        try
        {
            Float.parseFloat(input);
            return true;
        }
        catch( Exception e )
        {
            return false;
        }
    }

}
