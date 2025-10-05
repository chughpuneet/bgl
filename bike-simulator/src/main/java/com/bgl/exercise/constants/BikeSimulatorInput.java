package com.bgl.exercise.constants;

import java.util.regex.Pattern;

public enum BikeSimulatorInput {
    PLACE("^\\s*PLACE\\s+(\\d+)\\s*,\\s*(\\d+)\\s*,\\s*(NORTH|SOUTH|EAST|WEST)\\s*$"),
    FORWARD("^\\s*FORWARD\\s*$"),
    TURN_LEFT("^\\s*TURN_LEFT\\s*$"),
    TURN_RIGHT("^\\s*TURN_RIGHT\\s*$"),
    GPS_REPORT("^\\s*GPS_REPORT\\s*$");

    private Pattern inputPattern;
    BikeSimulatorInput(String patternRegex) {
        this.inputPattern = Pattern.compile(patternRegex, Pattern.CASE_INSENSITIVE);
    }

    public Pattern getInputPattern() {
        return inputPattern;
    }
}
