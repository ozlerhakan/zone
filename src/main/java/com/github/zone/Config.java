package com.github.zone;

import de.tototec.cmdoption.CmdOption;


/**
 * Created by hakan on 27/01/2017.
 */
public class Config {

    @CmdOption(names = {"--help"}, description = "Show this help", isHelp = true)
    private boolean help;

    @CmdOption(names = {"--hour", "-h"}, args = {"hour"}, description = "Add hour e.g. 2,3,4")
    private String h;

    @CmdOption(names = {"--period", "-p"}, args = {"period"}, description = "Add the period of the specified hour, i.e. AM or PM")
    private String time;

    @CmdOption(names = {"--zone", "-z"}, args = {"zone"}, description = "Add time zone to which the hour and period belong, e.g. CET")
    private String zone;

    @CmdOption(names = {"--local", "-l"}, args = {"local"}, description = "Override the local time zone, default is GMT+3")
    private String localZone;

    public String getHour() {
        return h;
    }

    public boolean isHelp() {
        return help;
    }

    public String getTime() {
        return time;
    }

    public String getZone() {
        return zone;
    }

    public String getLocalZone() {
        return localZone;
    }
}
