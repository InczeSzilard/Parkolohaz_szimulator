package com.mycompany.parkolohaz_szimulator;

/*
 * Common base class of all vehicles. Each subclass defines its own fee,
 * the parking garage only calls dij() and never checks the vehicle type.
 */
public abstract class Jarmu {

    private final String rendszam;

    protected Jarmu(String rendszam) {
        this.rendszam = normalizal(rendszam);
    }

    public String getRendszam() {
        return rendszam;
    }

    /* Fee in lei, based on the number of parked minutes. */
    public abstract double dij(long percek);

    /* Human readable type name for printing and statistics. */
    public abstract String getTipusNev();

    /*
     * Validates the license plate and brings it to a common format.
     * Spaces and dashes are removed and letters are uppercased,
     * so "ms-12-abc" and "MS 12 ABC" both become "MS12ABC".
     */
   static String normalizal(String rendszam) {
        if (rendszam == null || rendszam.trim().isEmpty()) {
            throw new IllegalArgumentException("A rendszam nem lehet ures");
        }
        return rendszam.trim().toUpperCase();
    }

    @Override
    public String toString() {
        return getTipusNev() + " (" + rendszam + ")";
    }
}