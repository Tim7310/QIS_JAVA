/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package files;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;
/**
 *
 * @author QPDLENOVO1
 */
public class ToCalender {

    public static void main(String[] args) {
        Calendar cal = Calendar.getInstance();
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy", Locale.getDefault());
        try {
            cal.setTime(sdf.parse("18/01/2015"));
        }
        catch (ParseException e) {
            e.printStackTrace();
        }
        System.out.println("dob : " + cal.getTime());
    }

}
