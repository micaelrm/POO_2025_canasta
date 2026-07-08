package Modelo;

import Modelo.Jugador;
import java.util.*;
import Modelo.Enum.*;
import Modelo.Interfaz.IJugador;
import java.io.IOException;
import java.rmi.RemoteException;


public class AdminJugadores {
    private ArrayList<Equipo> equipos;
    private Queue<Jugador> turnos;
    private Jugador jugadorEnTurno;
    private EstadoTurno estadoTurno;
    private Persistencia persistencia;
    
    public AdminJugadores() { 
        this.turnos = new LinkedList<>(); 
        this.equipos = new ArrayList<>();
        this.jugadorEnTurno = null;
        this.estadoTurno = EstadoTurno.TOMAR_CARTA;
        this.persistencia = new Persistencia();
    } 
    
    public void reiniciarEquipos() {
        for (Equipo equipo : equipos) equipo.reiniciarEquipo(); 
    }
    
    public IJugador nuevoJugador() throws RemoteException {
        Jugador nuevoJugador = null;
        for (Equipo equipo : getEquipos()) {
            if (equipo.getJugadores().size() < 2) { 
                nuevoJugador = new Jugador();
                equipo.agregarJugador(nuevoJugador);
                agregarTurnos(nuevoJugador);
                this.jugadorEnTurno = nuevoJugador;
                break;
            } 
        }
        return nuevoJugador;
    }
    
    public void sumarTresRojos(Jugador jugador) {
        Equipo equipoEnTurno = getEquipo(jugador);
        equipoEnTurno.sumarTresRojos();
    }
    
    public void intercalarTurnos() {
        turnos.clear();
        Queue<Jugador> turnosAuxiliar = new LinkedList<>();
        boolean primero;
        
        for (Equipo equipo : equipos) {
            primero = true;
            for (Jugador jugador : equipo.getJugadores()) {
                if (primero == true) {
                    turnos.add(jugador);
                    primero = false;
                }
                else turnosAuxiliar.add(jugador);
            }
        }
        
        turnos.addAll(turnosAuxiliar);
    }
    
    public boolean verificarJugadoresRetirados() {
        for (Equipo equipo : equipos) {
            if (equipo.verificarJugadoresRetirados()) { return true; }
        }
        return false;
    }
    
    public Equipo compararPuntaje() {
        Equipo equipo1 = getEquipos().getFirst();
        Equipo equipo2 = getEquipos().getLast();
        
        equipo1.calcularPuntajeFinal();
        equipo2.calcularPuntajeFinal();
        if (equipo1.getPuntaje() > equipo2.getPuntaje()) return equipo1;
        else return equipo2;
    }
  
    public boolean verificarPuntaje() {
        for (Equipo equipo : equipos) {
            if (equipo.getPuntaje() >= 5000) return true;
        }
        
        return false;
    }
    
    public void agregarTurnos(Jugador j) { turnos.offer(j); }
    
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
    
    public boolean siManoVacia() {
        for (Equipo equipo : equipos) {
            if (equipo.siManoVacia()) return true;
        }
        return false;
    }
    
    public boolean siRetiradaActiva() {
        Equipo equipoEnTurno = getEquipo(jugadorEnTurno);
        return equipoEnTurno.siRetiradaActiva();
    }
    
    public ArrayList<Equipo> getEquipos() { return equipos; }

    public Jugador getJugadorEnTurno() { return jugadorEnTurno; }
    
    public void setNombre(String nombre, IJugador jugador) { getEquipo(jugador).setNombre(nombre, jugador); } 
    
    public void solicitarRetiro(IJugador jugador) {
        getEquipo(jugador).solicitarRetiro(jugador);
    }
    
    public void responderRetiro(boolean respuesta, IJugador jugador) {
        getEquipo(jugador).responderRetiro(respuesta, jugador);
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
    
    //-----------------------------------------------------------------------
    
    public void guardar(ArrayList<RegistroPuntajes> registro) throws IOException {
        persistencia.guardar(registro);
    }
    
    public ArrayList<RegistroPuntajes> leer() throws IOException, ClassNotFoundException {
        return persistencia.leer();
    }
    
    public void actualizar(ArrayList<RegistroPuntajes> nuevosRegistros) throws RemoteException, IOException, ClassNotFoundException {
        persistencia.actualizar(nuevosRegistros);
    }
    
    public void lecturaEquipos() throws IOException, ClassNotFoundException {
        ArrayList<Equipo> equipos = getEquipos();
        ArrayList<RegistroPuntajes> registrosGuardados = persistencia.leer();
        
        if (registrosGuardados != null) {
            for (Equipo equipo : equipos) {
                String equipoNJ1 = equipo.getJugadores().getFirst().getNombre().trim();
                String equipoNJ2 = equipo.getJugadores().getLast().getNombre().trim();
                
                for (RegistroPuntajes registro : registrosGuardados) {                
                    String registroNJ1 = registro.getNombreJugador1().trim();
                    String registroNJ2 = registro.getNombreJugador2().trim();
                    
                    if ((registroNJ1.equalsIgnoreCase(equipoNJ1) && registroNJ2.equalsIgnoreCase(equipoNJ2)) || 
                        (registroNJ1.equalsIgnoreCase(equipoNJ2) && registroNJ2.equalsIgnoreCase(equipoNJ1))) {
                        
                        equipo.setPuntajeAnterior(registro.getPuntajeAnterior());
                        equipo.setPuntajeCombinacionMinima();
                    }
                }   
            }
        }
    }
    
    public ArrayList<RegistroPuntajes> crearRegistros() {
        ArrayList<RegistroPuntajes> registros = new ArrayList<>();
        for (Equipo equipo : getEquipos()) {
            String nombreJ1 = equipo.getJugadores().getFirst().getNombre();
            String nombreJ2 = equipo.getJugadores().getLast().getNombre();
            int puntajeAnterior = equipo.getPuntaje();
            RegistroPuntajes registro = new RegistroPuntajes(nombreJ1, nombreJ2, puntajeAnterior);
            registros.add(registro);
        }
        
        return registros;
    }
}
