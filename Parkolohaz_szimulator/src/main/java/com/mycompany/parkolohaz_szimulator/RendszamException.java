package com.mycompany.parkolohaz_szimulator;

public class RendszamException extends RuntimeException {
    public RendszamException(String rendszam) {
        super("Nincs bent ilyen rendszam: " + rendszam);
    }
}