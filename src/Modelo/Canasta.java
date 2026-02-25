
package Modelo;
import Modelo.Interfaz.*;
import Modelo.Enum.*;
import ar.edu.unlu.rmimvc.observer.IObservadorRemoto;
import ar.edu.unlu.rmimvc.observer.ObservableRemoto;
import java.io.IOException;
import java.rmi.RemoteException;
import java.util.*;
import javafx.util.Pair; 


public class Canasta extends ObservableRemoto implements ICanasta {
    private AdminJugadores adminJugadores;
    private Mazo mazo;
    private Descarte descarte;
    private EstadoJuego estadoJuego;
    private Persistencia persistencia;
    
    public Canasta() throws RemoteException {
        super();
        this.adminJugadores = new AdminJugadores();
        this.mazo = new Mazo();
        this.descarte = new Descarte();
        adminJugadores.agregarEquipo();
        adminJugadores.agregarEquipo();
        estadoJuego = EstadoJuego.ESPERA;
    }


    
    @Override
    public void iniciarJuego() throws RemoteException {
        //adminJugadores.intercalarTurnos();
        repartirCartas();
        descarte.agregarCartaDescarte(mazo);
        estadoJuego = EstadoJuego.CORRIENDO;
        notificarObservadores();
    }
    
    @Override
    public IJugador conectarJugador() throws RemoteException {
        return adminJugadores.nuevoJugador();
    }
    
    @Override
    public void validarCantidadJugadores() throws RemoteException {
        if (adminJugadores.getCantidadJugadores() == 4) iniciarJuego();
    }

  
    @Override
    public void formarCombinacion(ArrayList<Pair <Valor, Palo>> pares) throws RemoteException {
        formarCombinacionAux(pares);
        notificarObservadores();
    }
    
    @Override
    public void siguienteTurno() throws RemoteException {
        adminJugadores.avanzarTurno();
        notificarObservadores();
    }
    
    @Override
    public void descartar(Pair <Valor, Palo> par) throws RemoteException {
        descartarCarta(par);
        siguienteTurno();
    }
    
    @Override
    public void tomarDescarte() throws RemoteException {
        tomarCartaDescarte();
        notificarObservadores();
    }
    
    @Override
    public void tomarMazo() throws RemoteException {
        tomarCartaPila();
        adminJugadores.setEstadoTurno(EstadoTurno.COMBINAR_DESCARTAR_CARTA);
        if (mazo.siCero()) estadoJuego = EstadoJuego.FIN;
        notificarObservadores();
    }
    
    @Override
    public Mano getMano() throws RemoteException {
        return adminJugadores.getJugadorEnTurno().getMano();
    }
    
    @Override
    public IJugador getJugadorEnTurno() throws RemoteException {
        return adminJugadores.getJugadorEnTurno();
    }
    
    @Override
    public List<? extends IEquipo> getEquipos() throws RemoteException {
         return adminJugadores.getEquipos();
    }
    
    @Override
    public List<? extends IJugador> getJugadores() throws RemoteException {
        return adminJugadores.getJugadores();
    }
    
    
    @Override
    public Integer getCantidadMazo() throws RemoteException {
        return mazo.getCantidad();
    }
    
    @Override
    public ICarta getTopeDescarte() throws RemoteException {
        return descarte.getTopeDescarte();
    }
    
    @Override
    public int getCantidadJugadores() throws RemoteException {
        return adminJugadores.getCantidadJugadores();
    }
    
    public Equipo getEquipoGanador() throws RemoteException {
        return adminJugadores.compararPuntaje();
    }
    
    @Override
    public EstadoJuego getEstadoJuego() throws RemoteException {
        return estadoJuego;
    }
    
    @Override
    public IEquipo getGanador() throws RemoteException {
        return adminJugadores.compararPuntaje();
    }
    
    @Override
    public IEquipo getEquipoRival(IJugador j) throws RemoteException {
        return adminJugadores.getEquipoRival(j);
    }
    
    @Override
    public IJugador getAmigo(IJugador j) throws RemoteException {
        return adminJugadores.getEquipo(j).getAmigo(j);
    }
    
    @Override
    public void solicitarRetiro(IJugador jugador) throws RemoteException {
        adminJugadores.solicitarRetiro(jugador);
        notificarObservadores();
    }
    
    @Override
    public EstadoDescarte getEstadoDescarte() throws RemoteException {
       return descarte.getEstadoDescarte();
    }
    
    @Override
    public void setNombre(String nombre, IJugador jugador) {
        adminJugadores.setNombre(nombre , jugador);
    }
    
    @Override
    public EstadoTurno getEstadoTurno() throws RemoteException {
        return adminJugadores.getEstadoTurno();
    }
    


    @Override
    public void notificarObservadores() throws RemoteException {
        super.notificarObservadores(); 
    }

    @Override
    public void notificarObservadores(Object obj) throws RemoteException {
        super.notificarObservadores(obj); 
    }

