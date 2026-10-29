package com.mycompany.parkolohaz_szimulator;

import java.util.HashMap;
import java.util.Map;

/**
 * The parking garage. It has levels, and each level has the same number of spots.
 * It handles vehicles entering and leaving, and counts the income.
 */
public class Parkolohaz {

    // How many spots are taken on each level.
    // Example: foglalt[0] = 5 means 5 vehicles are on the first level.
    private final int[] foglalt;

    // How many spots one level has (every level is the same size).
    private final int helyekSzintenkent;

    // Where we get the time from (real clock in the program, fake clock in tests).
    private final Ora ora;

    // Three "tables". In each one the key is the license plate.
    private final Map<String, Jarmu> jarmuvek = new HashMap<>();   // plate -> the vehicle itself
    private final Map<String, Long> erkezes = new HashMap<>();     // plate -> when it arrived (minutes)
    private final Map<String, Integer> szintje = new HashMap<>();  // plate -> which level it's on

    // All the money collected so far.
    private double bevetel = 0;

    /*
     * Creates the garage.
     *number of levels
     *number of spots on each level
     *the clock to use
     */
        public Parkolohaz(int szintek, int helyekSzintenkent, Ora ora) {
        // A garage needs at least 1 level and 1 spot, and a clock.          // NEW
        if (szintek <= 0 || helyekSzintenkent <= 0 || ora == null) {        // NEW
            throw new IllegalArgumentException("Hibas parkolohaz adatok");   // NEW
        }                                                                    // NEW

        this.foglalt = new int[szintek];   // all levels start empty (0)
        this.helyekSzintenkent = helyekSzintenkent;
        this.ora = ora;
    }

    /**
     * A vehicle arrives at the barrier.
     * the level number where it parked (1, 2 or 3)
     */
     public int behajt(Jarmu j) {
        // Nothing to park.                                                  // NEW
        if (j == null) {                                                     // NEW
            throw new IllegalArgumentException("A jarmu nem lehet null");    // NEW
        }                                                                    // NEW

        String r = j.getRendszam();

        // The same plate can't be inside twice.
        if (jarmuvek.containsKey(r)) {
            throw new DuplikaltException(r);
        }

        // Check the levels from the bottom up.
        for (int sz = 0; sz < foglalt.length; sz++) {

            // Is there still a free spot on this level?
            if (foglalt[sz] < helyekSzintenkent) {
                foglalt[sz]++;                   // one more spot is taken here
                jarmuvek.put(r, j);              // remember the vehicle
                erkezes.put(r, ora.most());      // remember when it came in
                szintje.put(r, sz);              // remember which level it's on
                return sz + 1;                   // arrays start at 0, people count from 1
            }
        }

        // We checked every level and found no free spot.
        throw new TeltHazException();
    }

    /**
     * A vehicle leaves the garage.
     * the fee it has to pay, in lei
     */
        public double kihajt(String rendszam) {
        // No plate given.
        if (rendszam == null) {
            throw new RendszamException("null");
        }

        // Same format as in Jarmu, so "ms12abc" finds "MS12ABC".
        String r = rendszam.trim().toUpperCase();

        // Look up the vehicle. If it's not here, the plate is unknown.
        Jarmu j = jarmuvek.get(r);
        if (j == null) {
            throw new RendszamException(r);
        }

        // How long did it stay? Now minus the arrival time.
        long percek = ora.most() - erkezes.get(r);

        // The vehicle calculates its own fee (Auto, Motor, Kisbusz... each in its own way).
        // No instanceof or switch needed: this is polymorphism.
        double dij = j.dij(percek);

        // Free the spot on its level.
        foglalt[szintje.get(r)]--;

        // Forget everything about this vehicle.
        jarmuvek.remove(r);
        erkezes.remove(r);
        szintje.remove(r);

        // Add the fee to the income and give it back to the menu.
        bevetel += dij;
        return dij;
    }

    /** The money collected since the program started (the program runs for one day). */
    public double napiBevetel() {
        return bevetel;
    }

    /**
     * How many spots are taken on each level.
     * We return a copy, so whoever gets it can't change our real data.
     */
    public int[] foglaltsagSzintenkent() {
        return foglalt.clone();
    }

    /** How many spots one level has (the menu needs it to print "3 / 20"). */
    public int getHelyekSzintenkent() {
        return helyekSzintenkent;
    }
}