
package Modelo.Interfaz;

import Modelo.Enum.*;
import java.rmi.RemoteException;
import java.util.*;
import javafx.util.Pair;


public interface IControlador {
    List<? extends IEquipo> getEquipos() throws RemoteException;
    ICarta getTopeDescarte() throws RemoteException;
    Integer getCantidadMazo() throws RemoteException;
    IJugador getJugador() throws RemoteException;
    IJugador getJugadorEnTurno() throws RemoteException;
    void setNombre(String nombre) throws RemoteException;
    EstadoTurno getEstadoTurno() throws RemoteException;
    IEquipo getGanador() throws RemoteException;
    IEquipo getEquipoRival(IJugador j) throws RemoteException;
    IJugador getAmigo(IJugador j) throws RemoteException;
    void conectar(String nombre) throws RemoteException;
    void descartar(Pair <Valor, Palo> par) throws RemoteException;
    void tomar(String entrada) throws RemoteException;
    void combinacion(ArrayList<Pair <Valor, Palo>> pares) throws RemoteException;
    void solicitarRetiro() throws RemoteException;
    EstadoDescarte getEstadoDescarte() throws RemoteException;

    void setVista(IVista vista);
    void subscribir() throws RemoteException;

}
