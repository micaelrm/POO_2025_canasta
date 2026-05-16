package Modelo;

import java.io.Serializable;

public class RegistroPuntajes implements Serializable {
    
    private String nombreJugador1;
    private String nombreJugador2;
    private int puntajeAnterior;

    public RegistroPuntajes(String j1, String j2, int puntajeAnterior) {
        this.nombreJugador1 = j1;
        this.nombreJugador2 = j2;
        this.puntajeAnterior = puntajeAnterior;
    }

    public String getNombreJugador1() { return nombreJugador1; }
    public String getNombreJugador2() { return nombreJugador2; }
    public int getPuntajeAnterior() { return puntajeAnterior; }
    
    public void setPuntajeAnterior(int puntajeAnterior) { this.puntajeAnterior = puntajeAnterior; }
    
}
