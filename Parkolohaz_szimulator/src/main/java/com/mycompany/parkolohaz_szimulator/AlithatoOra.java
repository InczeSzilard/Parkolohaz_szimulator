package com.mycompany.parkolohaz_szimulator;

// A clock we can move forward by hand (for tests).
public class AlithatoOra implements Ora {

    private long perc = 0;

    @Override
    public long most() {
        return perc;
    }

    public void tekerj(long percek) {
        perc += percek;
    }
}