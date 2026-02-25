package Modelo;

import java.io.Serializable;

public class RegistroPuntajes implements Serializable {
    
    private String nombreJugador1;
    private String nombreJugador2;
    private int puntaje;

    public RegistroPuntajes(String j1, String j2, int puntaje) {
        this.nombreJugador1 = j1;
        this.nombreJugador2 = j2;
        this.puntaje = puntaje;
    }

    public String getNombreJugador1() { return nombreJugador1; }
    public String getNombreJugador2() { return nombreJugador2; }
    public int getPuntaje() { return puntaje; }
    
    public void setPuntaje(int puntaje) { this.puntaje = puntaje; }
    
}
