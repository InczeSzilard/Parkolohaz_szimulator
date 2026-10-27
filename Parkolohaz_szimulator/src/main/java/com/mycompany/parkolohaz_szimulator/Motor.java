package com.mycompany.parkolohaz_szimulator;

/** Motorcycle: first 30 minutes free, 2 lei per started hour, max 12 lei per day. */
public class Motor extends Jarmu {

    private static final Tarifa TARIFA = new Tarifa(30, 2.0, 12.0);

    public Motor(String rendszam) {
        super(rendszam);
    }

    @Override
    public double dij(long percek) {
        return TARIFA.szamol(percek);
    }

    @Override
    public String getTipusNev() {
        return "Motor";
    }
}