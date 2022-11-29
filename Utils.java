package vn.funix.fx17332.java.asm03.models;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class Utils {

    private String divider;
    private String title;
    private String dateTime;

    public Utils() {

    }

    public String getDivider() {
        return "+-----------+----------------------------+-----------+";
    }

    public String getTitle() {
        return "BIEN LAI GIAO DICH";
    }

    public String getDateTime() {
        DateFormat df = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
        Date today = Calendar.getInstance().getTime();
        return df.format(today);
    }

}
