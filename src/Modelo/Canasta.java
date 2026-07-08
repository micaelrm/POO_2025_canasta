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
    
    public Canasta() throws RemoteException {
        super();
        this.adminJugadores = new AdminJugadores();
        this.mazo = new Mazo();
        this.descarte = new Descarte();
        adminJugadores.agregarEquipo();
        adminJugadores.agregarEquipo();
        estadoJuego = EstadoJuego.ESPERA;
    }
    
    /*
    public void reiniciarCanasta() throws RemoteException {
        try {
            estadoJuego = EstadoJuego.CORRIENDO;

            mazo = new Mazo(); 
            descarte = new Descarte(); 

            adminJugadores.reiniciarEquipos();
            adminJugadores.lecturaEquipos();
            repartirCartas();
            descarte.agregarCartaDescarte(mazo);
            notificarObservadores();
        } catch (IOException ex) {
            System.getLogger(Canasta.class.getName()).log(System.Logger.Level.ERROR, (String) null, ex);
        } catch (ClassNotFoundException ex) {
            System.getLogger(Canasta.class.getName()).log(System.Logger.Level.ERROR, (String) null, ex);
        }
    }
    */
    
    @Override
    public void iniciarJuego() throws RemoteException {
        try {
            adminJugadores.intercalarTurnos();
            adminJugadores.lecturaEquipos();
            repartirCartas();
            descarte.agregarCartaDescarte(mazo);
            estadoJuego = EstadoJuego.CORRIENDO;
            notificarObservadores();
        } catch (IOException ex) {
            System.getLogger(Canasta.class.getName()).log(System.Logger.Level.ERROR, (String) null, ex);
        } catch (ClassNotFoundException ex) {
            System.getLogger(Canasta.class.getName()).log(System.Logger.Level.ERROR, (String) null, ex);
        }

    }
    
    public void revisarCondiciones() throws RemoteException {
        boolean mazoVacio = mazo.siCero();
        boolean puntaje = adminJugadores.verificarPuntaje();
        boolean manoVacia = adminJugadores.siManoVacia();
        if (mazoVacio || puntaje || manoVacia) estadoJuego = EstadoJuego.FIN;
        notificarObservadores();
    }
    
    @Override
    public void terminarJuego() throws RemoteException {
        ArrayList<RegistroPuntajes> registros = adminJugadores.crearRegistros();
        try {
            if (adminJugadores.leer() == null) { adminJugadores.guardar(registros); }
            else adminJugadores.actualizar(registros);
        } catch (IOException ex) {
            System.getLogger(Canasta.class.getName()).log(System.Logger.Level.ERROR, (String) null, ex);
        } catch (ClassNotFoundException ex) {
            System.getLogger(Canasta.class.getName()).log(System.Logger.Level.ERROR, (String) null, ex);
        }
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
    public void verificarJugadoresRetirados() throws RemoteException {
        boolean retirados = adminJugadores.verificarJugadoresRetirados();
        if (retirados) estadoJuego = EstadoJuego.FIN;
    }
    
    @Override
    public boolean validarJugadorEnTurno(IJugador j) throws RemoteException {
        return adminJugadores.getJugadorEnTurno().equals(j);
    }

    @Override
    public void formarCombinacion(ArrayList<Pair <Valor, Palo>> pares) throws RemoteException {
        formarCombinacionAux(pares);
        revisarCondiciones();
    }
    
    @Override
    public void siguienteTurno() throws RemoteException {
        adminJugadores.avanzarTurno();
        revisarCondiciones();
    }
    
    @Override
    public void descartar(Pair <Valor, Palo> par) throws RemoteException {
        descartarCarta(par);
        siguienteTurno();
    }
    
    @Override
    public void tomarDescarte() throws RemoteException {
        tomarCartaDescarte();
        revisarCondiciones();
    }
    
    @Override
    public void tomarMazo() throws RemoteException {
        tomarCartaPila();
        adminJugadores.setEstadoTurno(EstadoTurno.COMBINAR_DESCARTAR_CARTA);
        if (mazo.siCero()) estadoJuego = EstadoJuego.FIN;
        revisarCondiciones();
    }
    
    @Override
    public boolean siRetiradaActiva() throws RemoteException {
        return adminJugadores.siRetiradaActiva();
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
    public IEquipo getEquipo(IJugador j) throws RemoteException {
        return adminJugadores.getEquipo(j);
    }
    
    @Override
    public IJugador getAmigo(IJugador j) throws RemoteException {
        return adminJugadores.getEquipo(j).getAmigo(j);
    }
    
    @Override
    public void solicitarRetiro(IJugador jugador) throws RemoteException {
        adminJugadores.solicitarRetiro(jugador);
        revisarCondiciones();
    }
    
    @Override
    public void responderRetiro(boolean respuesta, IJugador jugador) throws RemoteException {
        adminJugadores.responderRetiro(respuesta, jugador);
        revisarCondiciones();
    }
    
    @Override
    public EstadoDescarte getEstadoDescarte() throws RemoteException {
       return descarte.getEstadoDescarte();
    }
    
    @Override
    public void setNombre(String nombre, IJugador jugador) throws RemoteException {
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
    public void repartirCartas() throws RemoteException {
        Carta carta = null;
        for (Equipo equipo : adminJugadores.getEquipos()) {
            for (Jugador jugador : equipo.getJugadores()) {
                for (int cantCarta = 0; cantCarta < 11; cantCarta++){
                    carta = mazo.cederCarta();
                    if(carta.getTipo() == Tipo.TRES && carta.getColor() == Color.ROJO) {
                        cantCarta--;
                        validarCombinacionTres(carta, jugador);
                        adminJugadores.sumarTresRojos(jugador);
                    }
                    else jugador.tomarCarta(carta);
                }
            }
        }
    }
    
    public void tomarCartaPila() throws RemoteException {
        Carta carta;
        Jugador jugador = adminJugadores.getJugadorEnTurno();
        do {
            carta = mazo.cederCarta();
            if (carta.getTipo() == Tipo.TRES && carta.getColor() == Color.ROJO) {
                validarCombinacionTres(carta, jugador);
                adminJugadores.sumarTresRojos(jugador);
            }
        } while (carta.getTipo() == Tipo.TRES && carta.getColor() == Color.ROJO);
        jugador.tomarCarta(carta);
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
        else if (descarte.getEstadoDescarte() == EstadoDescarte.BLOQUEADA) {
            descarte.setEstadoDescarte(EstadoDescarte.CONGELADA);
        }
    }
    
    public void tomarCartaDescarte() throws RemoteException {
        Jugador jugadorEnTurno = adminJugadores.getJugadorEnTurno();
        Equipo equipoTurno = adminJugadores.getEquipo(jugadorEnTurno);
        Carta carta = descarte.getTopeDescarte();
        Combinacion combinacion = buscarCombinacionPorValor(carta.getValor());
        boolean valido;
        if(descarte.getEstadoDescarte().equals(EstadoDescarte.CONGELADA)) {
            valido = buscarCombinacionEnManoNatural(carta, jugadorEnTurno);
        }
        else valido = buscarCombinacionEnMano(carta, jugadorEnTurno);
   
        if (combinacion == null && !valido) {
            throw new RemoteException("No es posible tomar el tope del descarte, no podes usarlo en una combinacion");
        }
        
        jugadorEnTurno.tomarCartasDescarte(descarte.getDescarte());
        descarte.vaciarDescarte();
        descarte.agregarCartaDescarte(mazo);
    }
    
    public ArrayList<Carta> convertirCombinacion(ArrayList<Pair <Valor, Palo>> lista, Jugador j) {
        ArrayList<Carta> combinacion = new ArrayList<>();
        ArrayList<Carta> copiaMano = new ArrayList<>(j.getMano().getCartas());

        for (Pair<Valor, Palo> par : lista) {
            for (Carta carta : copiaMano) {
                if (carta.getValor() == par.getKey() && carta.getPalo() == par.getValue()) {
                    combinacion.add(carta);
                    copiaMano.remove(carta); 
                    break;
                }
            }
        }
        return combinacion;
    }
    
    public Carta convertirCombinacion(Pair<Valor, Palo> par, Jugador j) {
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
        Combinacion existente = null;
        int naturales = 0;
        int comodines = 0;
        Valor valorCombinacion = null;
        
        ArrayList<Carta> cartas = convertirCombinacion(lista, j);
        if (cartas == null) { return false; }

        for (Carta carta : cartas) {
            if (carta.getTipo() == Tipo.TRES && carta.getColor() == Color.NEGRO) return false;  
            else if (carta.getTipo() == Tipo.COMODIN) comodines++; 
            else if (carta.getTipo() == Tipo.NATURAL) {
                naturales++;
                if (valorCombinacion == null) valorCombinacion = carta.getValor();
                else if (carta.getValor() != valorCombinacion) return false;
            }
        }

        if (valorCombinacion != null) existente = buscarCombinacionPorValor(valorCombinacion);

        if (existente != null) {
            if (naturales < 1 || comodines > 0) return false;

            for (Carta carta : cartas) existente.combinarCarta(carta); 
            existente.actualizarPuntaje();
            adminJugadores.getEquipo(j).actualizarPuntaje();
        } else {
            if (cartas.size() < 3 || naturales < 2 || comodines > 3) return false;
            
            Combinacion nueva = new Combinacion();
            for (Carta carta : cartas) nueva.combinarCarta(carta); 

            if (nueva.getListaCombinacion().size() == 7) {
                if (nueva.contarComodines() == 0) nueva.setCanastaNatural(true);
                else nueva.setCanastaMixta(true);
            }

            nueva.actualizarPuntaje();
            if (adminJugadores.getEquipo(j).getPuntajeCombinacionMinima() > nueva.getPuntaje() && !adminJugadores.getEquipo(j).siPrimerCombinacion()) return false;
            else { 
                adminJugadores.getEquipo(j).setPrimerCombinacion(true);
                descarte.setEstadoDescarte(EstadoDescarte.LIBRE);
            }
            
            adminJugadores.getEquipo(j).agregarCombinacion(nueva);
            adminJugadores.getEquipo(j).actualizarPuntaje();
        }
        
        for (Carta cartaAEliminar : cartas) {
            j.getMano().eliminarCarta(cartaAEliminar);
        }  
        
        return true;
    }
    
    public boolean validarCombinacionTres(Carta carta, Jugador j) throws RemoteException {
        if (carta == null) { return false; }

        Combinacion existente = buscarCombinacionPorValor(Valor.TRES);

        if (existente != null) {
            existente.combinarCarta(carta); 
            existente.actualizarPuntaje();
            adminJugadores.getEquipo(j).actualizarPuntaje();
        } else {
            Combinacion nueva = new Combinacion();
            nueva.combinarCarta(carta); 
            nueva.actualizarPuntaje();
            adminJugadores.getEquipo(j).agregarCombinacion(nueva);
            adminJugadores.getEquipo(j).actualizarPuntaje();
        }
        
        return true;
    }
    
    public void formarCombinacionAux(ArrayList<Pair <Valor, Palo>> pares) throws RemoteException {
        Jugador jugadorEnTurno = adminJugadores.getJugadorEnTurno();
        if (!(validarCombinacion(pares, jugadorEnTurno))) {
            throw new RemoteException("Combinacion con formato incorrecto o puntaje menor al minimo");
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

    
}
