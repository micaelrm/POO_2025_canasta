
package Modelo.Interfaz;
import Modelo.*;
import java.util.*;


public interface IEquipo {
    ArrayList<Jugador> getJugadores();
    Integer getPuntaje();
    ArrayList<Combinacion> getCombinaciones();
    int getId();
    Jugador getAmigo(IJugador j);
}
