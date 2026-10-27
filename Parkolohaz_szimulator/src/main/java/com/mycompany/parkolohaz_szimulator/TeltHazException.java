package com.mycompany.parkolohaz_szimulator;

public class TeltHazException extends RuntimeException {
    public TeltHazException() {
        super("Telt haz, nincs szabad hely");
    }
}