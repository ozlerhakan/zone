package com.github.zone;

import de.tototec.cmdoption.CmdlineParser;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.TimeZone;

/**
 * Created by hakan on 27/01/2017.
 */
public class Zone {

    private final DateTimeFormatter date12Format = DateTimeFormatter.ofPattern("yyyy-MM-dd hh:mm a");
    private final Config config;

    public static void main(String[] args) {

        final Config config = new Config();
        final CmdlineParser cp = new CmdlineParser(config);
        cp.setProgramName("zone");
        cp.parse(args);

        if (config.isHelp()) {
            cp.usage();
            System.exit(0);
        }

        try {
            Zone zone = new Zone(config);
            zone.find();
        } catch (Exception e) {
            System.err.println(e.getMessage());
            System.exit(0);
        }

    }

    private void printResult(DateTimeFormatter date12Format, LocalDateTime targetTime, LocalDateTime localDateTime) {
        System.out.print("In your local time: ");
        System.out.print("(" + targetTime.format(date12Format) + ")");
        System.out.print(" is ");
        System.out.println("(" + localDateTime.format(date12Format) + ")");
    }

    private Zone(final Config config) {
        this.config = config;
    }

    public void find() {
        int hour = Integer.parseInt(config.getHour());
        if (config.getTime().toLowerCase().equals("PM".toLowerCase())) hour += 12;

        LocalDateTime targetTime = LocalDateTime.now();
        targetTime = LocalDateTime.of(targetTime.getYear(), targetTime.getMonth(), targetTime.getDayOfMonth(), hour, 0);

        final ZonedDateTime zdt = targetTime.atZone(ZoneId.of(config.getZone()));
        //-Duser.timezone=GMT+3
        String zoneStr = config.getLocalZone() == null || config.getLocalZone().equals("") ? "GMT+3" : config.getLocalZone();
        final TimeZone zone = TimeZone.getTimeZone(zoneStr);
        LocalDateTime localDateTime = zdt.withZoneSameInstant(zone.toZoneId()).toLocalDateTime();

        printResult(date12Format, targetTime, localDateTime);
    }
}
