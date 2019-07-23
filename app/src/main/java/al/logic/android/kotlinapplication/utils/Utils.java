package al.logic.android.kotlinapplication.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.GregorianCalendar;
import java.util.Locale;

public class Utils {

    private static String defaultFormat = "yyyy-MM-dd'T'HH:mm:ss'Z'";
    public static String registrationFormat = "dd/MM/yyyy HH:mm:ss";

    /**
     * @param time   human readable time
     * @param format the requred format string
     * @return returns a human readable string for specified gregorian calendar instance
     * @throws ParseException format is incorrect or
     */
    public static GregorianCalendar stringToCalendar(String time, String format) throws ParseException {
        SimpleDateFormat f = new SimpleDateFormat(format, Locale.US);
        GregorianCalendar cal = new GregorianCalendar();
        cal.setTime(f.parse(time));
        return cal;
    }


    public static GregorianCalendar stringToCalendar(String time) throws ParseException {
        return stringToCalendar(time, defaultFormat);
    }

    /**
     * @param cal GregorianCalendar instance to convert to String
     * @return Date formatted with the default format
     */
    public static String calendarToString(GregorianCalendar cal) {
        return calendarToString(cal, defaultFormat);
    }

    /**
     * @param cal    GregorianCalendar instance to convert to String
     * @param format The date-format to use for converting
     * @return Date string formatted with the given format
     */
    public static String calendarToString(GregorianCalendar cal, String format) {
        SimpleDateFormat f = new SimpleDateFormat(format, Locale.US);
        if (cal == null)
            return "";
        return f.format(cal.getTime());
    }

}
