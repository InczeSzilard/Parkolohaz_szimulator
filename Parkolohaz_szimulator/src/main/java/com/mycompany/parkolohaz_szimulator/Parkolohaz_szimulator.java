package com.mycompany.parkolohaz_szimulator;

import java.util.Scanner;

public class Parkolohaz_szimulator {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        // A clock we can move forward from the menu, so the fees can be tried out.
        AlithatoOra ora = new AlithatoOra();
        Parkolohaz haz = new Parkolohaz(3, 20, ora);   // 3 levels x 20 spots

        while (true) {
            System.out.println();
            System.out.println("=== Parkolohaz ===");
            System.out.println("1 - Behajtas");
            System.out.println("2 - Kihajtas");
            System.out.println("3 - Foglaltsag szintenkent");
            System.out.println("4 - Napi bevetel");
            System.out.println("5 - Ido tekerese");
            System.out.println("0 - Kilepes");
            System.out.print("Valasztas: ");
            String valasz = sc.nextLine().trim();

            // Every error from the garage ends up here, so the program doesn't stop.
            try {
                if (valasz.equals("1")) {
                    behajtas(sc, haz);
                } else if (valasz.equals("2")) {
                    System.out.print("Rendszam: ");
                    double dij = haz.kihajt(sc.nextLine());
                    System.out.println("Fizetendo: " + dij + " lej");
                } else if (valasz.equals("3")) {
                    int[] f = haz.foglaltsagSzintenkent();
                    for (int i = 0; i < f.length; i++) {
                        System.out.println((i + 1) + ". szint: " + f[i] + " / " + haz.getHelyekSzintenkent());
                    }
                } else if (valasz.equals("4")) {
                    System.out.println("Mai bevetel: " + haz.napiBevetel() + " lej");
                } else if (valasz.equals("5")) {
                    System.out.print("Hany perc teljen el? ");
                    long perc = Long.parseLong(sc.nextLine().trim());
                    ora.tekerj(perc);
                    System.out.println("Eltelt " + perc + " perc.");
                } else if (valasz.equals("0")) {
                    System.out.println("Viszlat!");
                    break;
                } else {
                    System.out.println("Nincs ilyen menupont.");
                }
            } catch (NumberFormatException e) {
                System.out.println("HIBA: szamot adj meg.");
            } catch (RuntimeException e) {
                System.out.println("HIBA: " + e.getMessage());
            }
        }
    }

    // Asks for the type and the plate, creates the vehicle and lets it in.
    private static void behajtas(Scanner sc, Parkolohaz haz) {
        System.out.println("1 - Auto");
        System.out.println("2 - Motor");
        System.out.println("3 - Kisbusz");
        System.out.println("4 - Elektromos auto (tolt)");
        System.out.println("5 - Elektromos auto (nem tolt)");
        System.out.print("Tipus: ");
        String tipus = sc.nextLine().trim();
        System.out.print("Rendszam: ");
        String rendszam = sc.nextLine();

        // Here we only decide which object to create.
        // The garage itself never checks the type.
        Jarmu j;
        if (tipus.equals("1")) {
            j = new Auto(rendszam);
        } else if (tipus.equals("2")) {
            j = new Motor(rendszam);
        } else if (tipus.equals("3")) {
            j = new Kisbusz(rendszam);
        } else if (tipus.equals("4")) {
            j = new ElektromosAuto(rendszam, true);
        } else if (tipus.equals("5")) {
            j = new ElektromosAuto(rendszam, false);
        } else {
            System.out.println("Nincs ilyen tipus.");
            return;
        }

        int szint = haz.behajt(j);
        System.out.println("Behajtott: " + j + ", " + szint + ". szint");
    }
}