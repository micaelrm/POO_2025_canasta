package Modelo;

import Modelo.Jugador;
import java.util.*;
import Modelo.Enum.*;
import Modelo.Interfaz.IJugador;
import java.rmi.RemoteException;


public class AdminJugadores {
    private ArrayList<Equipo> equipos;
    private Queue<Jugador> turnos;
    private Jugador jugadorEnTurno;
    private EstadoTurno estadoTurno;
    
    public AdminJugadores() { 
        this.turnos = new LinkedList<>(); 
        this.equipos = new ArrayList<>();
        this.jugadorEnTurno = null;
        this.estadoTurno = EstadoTurno.TOMAR_CARTA;
    } 
    
    public IJugador nuevoJugador() throws RemoteException {
        Jugador nuevoJugador = null;
        for (Equipo equipo : getEquipos()) {
            if (equipo.getJugadores().size() < 2) { 
                nuevoJugador = new Jugador(equipo);
                equipo.agregarJugador(nuevoJugador);
                agregarTurnos(nuevoJugador);
                this.jugadorEnTurno = nuevoJugador;
                break;
            } 
        }
        return nuevoJugador;
    }
    
    public Equipo compararPuntaje() {
        Equipo equipo1 = getEquipos().getFirst();
        Equipo equipo2 = getEquipos().getLast();
        if (equipo1.getPuntaje() > equipo2.getPuntaje()) return equipo1;
        else return equipo2;
    }
    
    public void agregarTurnos(Jugador j) { turnos.offer(j); }
    
    /*
    public void intercalarTurnos() {
        Stack<Jugador> aux1 = new Stack<>();
        Stack<Jugador> aux2 = new Stack<>();

        while(!turnos.isEmpty()) {
            aux1.push(turnos.poll());
            if(!turnos.isEmpty()) { 
                aux2.push(turnos.poll());
            }
        }

        while(!aux1.isEmpty() || !aux2.isEmpty()) {
            if(!aux1.isEmpty()) turnos.offer(aux1.pop());
            if(!aux2.isEmpty()) turnos.offer(aux2.pop());
        }
    }
    */
    
    public void avanzarTurno() {
        jugadorEnTurno = turnos.peek();
        turnos.offer(turnos.remove());
        setEstadoTurno(EstadoTurno.TOMAR_CARTA);
    } 
    
    public void agregarEquipo() { equipos.add(new Equipo()); }
    
    
    public Equipo getEquipoRival(IJugador j) {
        for (Equipo equipo : equipos) {
            if (!equipo.getJugadores().contains(j)) return equipo;
        }
        return null;
    }
    
    public Equipo getEquipo(IJugador j) {
        for (Equipo equipo : equipos) {
            if (equipo.getJugadores().contains(j)) return equipo;
        }
        return null;  
    }
    
    
    public ArrayList<Equipo> getEquipos() { return equipos; }

    public Jugador getJugadorEnTurno() { return jugadorEnTurno; }
    
    public void setNombre(String nombre, IJugador jugador) { getEquipo(jugador).setNombre(nombre, jugador); } 
    
    public void solicitarRetiro(IJugador jugador) {
        getEquipo(jugador).solicitarRetiro(jugador);
    }
    
    public int getCantidadJugadores() {
        int cantidad = 0;
        for (Equipo equipo : equipos) cantidad += equipo.getJugadores().size();
        return cantidad;
    }
    
    public ArrayList<Jugador> getJugadores() {
        ArrayList<Jugador> jugadores = new ArrayList<>();
  
        for(Equipo equipo : equipos) {
           jugadores.addAll(equipo.getJugadores());
        }
        return jugadores;
    }
    
    public EstadoTurno getEstadoTurno() {
        return estadoTurno;
    }
    
    public void setEstadoTurno(EstadoTurno estadoTurno) { this.estadoTurno = estadoTurno; }
}
