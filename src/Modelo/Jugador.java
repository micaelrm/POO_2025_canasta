
package Modelo;

import Modelo.Enum.EstadoJugador;
import Modelo.Interfaz.IJugador;
import java.io.Serializable;
import java.util.*;


public class Jugador implements IJugador, Serializable{
    private static int idEstatico = 0;
    private int id;
    private String nombre;
    private Mano mano;
    private Equipo equipo;
    private EstadoJugador estado;

    public Jugador(Equipo equipo) { 
        mano = new Mano(); 
        this.equipo = equipo;
        this.id = idEstatico;
        idEstatico += 1;
        nombre = null;
    }
    
    public void tomarCarta(Carta carta) { mano.agregarCarta(carta); }
    
    public void descartarCarta(Carta carta) { mano.eliminarCarta(carta); }
    
    public void tomarCartasDescarte(Stack<Carta> pilaDescarte) {
        for (Carta carta : pilaDescarte) {
            tomarCarta(carta);
        }
    }

    public void solicitarRetiro() {
        setEstado(EstadoJugador.SOLICITUD);
    }
    
    public boolean responderRetiro() {
        return false;
    }
    
    @Override
    public Mano getMano() { return mano;}
    
    @Override
    public Equipo getEquipo() { return equipo; }
    
    @Override
    public int getId() { return id; }
    
    @Override
    public String getNombre() { return nombre; }
    
    @Override
    public void setNombre(String nombre) { this.nombre = nombre; }
    
    @Override
    public EstadoJugador getEstado() { return estado; }

    public void setEstado(EstadoJugador estado) { this.estado = estado; }
    
    @Override
    public String toString() {
        return "Jugador{" + "id=" + id + ", mano=" + mano + '}';
    }
    
    public void mostrarMano() {
        mano.mostrarMano();
    }
    
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Jugador jugador = (Jugador) o;
        return Objects.equals(id, jugador.id);
    }
}
