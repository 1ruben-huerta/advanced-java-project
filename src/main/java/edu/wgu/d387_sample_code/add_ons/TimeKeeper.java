package edu.wgu.d387_sample_code.add_ons;

import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.Map;

@Service
public class TimeKeeper {

    public Map<String, String> timeZones() {
        ZoneId zEastern=ZoneId.of("America/New_York");
        ZoneId zMountain=ZoneId.of("America/Denver");
        ZoneId zUTC=ZoneId.of("UTC");
        ZoneId zoneId=ZoneId.systemDefault();

        LocalDateTime localDateTime=LocalDateTime.now();
        ZonedDateTime zonedDateTime=localDateTime.atZone(zoneId);

        ZonedDateTime zonedDateTimeUniversal=zonedDateTime.withZoneSameInstant(zUTC);
        String universalTime = zonedDateTimeUniversal.format(DateTimeFormatter.ofPattern("HH:mm"));

        ZonedDateTime zonedDateTimeEastern=zonedDateTime.withZoneSameInstant(zEastern);
        String easternTime = zonedDateTimeEastern.format(DateTimeFormatter.ofPattern("HH:mm"));

        ZonedDateTime zonedDateTimeMountain=zonedDateTime.withZoneSameInstant(zMountain);
        String mountainTime = zonedDateTimeMountain.format(DateTimeFormatter.ofPattern("HH:mm"));

        Map<String, String> allTimeZones = new HashMap<>();
        allTimeZones.put("UTC", universalTime);
        allTimeZones.put("ET", easternTime);
        allTimeZones.put("MT", mountainTime);

        return allTimeZones;
    }
}
