package recursos;

import java.util.Random;

public class Baraja {
    private final int NUM_CARTAS = 40;
    private Carta[] cartas = new Carta[NUM_CARTAS];
    private int primeraMano = 0;
/*----------------------------------------------------------*/
    public Baraja() {
        int ultimaCarta = 0;
        for (Palo p : Palo.values()) {
            for (int j = 0; j < 12; j++) {
                if (j == 7 || j == 8) continue;
                cartas[ultimaCarta] = new Carta(p, j + 1);
                ultimaCarta++;
            }
        }
    }
/*----------------------------------------------------------*/
    public void barajar() {
        Random r = new Random();
        for (int i = primeraMano; i < cartas.length; i++) {
            int posicionAzar = r.nextInt(cartas.length - primeraMano) + primeraMano;
            Carta temp = cartas[i];
            cartas[i] = cartas[posicionAzar];
            cartas[posicionAzar] = temp;
        }
    }
/*----------------------------------------------------------*/
    public Carta[] darCartas(int numCartasDar) {
        int cartasEnMazo = cartas.length - primeraMano;
        if (cartasEnMazo < numCartasDar) return new Carta[0];

        Carta[] cartasParaDar = new Carta[numCartasDar];
        for (int i = 0; i < numCartasDar; i++) {
            cartasParaDar[i] = cartas[primeraMano++];
        }
        return cartasParaDar;
    }
}
