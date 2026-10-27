package com.mycompany.parkolohaz_szimulator;

/**
 * A simple tariff: the first X minutes are free, after that every started
 * hour is paid, but one day never costs more than the daily cap.
 * For multi-day parking every full day costs the cap, and the remaining
 * part of the last day is calculated separately.
 *
 * It's a separate class so the same formula isn't copied into every vehicle.
 */
final class Tarifa {

    static final long PERC_NAPONTA = 24 * 60;

    private final long ingyenesPerc;
    private final double oradij;
    private final double napiPlafon;

    Tarifa(long ingyenesPerc, double oradij, double napiPlafon) {
        if (ingyenesPerc < 0 || oradij < 0 || napiPlafon < 0) {
            throw new IllegalArgumentException("A tarifa ertekei nem lehetnek negativak");
        }
        this.ingyenesPerc = ingyenesPerc;
        this.oradij = oradij;
        this.napiPlafon = napiPlafon;
    }

     double szamol(long percek) {
        if (percek < 0) {
            throw new IllegalArgumentException("A parkolasi ido nem lehet negativ");
        }
        if (percek <= ingyenesPerc) {
            return 0;
        }
        long fizetosPerc = percek - ingyenesPerc;
        long orak = (long) Math.ceil(fizetosPerc / 60.0);   // every started hour counts
        return Math.min(orak * oradij, napiPlafon);
    }
}
