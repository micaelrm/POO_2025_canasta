
package Modelo.Interfaz;

import Modelo.Enum.*;

public interface ICarta {
    Color getColor();
    Palo getPalo();
    Valor getValor();
    int getPuntaje();
    Tipo getTipo();
    boolean getRotado(); 
    String toString();
}
