
package Modelo.Interfaz;

import Modelo.Enum.EstadoJugador;
import Modelo.Equipo;
import Modelo.Mano;


public interface IJugador {
    Mano getMano();
    boolean equals(Object o);
    Equipo getEquipo();
    int getId();
    String getNombre();
    void setNombre(String nombre);
    EstadoJugador getEstado();
}
