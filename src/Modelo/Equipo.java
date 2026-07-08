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
    private Integer puntajeAnterior;
    private ArrayList<Combinacion> combinaciones;
    private int id;
    private static int idEquipos = 1;
    private boolean primerCombinacion;
    private Integer puntajeCombinacionMinima;
    private Integer canastaNatural;
    private Integer canastaMixta;
    private int tresRojos;
    
    public Equipo () {
        this.puntaje = 0;
        this.puntajeAnterior = 0;
        this.combinaciones = new ArrayList<>();
        this.jugadores = new ArrayList<>();
        this.id = idEquipos;
        idEquipos++;
        this.primerCombinacion = false;
        this.puntajeCombinacionMinima = 15;
        this.canastaNatural = 0;
        this.canastaMixta = 0;
        this.tresRojos = 0;
    }
    
    public void reiniciarEquipo() {
        combinaciones.clear();
        for (Jugador jugador : jugadores) jugador.reiniciarJugador(); 
    }
    
    public void agregarJugador(Jugador j) { jugadores.add(j); }
    
    public void actualizarPuntaje() {
        this.puntaje = puntajeAnterior;
        for (Combinacion combinacion : combinaciones) {
            this.puntaje += combinacion.getPuntaje();
            if (combinacion.siCanastaMixta()) canastaMixta++;
            if (combinacion.siCanastaNatural()) canastaNatural++;
        }
    }
    
    public void calcularPuntajeFinal() {
        int penalizacion = calcularPenalizacion();
        int puntajeCanastaNatural = canastaNatural * 500;
        int puntajeCanastaMixta = canastaMixta * 300;
        int puntajeRetirada = 0;
        if (verificarJugadoresRetirados()) puntajeRetirada = 100;
        if (tresRojos == 4) puntaje += 400;
        
        this.puntaje = puntaje + puntajeCanastaNatural + puntajeCanastaMixta + puntajeRetirada - penalizacion;
    }
    
    public int calcularPenalizacion() {
        int penalizacion = jugadores.getFirst().getPuntajeMano() + jugadores.getLast().getPuntajeMano();
        if (combinaciones.isEmpty()) penalizacion += puntaje;
        
        return penalizacion;
    }
    
    public boolean siRetiradaActiva() {
        if (canastaMixta >= 1 || canastaNatural >= 1) return true;
        else return false;
    }
   
    public void setPuntajeCombinacionMinima() {
        if (puntajeAnterior <= 0) { puntajeCombinacionMinima = 15; }
        else if (puntajeAnterior <= 1500) { puntajeCombinacionMinima = 50; }
        else if (puntajeAnterior <= 3000) { puntajeCombinacionMinima = 90; }
        else { puntajeCombinacionMinima = 120; }
    }
    
    public void agregarCombinacion(Combinacion combinacion) { combinaciones.add(combinacion); }
    
    @Override
    public ArrayList<Jugador> getJugadores() { return jugadores; }
    
    @Override
    public Integer getPuntaje() { return puntaje; }
    
    public Integer getPuntajeAnterior() { return puntajeAnterior; }
    
    @Override
    public Integer getPuntajeCombinacionMinima() { return puntajeCombinacionMinima; }
    
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
    
    public int getTresRojos() { return tresRojos; }
    
    @Override
    public int getId() { return id; }
    
    public boolean siPrimerCombinacion() { return primerCombinacion; }
    
    
    public void retirarEquipo() {
        for (Jugador jugador : jugadores) { jugador.setEstado(EstadoJugador.RETIRADO); }
    }
    
    public void solicitarRetiro(IJugador jugador) {
        getJugador(jugador).solicitarRetiro();
    }
    
    public void responderRetiro(boolean respuesta, IJugador jugador) {
        if(respuesta) { retirarEquipo(); }
        else getAmigo(jugador).setEstado(EstadoJugador.ACTIVO);
    }
    
    public boolean verificarJugadoresRetirados() {
        int retirados = 0;
        for (Jugador jugador : jugadores) {
            if (jugador.getEstado() == EstadoJugador.RETIRADO) { retirados++; }
        }
        if (retirados == 2) return true;
        else return false;
    }
    
    public boolean siManoVacia() {
        for (Jugador jugador : jugadores) {
            if (jugador.siManoVacia()) return true;
        }
        return false;
    }
    
    public void setNombre(String nombre, IJugador jugador) {
        getJugador(jugador).setNombre(nombre);
    }
    
    public void setPuntaje(int puntaje) { this.puntaje = puntaje; }
    
    public void setPuntajeAnterior(int puntajeAnterior) { this.puntajeAnterior = puntajeAnterior; }
    
    public void setPrimerCombinacion(boolean primerCombinacion) { this.primerCombinacion = primerCombinacion; }
    
    public void sumarTresRojos() { tresRojos += 1; }
        
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Equipo equipo = (Equipo) o;
        return Objects.equals(id, equipo.id);
    }
}
