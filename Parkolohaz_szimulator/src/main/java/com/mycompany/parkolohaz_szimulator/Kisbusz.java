package com.mycompany.parkolohaz_szimulator;

/**
 * Minibus: takes up more space, so it's more expensive.
 * Only 15 minutes free, 8 lei per started hour, max 60 lei per day.
 */
public class Kisbusz extends Jarmu {

    private static final Tarifa TARIFA = new Tarifa(15, 8.0, 60.0);

    public Kisbusz(String rendszam) {
        super(rendszam);
    }

    @Override
    public double dij(long percek) {
        return TARIFA.szamol(percek);
    }

    @Override
    public String getTipusNev() {
        return "Kisbusz";
    }
}