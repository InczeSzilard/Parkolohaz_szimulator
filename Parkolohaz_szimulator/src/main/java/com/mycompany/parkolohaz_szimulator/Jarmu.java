
package com.mycompany.parkolohaz_szimulator;


/**
 * Minden jármű közös őse. A díjszámítást az alosztályok adják meg,
 * a parkolóház csak a dij() metódust hívja, nem foglalkozik a típussal.
 */
public abstract class Jarmu {

    private final String rendszam;

    protected Jarmu(String rendszam) {
        this.rendszam = normalizal(rendszam);
    }

    public String getRendszam() {
        return rendszam;
    }

    /** Díj lejben a parkolt percek alapján. */
    public abstract double dij(long percek);

    /** Emberi név a kiíráshoz és a statisztikához (pl. "Autó"). */
    public abstract String getTipusNev();

    /**
     * Rendszám ellenőrzése és egységes alakra hozása.
     * Szóközt és kötőjelet kiveszünk, nagybetűsítünk, így az "ms-12-abc"
     * és az "MS 12 ABC" ugyanaz lesz.
     */
    static String normalizal(String rendszam) {
        if (rendszam == null) {
            throw new IllegalArgumentException("A rendszám nem lehet null");
        }
        String r = rendszam.replaceAll("[\\s-]", "").toUpperCase();
        if (r.isEmpty()) {
            throw new IllegalArgumentException("A rendszám nem lehet üres");
        }
        if (!r.matches("[A-Z0-9]{2,10}")) {
            throw new IllegalArgumentException("Érvénytelen rendszám: " + rendszam);
        }
        return r;
    }

    @Override
    public String toString() {
        return getTipusNev() + " (" + rendszam + ")";
    }
}