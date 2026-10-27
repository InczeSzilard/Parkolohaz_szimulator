package com.mycompany.parkolohaz_szimulator;

/**
 * Electric car. Parking costs the same as for a normal car,
 * but if it charges, the electricity is paid separately.
 *
 * The charger is 11 kW, 1.5 lei per kWh, and the battery takes at most
 * 60 kWh (after that it's full and no more energy is billed).
 */
public class ElektromosAuto extends Auto {

    private static final double TOLTO_KW = 11.0;
    private static final double AR_KWH = 1.5;
    private static final double MAX_KWH = 60.0;

    private final boolean tolt;

    public ElektromosAuto(String rendszam, boolean tolt) {
        super(rendszam);
        this.tolt = tolt;
    }

    public boolean isTolt() {
        return tolt;
    }

    @Override
    public double dij(long percek) {
        // parking fee comes from Auto, charging is added on top
        double parkolas = super.dij(percek);
        return kerekit(parkolas + toltesDij(percek));
    }

    double toltesDij(long percek) {
        if (!tolt) {
            return 0;
        }
        double kwh = Math.min(percek / 60.0 * TOLTO_KW, MAX_KWH);
        return kwh * AR_KWH;
    }

    // round to 2 decimals, because double values like 1.5 * 17.41 aren't exact
    private static double kerekit(double ertek) {
        return Math.round(ertek * 100) / 100.0;
    }

    @Override
    public String getTipusNev() {
        return "Elektromos auto";
    }
}