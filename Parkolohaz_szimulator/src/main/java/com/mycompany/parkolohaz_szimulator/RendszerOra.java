package com.mycompany.parkolohaz_szimulator;

// The real clock: current system time in minutes.
public class RendszerOra implements Ora {

    @Override
    public long most() {
        return System.currentTimeMillis() / 60000;
    }
}