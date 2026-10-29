package com.mycompany.parkolohaz_szimulator;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Test;

// Fee calculation tests. Car: 30 min free, 5 lei per started hour, max 40 lei.
public class DijTest {

    // allowed difference when comparing double numbers
    private static final double DELTA = 0.001;

    @Test
    void nullaPerc() {
        assertEquals(0.0, new Auto("MS12ABC").dij(0), DELTA);
    }

    @Test
    void harmincPerc() {
        // exactly 30 minutes is still free
        assertEquals(0.0, new Auto("MS12ABC").dij(30), DELTA);
    }

    @Test
    void harmincegyPerc() {
        // 1 paid minute = 1 started hour = 5 lei
        assertEquals(5.0, new Auto("MS12ABC").dij(31), DELTA);
    }

    @Test
    void napiPlafon() {
        // 10 hours would be 50 lei, but the daily max is 40
        assertEquals(40.0, new Auto("MS12ABC").dij(600), DELTA);
    }

    @Test
    void motorEsKisbusz() {
        assertEquals(2.0, new Motor("MS34MOT").dij(31), DELTA);
        assertEquals(8.0, new Kisbusz("MS56BUS").dij(16), DELTA);   // only 15 min free
    }

    @Test
    void elektromosAuto() {
        // 60 minutes: 5 lei parking + 11 kWh x 1.5 lei = 16.5 lei charging
        assertEquals(21.5, new ElektromosAuto("MS07ECO", true).dij(60), DELTA);
        // without charging it's the same as a normal car
        assertEquals(5.0, new ElektromosAuto("MS07ECO", false).dij(60), DELTA);
    }

    @Test
    void uresRendszam() {
        assertThrows(IllegalArgumentException.class, () -> new Auto(null));
        assertThrows(IllegalArgumentException.class, () -> new Auto(""));
    }
}