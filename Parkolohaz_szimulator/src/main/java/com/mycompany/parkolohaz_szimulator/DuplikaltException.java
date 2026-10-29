package com.mycompany.parkolohaz_szimulator;

public class DuplikaltException extends RuntimeException {
    public DuplikaltException(String rendszam) {
        super("Ez a jarmu mar bent van: " + rendszam);
    }
}