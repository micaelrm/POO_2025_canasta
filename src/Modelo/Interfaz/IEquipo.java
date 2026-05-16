
package Modelo.Interfaz;
import Modelo.*;
import java.util.*;


public interface IEquipo {
    ArrayList<Jugador> getJugadores();
    Integer getPuntaje();
    Integer getPuntajeCombinacionMinima();
    ArrayList<Combinacion> getCombinaciones();
    int getId();
    Jugador getAmigo(IJugador j);
}
