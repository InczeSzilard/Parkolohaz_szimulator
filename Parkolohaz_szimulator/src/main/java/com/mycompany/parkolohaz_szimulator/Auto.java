
package com.mycompany.parkolohaz_szimulator;



/** Car: first 30 minutes free, 5 lei per started hour, max 40 lei per day. */
public class Auto extends Jarmu {

    private static final Tarifa TARIFA = new Tarifa(30, 5.0, 40.0);

    public Auto(String rendszam) {
        super(rendszam);
    }

    @Override
    public double dij(long percek) {
        return TARIFA.szamol(percek);
    }

    @Override
    public String getTipusNev() {
        return "Auto";
    }
}