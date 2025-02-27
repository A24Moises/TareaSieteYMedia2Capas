package sieteymedia;

import recursos.Baraja;
import recursos.Carta;
import java.util.ArrayList;
import java.util.List;

public class SieteyMedia {
    private final Baraja baraja;
    private final List<Carta> cartasJugador;
    private final List<Carta> cartasBanca;
    private boolean turnoJugadorFinalizado;
/*----------------------------------------------------------*/
    public SieteyMedia() {
        baraja = new Baraja();
        baraja.barajar();
        cartasJugador = new ArrayList<>();
        cartasBanca = new ArrayList<>();
        turnoJugadorFinalizado = false;
    }
/*----------------------------------------------------------*/
    public void iniciarJuego() {
        recibirCartaJugador();
    }
/*----------------------------------------------------------*/
    public void recibirCartaJugador() {
        if (!turnoJugadorFinalizado) {
            cartasJugador.add(baraja.darCartas(1)[0]);
        }
    }
/*----------------------------------------------------------*/
    public void plantarse() {
        turnoJugadorFinalizado = true;
    }
/*----------------------------------------------------------*/
    public void jugarBanca() {
        double valorJugador = calcularValor(cartasJugador);
        if (valorJugador > 7.5) return;  // Jugador ya perdió

        while (calcularValor(cartasBanca) < valorJugador) {
            cartasBanca.add(baraja.darCartas(1)[0]);
        }
    }
/*----------------------------------------------------------*/
    public String obtenerResultado() {
        double valorJugador = calcularValor(cartasJugador);
        double valorBanca = calcularValor(cartasBanca);

        if (valorJugador > 7.5) return "Jugador, te has pasado en tu jugada anterior, gana la banca";
        if (valorBanca > 7.5) return "Me pasé, ganas tú,jugador";
        return (valorBanca >= valorJugador) ? "Gana la banca." : "Gana el jugador.";
    }
/*----------------------------------------------------------*/
    public List<Carta> getCartasJugador() {
        return new ArrayList<>(cartasJugador);
    }
/*----------------------------------------------------------*/
    public List<Carta> getCartasBanca() {
        return new ArrayList<>(cartasBanca);
    }
/*----------------------------------------------------------*/
    private double calcularValor(List<Carta> cartas) {
        return cartas.stream().mapToDouble(c -> c.getNumero() > 7 ? 0.5 : c.getNumero()).sum();
    }
}
