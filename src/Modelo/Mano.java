
package Modelo;

import Modelo.Enum.*;
import Modelo.Interfaz.IMano;
import java.io.Serializable;
import java.util.*;
import javafx.util.Pair;


public class Mano implements IMano, Serializable {
    private ArrayList<Carta> cartas;
    private ArrayList<Carta> combinacion;
    
    public Mano() { 
        this.cartas = new ArrayList<Carta>(); 
        this.combinacion = new ArrayList<Carta>(); 
    }
    
    public void agregarCarta(Carta carta) { cartas.add(carta); }
       
    public void eliminarCarta(Carta carta) { cartas.remove(carta); }
   
    @Override
    public ArrayList<Carta> getCartas() { return cartas; }
    
    public ArrayList<Carta> getCombinacion() { return combinacion; }
    
    @Override
    public String toString() {
        return "Mano{" + "cartas=" + cartas + '}';
    }
    
    public void mostrarMano() {
        for (Carta carta : cartas) {
            System.out.println(carta.toString());
        }
    }
    
    //--------------------------------------------------------------------------
    
    
}
