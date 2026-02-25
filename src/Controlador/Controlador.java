package Controlador;

import Modelo.Interfaz.*;
import Modelo.Interfaz.IJugador;
import Modelo.Interfaz.IVista;
import ar.edu.unlu.rmimvc.cliente.IControladorRemoto;
import ar.edu.unlu.rmimvc.observer.IObservableRemoto;
import java.rmi.RemoteException;
import java.util.ArrayList;
import javafx.util.Pair;
import Modelo.Enum.*;
import Modelo.Interfaz.IControlador;
import Modelo.Jugador;
import java.util.List;


public class Controlador implements IControladorRemoto, IControlador {
    private ICanasta modelo;
    private IVista vista;
    private IJugador jugador;
    
    public Controlador() throws RemoteException {
        //conectar();
    }
    
    @Override
    public void conectar(String nombre) throws RemoteException {
        jugador = (IJugador) modelo.conectarJugador();
        setNombre(nombre);
        modelo.validarCantidadJugadores();
    }
    
    @Override
    public void descartar(Pair <Valor, Palo> par) throws RemoteException {
        modelo.descartar(par);
    }
    
    @Override
    public void tomar(String entrada) throws RemoteException {
        if ("MAZO".equals(entrada)) modelo.tomarMazo();
        else if ("DESCARTE".equals(entrada)) modelo.tomarDescarte();
    }
    
    @Override
    public void combinacion(ArrayList<Pair <Valor, Palo>> pares) throws RemoteException {
        modelo.formarCombinacion(pares);
    }

    @Override
    public void actualizar(IObservableRemoto ior, Object o) throws RemoteException {
        if (modelo.getEstadoJuego() == EstadoJuego.CORRIENDO) vista.mostrarJuego();
        if (modelo.getEstadoJuego() == EstadoJuego.FIN) vista.mostrarFin();
        if (modelo.getAmigo(jugador).getEstado() == EstadoJugador.SOLICITUD) vista.responderSolicitudRetiro();
    }
    
    @Override
    public <T extends IObservableRemoto> void setModeloRemoto(T modeloRemoto) throws RemoteException {
        this.modelo = (ICanasta) modeloRemoto;
    }

    @Override
    public void setVista(IVista vista) {
        this.vista = vista;
    }
    
    @Override
    public void setNombre(String nombre) throws RemoteException {
        modelo.setNombre(nombre, jugador);
    }
        
    @Override
    public List<? extends IEquipo> getEquipos() throws RemoteException {
        return modelo.getEquipos();
    }
    
    @Override
    public Integer getCantidadMazo() throws RemoteException {
        return modelo.getCantidadMazo();
    }
    
    @Override
    public ICarta getTopeDescarte() throws RemoteException { return modelo.getTopeDescarte(); }
    
    @Override
    public IJugador getJugador() throws RemoteException {
        for (IJugador j : modelo.getJugadores()) {
            if (this.jugador.equals(j)) return j;
        }
        return null;
    }
    
    @Override
    public IJugador getJugadorEnTurno() throws RemoteException {
        return modelo.getJugadorEnTurno();
    }
    
    @Override
    public EstadoTurno getEstadoTurno() throws RemoteException {
        return modelo.getEstadoTurno();
    }
    
    public EstadoDescarte getEstadoDescarte() throws RemoteException {
        return modelo.getEstadoDescarte();
    }
    
    @Override
    public IEquipo getGanador() throws RemoteException {
        return modelo.getGanador();
    }
    
    @Override
    public IEquipo getEquipoRival(IJugador j) throws RemoteException {
        return modelo.getEquipoRival(j);
    }
    
    @Override
    public IJugador getAmigo(IJugador j) throws RemoteException {
        return modelo.getAmigo(j);
    }
    
    @Override
    public void solicitarRetiro() throws RemoteException {
        modelo.solicitarRetiro(jugador);
    }
    
    @Override
    public void subscribir() throws RemoteException {
        modelo.agregarObservador(this);   
    }
}
