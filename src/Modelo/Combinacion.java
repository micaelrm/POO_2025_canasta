
package Modelo;
import Modelo.Enum.*;
import Modelo.Interfaz.ICombinacion;
import java.io.Serializable;
import java.util.*;


public class Combinacion implements ICombinacion, Serializable {
    private ArrayList<Carta> listaCombinacion;
    private Integer puntaje;
    private boolean canasta;
    private boolean pura;
    
    public Combinacion() { 
        this.listaCombinacion = new ArrayList<Carta>(); 
        this.canasta = false;
        this.pura = false;
        actualizarPuntaje();
    }
       
    public void eliminarCombinacion() { listaCombinacion.clear(); }
    
    public void eliminarCarta(Carta cartaE) {
        for (Carta carta : listaCombinacion) {
            if (carta.equals(cartaE)) {
                listaCombinacion.remove(cartaE);
            }
        }
    }
    

    public void combinarCarta(Carta carta) { 
        listaCombinacion.add(carta); 
        actualizarPuntaje();
    }
    
    public void actualizarPuntaje(){
        this.puntaje = 0;
        for (Carta carta : listaCombinacion) {
            puntaje += carta.getPuntaje();
        } 
    }
    
    public int contarNaturales() {
        int naturales = 0;
        for (Carta carta : listaCombinacion) {
            if (carta.getTipo() == Tipo.NATURAL) naturales++;
        }
        return naturales;
    }   

    public int contarComodines() {
        int comodines = 0;
        for (Carta carta : listaCombinacion) {
            if (carta.getTipo() == Tipo.COMODIN) comodines++;
        }
        return comodines;
    }
    
    @Override
    public Integer getPuntaje() { return puntaje; }
    
    @Override
    public ArrayList<Carta> getListaCombinacion() { return listaCombinacion; }
    
    @Override
    public Carta getCartaNaturalTres() {
        Carta cartaBuscada = null; 
        for (Carta carta : listaCombinacion){
            if (carta.getTipo() == Tipo.NATURAL || carta.getTipo() == Tipo.TRES){
                cartaBuscada = carta;
                break;
            }
        }
        return cartaBuscada;
    }    
    
    public void setCanasta(boolean canasta) { this.canasta = canasta; }

    public void setPura(boolean pura) { this.pura = pura; }
    
}