    @Override
    public void removerObservador(IObservadorRemoto o) throws RemoteException {
        super.removerObservador(o); 
    }

    @Override
    public void agregarObservador(IObservadorRemoto o) throws RemoteException {
        super.agregarObservador(o); 
    }
    
    
    
    //--------------------------------------------------------------------------
    
    @Override
    public void repartirCartas(){
        Carta carta = null;
        for (Equipo equipo : adminJugadores.getEquipos()) {
            for (Jugador jugador : equipo.getJugadores()) {
                for (int cantCarta = 0; cantCarta < 11; cantCarta++){
                    carta = mazo.cederCarta();
                    if(carta.getTipo() == Tipo.TRES && carta.getColor() == Color.ROJO) {
                        cantCarta--;
                        validarCombinacionTres(carta, jugador);
                    }
                    else jugador.tomarCarta(carta);
                }
            }
        }
    }
    
    public void tomarCartaPila() {
        Carta carta = mazo.cederCarta();
        adminJugadores.getJugadorEnTurno().tomarCarta(carta);
    }
    
    public void descartarCarta(Pair<Valor, Palo> par) throws RemoteException {
        Jugador jugadorEnTurno = adminJugadores.getJugadorEnTurno();
        //Equipo equipoTurno = jugadorEnTurno.getEquipo();
        System.out.println(par.toString());
        Carta carta = convertirCombinacion(par, jugadorEnTurno);
        System.out.println(carta.toString());
        descarte.agregarCartaDescarte(carta);
        jugadorEnTurno.descartarCarta(carta);
        if ((carta.getTipo() == Tipo.COMODIN || carta.getTipo() == Tipo.TRES) && descarte.getEstadoDescarte() == EstadoDescarte.LIBRE) {
            descarte.setEstadoDescarte(EstadoDescarte.BLOQUEADA);
        }
        if (descarte.getEstadoDescarte() == EstadoDescarte.BLOQUEADA) {
            descarte.setEstadoDescarte(EstadoDescarte.CONGELADA);
        }
    }
    
    public void tomarCartaDescarte() throws IllegalStateException {
        Jugador jugadorEnTurno = adminJugadores.getJugadorEnTurno();
        Equipo equipoTurno = jugadorEnTurno.getEquipo();
        Carta carta = descarte.getTopeDescarte();
        Combinacion combinacion = buscarCombinacionPorValor(carta.getValor());
        boolean valido;
        if(descarte.getEstadoDescarte().equals(EstadoDescarte.CONGELADA)) {
            valido = buscarCombinacionEnManoNatural(carta, jugadorEnTurno);
        }
        else valido = buscarCombinacionEnMano(carta, jugadorEnTurno);
        if (combinacion == null && !valido) {
            throw new IllegalStateException();
        }
        
        jugadorEnTurno.tomarCartasDescarte(descarte.getDescarte());
        descarte.vaciarDescarte();
    }
    
    public ArrayList<Carta> convertirCombinacion(ArrayList<Pair <Valor, Palo>> lista, Jugador j) throws IllegalStateException {
        ArrayList<Carta> combinacion = new ArrayList<>();
        Mano mano = j.getMano();
        for (Pair<Valor, Palo> par : lista) {
            for (Carta carta : mano.getCartas()) {
                if (carta.getValor() == par.getKey() && carta.getPalo() == par.getValue()) {
                    combinacion.add(carta);
                    j.getMano().eliminarCarta(carta);
                    break;
                }
            }
        }
        return combinacion;
    }
    
    public Carta convertirCombinacion(Pair<Valor, Palo> par, Jugador j) throws IllegalStateException {
        Valor valorBuscado = par.getKey();
        Palo paloBuscado = par.getValue();

        for (ICarta carta : j.getMano().getCartas()) {
            if (valorBuscado == Valor.JOKER && carta.getValor() == Valor.JOKER) {
                return (Carta) carta;
            }
            if (carta.getValor() == valorBuscado && carta.getPalo() == paloBuscado) {
                return (Carta) carta;
            }
        }
        return null;
    }
    
