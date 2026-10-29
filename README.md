# Parkolóház szimulátor

A console program for a parking garage. Cars, motorcycles, minibuses and
electric cars can enter and leave, and each vehicle type has its own fee.

## Fees

| Vehicle | Free | Per hour | Daily max |
|---|---|---|---|
| Autó | 30 min | 5 lei | 40 lei |
| Motor | 30 min | 2 lei | 12 lei |
| Kisbusz | 15 min | 8 lei | 60 lei |
| Elektromos autó | like a car + charging | | |

## Example output

Option 5 moves the clock forward, so the fees can be shown without waiting.

```
=== Parkolohaz ===
1 - Behajtas
2 - Kihajtas
3 - Foglaltsag szintenkent
4 - Napi bevetel
5 - Ido tekerese
0 - Kilepes
Valasztas: 1
Tipus: 1
Rendszam: MS12ABC
Behajtott: Autó (MS12ABC), 1. szint

Valasztas: 3
1. szint: 1 / 20
2. szint: 0 / 20
3. szint: 0 / 20

Valasztas: 5
Hany perc teljen el? 95
Eltelt 95 perc.

Valasztas: 2
Rendszam: MS12ABC
Fizetendo: 10.0 lej

Valasztas: 2
Rendszam: XX99ZZZ
HIBA: Nincs bent ilyen rendszám: XX99ZZZ

Valasztas: 4
Mai bevetel: 10.0 lej

Valasztas: 0
Viszlat!
```
