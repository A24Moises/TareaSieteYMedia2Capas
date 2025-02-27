package sieteymedia;

import recursos.Carta;
import java.util.List;
import java.util.Scanner;
import recursos.Colores;

public class InterfaceConsola {
    private final SieteyMedia juego;
    private final Scanner sc;
    private String nombreJugador;

    public InterfaceConsola() {
        juego = new SieteyMedia();
        sc = new Scanner(System.in);
    }
/*----------------------------------------------------------*/
    public void iniciar() {
        presentarJuego();
        IngresoNombreJugador();
        juego.iniciarJuego();
        turnoJugador();
        turnoBanca();
        mostrarResultado();
    }
/*----------------------------------------------------------*/
    private void IngresoNombreJugador() {
        System.out.print(Colores.AMARILLO+"Escribe tu nombre Jugador: "+Colores.RESET);
        nombreJugador = sc.nextLine().trim();
    }
/*----------------------------------------------------------*/
    private void presentarJuego() {
        System.out.println();
        System.out.println(Colores.AZUL+"|----------------------------------|");
        System.out.println("| Bienvenido al juego de 7 y medio |");
        System.out.println("|----------------------------------|"+Colores.RESET);
        System.out.println();
        System.out.println(Colores.AMARILLO+"Las reglas son las siguientes:"+Colores.RESET);
        System.out.println(Colores.VERDE+"- El usuario es el jugador y el ordenador la  banca.");
        System.out.println("- No hay en la baraja 8s y 9s. El 10 es la sota, el 11 el caballo y el 12 el Rey.");
        System.out.println("- las figuras (10-sota, 11-caballo y 12-rey) valen medio punto y, el resto, su valor.");
        System.out.println("- Hay dos turnos de juego: el turno del jugador y el turno de la banca: Se comienza por el turno del jugador.");
        System.out.println("- El jugador va pidiendo cartas a la banca de una en una.");
        System.out.println("- Si la suma de los valores de las cartas sacadas es superior a 7 y medio, el jugador 'se pasa de siete y medio' y  pierde.");
        System.out.println("- Si el jugador no se pasa, comienza a sacar cartas, la banca está obligada a sacar cartas hasta empatar o superar al jugador.");
        System.out.println("- La banca no se puede plantar y tiene que empatar o superar la puntuación del  jugador sin pasarse.");
        System.out.println("- En este proceso puede ocurrir que la banca 'se pase' y entonces pierde la banca y gana el jugador."+Colores.RESET);
        System.out.println();
        System.out.println(Colores.ROJO+"Empecemos!!!"+Colores.RESET);
        System.out.println();
    }
/*----------------------------------------------------------*/
    private void turnoJugador() {
        System.out.println();
        System.out.println(Colores.ROJO+"Como mínimo recibes una carta, luego puedes decidir si seguir o plantarte"+Colores.RESET);
        char opcion;
        do {
            mostrarCartas(nombreJugador, juego.getCartasJugador());
            System.out.println("¿Quieres [C]arta o [P]lantarte?");
            opcion = sc.next().trim().toUpperCase().charAt(0);
            if (opcion == 'C') juego.recibirCartaJugador();
        } while (opcion == 'C' && calcularValor(juego.getCartasJugador()) < 7.5);

        juego.plantarse();
    }
/*----------------------------------------------------------*/
    private void turnoBanca() {
        System.out.println("\nTurno de la banca...");
        juego.jugarBanca();
        mostrarCartas("Banca", juego.getCartasBanca());
    }
/*----------------------------------------------------------*/
    private void mostrarCartas(String jugador, List<Carta> cartas) {
        System.out.println("\nCartas de " + jugador + ":");
        cartas.forEach(c -> System.out.print("\t" + c));
        System.out.println("\nValor total: " + calcularValor(cartas));
    }
/*----------------------------------------------------------*/
    private void mostrarResultado() {
        System.out.println("\n" + juego.obtenerResultado());
    }
/*----------------------------------------------------------*/
    private double calcularValor(List<Carta> cartas) {
        return cartas.stream().mapToDouble(c -> c.getNumero() > 7 ? 0.5 : c.getNumero()).sum();
    }
/*----------------------------------------------------------*/
    public static void main(String[] args) {
        new InterfaceConsola().iniciar();
    }
}
