package com.base.util.reporter;

import com.base.util.Formatter;
import java.text.SimpleDateFormat;
import java.util.Calendar;

public class Reporter {


    public static void log(String s) {

            Calendar cal = Calendar.getInstance();
            SimpleDateFormat sdf = new SimpleDateFormat("H:mm:ss:SSS");
            sdf.format(cal.getTime());

            System.out.println(s);
            org.testng.Reporter.log("[" + sdf.format(cal.getTime()) + "]: " + Formatter.escapeCharacters(s) + "<br></br>");
    }
}