    public boolean validarCombinacion(ArrayList<Pair<Valor, Palo>> lista, Jugador j) {
        ArrayList<Carta> cartas = convertirCombinacion(lista, j);
        if (cartas == null) { return false; }

        // REVISAR
        //if (cartas.size() < 3) return false; 

        int naturales = 0;
        int comodines = 0;
        Valor baseValor = null;

        for (Carta carta : cartas) {
            if (carta.getTipo() == Tipo.TRES && carta.getColor() == Color.NEGRO) { return false; } 
            else if (carta.getTipo() == Tipo.COMODIN) { comodines++; } 
            else if (carta.getTipo() == Tipo.NATURAL) {
                naturales++;
                if (baseValor == null) baseValor = carta.getValor();
                else if (carta.getValor() != baseValor) return false;
            }
        }

        Combinacion existente = (baseValor != null) ? buscarCombinacionPorValor(baseValor) : null;

        if (existente != null) {
            int existentesNaturales = existente.contarNaturales();
            int existentesComodines = existente.contarComodines();

            int totalNaturales = existentesNaturales + naturales;
            int totalComodines = existentesComodines + comodines;
            int totalSize = existente.getListaCombinacion().size() + cartas.size();

            if (totalComodines > 3) return false;

            if (totalSize >= 7 && totalNaturales < 4) return false;

            for (Carta carta : cartas) existente.combinarCarta(carta); 
            existente.actualizarPuntaje();
            adminJugadores.getEquipo(j).actualizarPuntaje();
            return true;
        } else {
            if (naturales < 2 || comodines > 3 || cartas.size() > 7) return false;
            Combinacion nueva = new Combinacion();
            for (Carta carta : cartas) nueva.combinarCarta(carta); 

            if (nueva.getListaCombinacion().size() == 7) {
                nueva.setCanasta(true);
                if (nueva.contarComodines() == 0) nueva.setPura(true);
                else nueva.setPura(false);
            } else {
                nueva.setCanasta(false);
                nueva.setPura(nueva.contarComodines() == 0);
            }

            nueva.actualizarPuntaje();
            adminJugadores.getEquipo(j).agregarCombinacion(nueva);
            adminJugadores.getEquipo(j).actualizarPuntaje();
            return true;
        }
    }
    
    public boolean validarCombinacionTres(Carta carta, Jugador j) {

        if (carta == null) { return false; }

        Combinacion existente = buscarCombinacionPorValor(Valor.TRES);

        if (existente != null) {
            existente.combinarCarta(carta); 
            existente.actualizarPuntaje();
            adminJugadores.getEquipo(j).actualizarPuntaje();
            return true;
        } else {
            Combinacion nueva = new Combinacion();
            nueva.combinarCarta(carta); 
            nueva.actualizarPuntaje();
            adminJugadores.getEquipo(j).agregarCombinacion(nueva);
            adminJugadores.getEquipo(j).actualizarPuntaje();
            return true;
        }
    }
    
    public void formarCombinacionAux(ArrayList<Pair <Valor, Palo>> pares) throws IllegalStateException {
        Jugador jugadorEnTurno = adminJugadores.getJugadorEnTurno();
        if (!(validarCombinacion(pares, jugadorEnTurno))) {
            throw new IllegalStateException();
        }
    }
    
    public boolean buscarCombinacionEnMano(Carta cartaBuscada, Jugador j) {
        boolean valido = true;
        Mano mano = j.getMano();
        ArrayList<Carta> cartas = mano.getCartas();
        int naturales = 0, comodines = 0;
        for (Carta carta : cartas) {
            if (carta.getValor() == cartaBuscada.getValor()) naturales++;
            else if (carta.getTipo() == Tipo.COMODIN) comodines++;
        }
        if (naturales < 2 || comodines < (naturales / 2)) valido = false;
        
        return valido;
    }
    
    public boolean buscarCombinacionEnManoNatural(Carta cartaBuscada, Jugador j) {
        boolean valido = true;
        Mano mano = j.getMano();
        ArrayList<Carta> cartas = mano.getCartas();
        int naturales = 0;
        for (Carta carta : cartas) {
            if (carta.getValor() == cartaBuscada.getValor()) naturales++;
        }
        if (naturales < 2) valido = false;
        
        return valido;
    }
    
    public Combinacion buscarCombinacionPorValor(Valor valor) {
        ArrayList<Combinacion> combinaciones = adminJugadores.getEquipo(adminJugadores.getJugadorEnTurno()).getCombinaciones();
        for (Combinacion combinacion : combinaciones) {
            Carta base = combinacion.getCartaNaturalTres(); 
            if (base != null && base.getValor() == valor) {
                return combinacion;
            }
        }
        return null;
    }
    
    //---------------------------------------------------------------------------
    /*
    public ArrayList<RegistroPuntajes> leer() throws IOException {
        return persistencia.leer();
    }
    /*
    public guardar() {
        persistencia.guardar();
    }
    */
    public void LecturaEquipos() throws IOException, ClassNotFoundException {
        ArrayList<Equipo> equipos = adminJugadores.getEquipos();
        ArrayList<RegistroPuntajes> registrosGuardados = persistencia.leer();
        for (Equipo equipo : equipos) {
            String EquipoNJ1 = equipo.getJugadores().getFirst().getNombre();
            String EquipoNJ2 = equipo.getJugadores().getLast().getNombre();
            for (RegistroPuntajes registro : registrosGuardados) {                
                String RegistroNJ1 = registro.getNombreJugador1();
                String RegistroNJ2 = registro.getNombreJugador2();
                if ((RegistroNJ1 == EquipoNJ1 && RegistroNJ2 == EquipoNJ2) || (RegistroNJ1 == EquipoNJ2 && RegistroNJ2 == EquipoNJ1)) {
                    equipo.setPuntaje(registro.getPuntaje());
                    equipo.setPuntajeCombinacionMinima();
                }
            }   
        }
    }
}
