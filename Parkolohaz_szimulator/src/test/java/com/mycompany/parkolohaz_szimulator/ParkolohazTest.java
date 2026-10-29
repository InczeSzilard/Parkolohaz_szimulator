package com.mycompany.parkolohaz_szimulator;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

// Parking garage tests.
public class ParkolohazTest {

    private AlithatoOra ora;
    private Parkolohaz haz;

    // Runs before every test, so each test starts with an empty garage.
    @BeforeEach
    void setUp() {
        ora = new AlithatoOra();
        haz = new Parkolohaz(3, 2, ora);   // 3 levels x 2 spots = 6 spots
    }

    @Test
    void teltHaz() {
        // fill all 6 spots
        for (int i = 1; i <= 6; i++) {
            haz.behajt(new Auto("MS0" + i + "AAA"));
        }
        // the 7th vehicle can't come in
        assertThrows(TeltHazException.class, () -> haz.behajt(new Motor("MS99MOT")));
    }

    @Test
    void ismeretlenRendszam() {
        assertThrows(RendszamException.class, () -> haz.kihajt("XX99ZZZ"));
    }

    @Test
    void duplikaltBehajtas() {
        haz.behajt(new Auto("MS12ABC"));
        assertThrows(DuplikaltException.class, () -> haz.behajt(new Auto("MS12ABC")));
    }

    @Test
    void kihajtasEsBevetel() {
        haz.behajt(new Auto("MS12ABC"));
        haz.behajt(new Motor("MS34MOT"));
        ora.tekerj(95);   // 95 minutes pass

        assertEquals(10.0, haz.kihajt("MS12ABC"), 0.001);   // 65 paid min = 2 hours x 5
        assertEquals(4.0, haz.kihajt("MS34MOT"), 0.001);    // 2 hours x 2
        assertEquals(14.0, haz.napiBevetel(), 0.001);
    }

    @Test
    void foglaltsag() {
        haz.behajt(new Auto("MS01AAA"));
        haz.behajt(new Auto("MS02AAA"));
        haz.behajt(new Kisbusz("MS03BUS"));

        int[] f = haz.foglaltsagSzintenkent();
        assertEquals(2, f[0]);   // level 1 is full
        assertEquals(1, f[1]);   // level 2 has one
        assertEquals(0, f[2]);   // level 3 is empty
    }
}