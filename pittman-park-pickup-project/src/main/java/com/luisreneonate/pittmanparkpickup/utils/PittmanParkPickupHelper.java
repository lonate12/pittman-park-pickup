package main.java.com.luisreneonate.pittmanparkpickup.utils;

import java.time.LocalDateTime;
import java.time.ZoneId;

public class PittmanParkPickupHelper {
    public static String localDateTimeToMilli(LocalDateTime dt) {
        long timeInMilli = dt.atZone(ZoneId.of("America/New_York")).toInstant().toEpochMilli();
        return String.valueOf(timeInMilli);
    }
}
