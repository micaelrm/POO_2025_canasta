package Modelo;

import Modelo.Enum.*;
import Modelo.Interfaz.IEquipo;
import Modelo.Interfaz.IJugador;
import java.io.Serializable;
import java.util.*;
import javafx.util.*;
    

public class Equipo implements IEquipo, Serializable {
    private ArrayList<Jugador> jugadores;
    private Integer puntaje;
    private ArrayList<Combinacion> combinaciones;
    private int id;
    private static int idEquipos = 1;
    private boolean primerCombinacion;
    private int puntajeCombinacionMinima;
    
    public Equipo () {
        this.puntaje = 0;
        this.combinaciones = new ArrayList<>();
        this.jugadores = new ArrayList<>();
        this.id = idEquipos;
        idEquipos++;
        this.primerCombinacion = false;
        this.puntajeCombinacionMinima = 0;
    }
    
    public void agregarJugador(Jugador j) { jugadores.add(j); }
    
    public void actualizarPuntaje() {
        this.puntaje = 0;
        for (Combinacion combinacion : combinaciones) {
            this.puntaje += combinacion.getPuntaje();
        }
    }
   
    public void setPuntajeCombinacionMinima() {
        if (puntaje < 0) { puntajeCombinacionMinima = 15; }
        else if (puntaje < 1500) { puntajeCombinacionMinima = 50; }
        else if (puntaje < 3000) { puntajeCombinacionMinima = 90; }
        else { puntajeCombinacionMinima = 120; }
    }
    
    public void agregarCombinacion(Combinacion combinacion) { combinaciones.add(combinacion); }
    
    @Override
    public ArrayList<Jugador> getJugadores() { return jugadores; }
    
    @Override
    public Integer getPuntaje() { return puntaje; }
    
    @Override
    public ArrayList<Combinacion> getCombinaciones() {
        return combinaciones;
    }
    
    @Override
    public Jugador getAmigo(IJugador j) {
        for (Jugador jugador : jugadores) {
            if (!j.equals(jugador)) return jugador;
        }
        return null;
    }
    
    public Jugador getJugador(IJugador j) {
        for (Jugador jugador : jugadores) { 
            if (jugador.equals(j)) return jugador;
        }
        return null;
    }
    
    public void solicitarRetiro(IJugador jugador) {
        getJugador(jugador).solicitarRetiro();
    }
    
    public void setNombre(String nombre, IJugador jugador) {
        getJugador(jugador).setNombre(nombre);
    }
    
    public void setPuntaje(int puntaje) { this.puntaje = puntaje; }
    
    @Override
    public int getId() { return id; }
}
