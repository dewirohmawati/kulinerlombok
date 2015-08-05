package kl.app.syamsul.com.kulinerlombok.helper;

/**
 * Created by syamsul on 03/08/15.
 */
public class TimeFormatter {
    public static String twoDigitFormat(String time){
        String[] x = time.split("\\.");
        String formattedTime;

        if(x.length > 1){
            String menit = (x[1].length() == 1) ? x[1] + "0" : x[1];
            formattedTime = (x[0].length() == 1) ? "0" + x[0] : x[0];
            formattedTime += "." + menit;
        } else {
            formattedTime = (x[0].length() == 1) ? "0" + x[0] : x[0];
            formattedTime += ".00";
        }

        return formattedTime;
    }
}
