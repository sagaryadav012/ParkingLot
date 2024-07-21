package com.example.ParkingLot.Utils;

import java.util.Date;

public class DateTimeUtils {
    public static int calculateHours(Date entryDate, Date exitDate){
        long milliSeconds = exitDate.getTime() - entryDate.getTime();
        long seconds = milliSeconds/1000;
        return (int)Math.ceil((double)seconds/3600);
    }
}
