

package Modelo.Interfaz;
import ar.edu.unlu.rmimvc.observer.IObservableRemoto;
import java.rmi.RemoteException;
import java.util.ArrayList;
import Modelo.Enum.*;
import Modelo.*;
import java.util.*;
import javafx.util.Pair;


public interface ICanasta extends IObservableRemoto {
    
    IJugador conectarJugador() throws RemoteException;
    void validarCantidadJugadores() throws RemoteException;
    
    void iniciarJuego() throws RemoteException;
    void repartirCartas() throws RemoteException;
    void descartar(Pair <Valor, Palo> par) throws RemoteException;
    void formarCombinacion(ArrayList<Pair <Valor, Palo>> pares) throws RemoteException;
    void tomarMazo() throws RemoteException;
    void tomarDescarte() throws RemoteException;
    
    void siguienteTurno() throws RemoteException;
    IJugador getJugadorEnTurno() throws RemoteException;
    Mano getMano() throws RemoteException;
    List<? extends IEquipo> getEquipos() throws RemoteException;
    List<? extends IJugador> getJugadores() throws RemoteException;
    Integer getCantidadMazo() throws RemoteException;
    ICarta getTopeDescarte() throws RemoteException;
    int getCantidadJugadores() throws RemoteException;
    EstadoTurno getEstadoTurno() throws RemoteException;
    EstadoJuego getEstadoJuego() throws RemoteException;
    IEquipo getGanador() throws RemoteException;
    IEquipo getEquipoRival(IJugador j) throws RemoteException;
    IJugador getAmigo(IJugador j) throws RemoteException;
    void setNombre(String nombre, IJugador jugador) throws RemoteException;
    void solicitarRetiro(IJugador jugador) throws RemoteException;
    EstadoDescarte getEstadoDescarte() throws RemoteException;
}
