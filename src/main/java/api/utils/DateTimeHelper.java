package api.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;
import java.util.concurrent.TimeUnit;

/**
 * Created by maxim on 1/21/2017.
 */
public class DateTimeHelper {
    public static String getDateTime(int numberOfDays) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(new Date());
        cal.add(Calendar.DATE, numberOfDays);
        cal.set(Calendar.SECOND, 0);
        Date date = cal.getTime();

        SimpleDateFormat isoFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss");
        String dateNewFormat = isoFormat.format(date);

        return dateNewFormat;
    }

    public static String getDateTimeForFileName() {
        Date date = new Date();
        SimpleDateFormat isoFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH-mm-ss-SSS");
        String dateNewFormat = isoFormat.format(date);

        return dateNewFormat;
    }

    public static String formatDate(String dateTime, String formatIn, String formatOut) {
        String dateNewFormat = null;
        try {
            SimpleDateFormat baseDateFormat = new SimpleDateFormat(formatIn);
            Date date = baseDateFormat.parse(dateTime);
            SimpleDateFormat newFormat = new SimpleDateFormat(formatOut, Locale.ENGLISH);
            dateNewFormat = newFormat.format(date);
        } catch (ParseException e) {
            ReportWriter.logException("ParseException: " + e.getMessage());
        }
        return dateNewFormat;
    }

    public static String getDateTime(int numberOfDays, String formatOut) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(new Date());
        cal.add(Calendar.DATE, numberOfDays);
        cal.set(Calendar.SECOND, 0);
        Date date = cal.getTime();

        SimpleDateFormat isoFormat = new SimpleDateFormat(formatOut, Locale.ENGLISH);
        String dateNewFormat = isoFormat.format(date);

        return dateNewFormat;
    }

    public static String getHourBackTime(int numberOfHours, String formatOut) {
        Date date = new Date(System.currentTimeMillis() - TimeUnit.HOURS.toMillis(numberOfHours));
        SimpleDateFormat isoFormat = new SimpleDateFormat(formatOut, Locale.ENGLISH);
        String dateNewFormat = isoFormat.format(date);

        return dateNewFormat;
    }

    public static String getHourAndMinuteForwardTime(int numberOfHours, String formatOut) {
        Date date = new Date(System.currentTimeMillis() + TimeUnit.HOURS.toMillis(numberOfHours) + TimeUnit.MINUTES.toMillis(1));
        SimpleDateFormat isoFormat = new SimpleDateFormat(formatOut, Locale.ENGLISH);
        String dateNewFormat = isoFormat.format(date);

        return dateNewFormat;
    }

    public static String getDateTime(String formatOut) {
        Date date = new Date();
        SimpleDateFormat isoFormat = new SimpleDateFormat(formatOut, Locale.ENGLISH);
        String dateNewFormat = isoFormat.format(date);

        return dateNewFormat;
    }

    private static Date getDate(String time) {
        Calendar cal = Calendar.getInstance();
        cal.set(cal.get(Calendar.YEAR), cal.get(Calendar.MONTH), cal.get(Calendar.DATE), Integer.valueOf(time.substring(0, 2)), Integer.valueOf(time.substring(3, 5)));

        return cal.getTime();
    }

    public static boolean compareDateWithCurrent_1min_accuracy(String actualDate) {
        long actualDateLong = getDate(actualDate).getTime();
        long expectedDateLong = (new Date()).getTime();
        long difference = Math.abs(expectedDateLong - actualDateLong);

        if(difference > 60000){
            ReportWriter.logError("More then 1 minute between dates");
            return false;
        }

        return true;
    }
}