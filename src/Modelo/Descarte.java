package Modelo;

import Modelo.Enum.EstadoDescarte;
import java.io.Serializable;
import java.util.*;


public class Descarte implements Serializable {
    private Stack<Carta> pilaDescarte;
    private EstadoDescarte estado;
    
    public Descarte() { 
        this.pilaDescarte = new Stack<>(); 
        estado = EstadoDescarte.INICIO;
    }
    
    public void vaciarDescarte() { pilaDescarte.clear(); }
    
    public void agregarCartaDescarte(Mazo mazo) { pilaDescarte.push(mazo.cederNaturalTres()); }
    
    public void agregarCartaDescarte(Carta carta) { pilaDescarte.push(carta); }
    
    public Stack<Carta> getDescarte() { return pilaDescarte; }
    
    public Carta getTopeDescarte() { return pilaDescarte.isEmpty() ? null : pilaDescarte.peek(); }
    
    public void setEstadoDescarte(EstadoDescarte estado) { this.estado = estado; }
    
    public EstadoDescarte getEstadoDescarte() { return estado; }
}
