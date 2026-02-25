
package Vista;


import java.awt.event.KeyEvent;
import java.util.*;
import javafx.util.Pair;
import Modelo.Enum.*;
import Modelo.Interfaz.*;
import java.rmi.RemoteException;


public class VistaConsola extends javax.swing.JFrame implements IVista{
    private ValidadorEntrada validador;
    private IControlador controlador;

    public VistaConsola(IControlador controlador) {
        initComponents();
        this.setLocationRelativeTo(null);
        this.setTitle("Canasta");
        this.validador = new ValidadorEntrada();
        this.controlador = controlador;
        areaSalida.setEnabled(false);
    }
    
   
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        interfaz1 = new gui.Interfaz();
        campoEntrada = new javax.swing.JTextField();
        jScrollPane1 = new javax.swing.JScrollPane();
        areaSalida = new javax.swing.JTextArea();

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 100, Short.MAX_VALUE)
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 100, Short.MAX_VALUE)
        );

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        campoEntrada.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                campoEntradaKeyPressed(evt);
            }
        });
        getContentPane().add(campoEntrada, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 330, 380, 30));

        areaSalida.setColumns(20);
        areaSalida.setRows(5);
        jScrollPane1.setViewportView(areaSalida);

        getContentPane().add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 10, 380, 310));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    @Override
    public void mostrarJuego() {
        mostrarCombinacionesPuntaje();
        mostrarCantidadMazo();
        mostrarTopeDescarte();
        mostrarMano();
        turnoJugador();
    }
    
    
    private void campoEntradaKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_campoEntradaKeyPressed
          
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            String entrada = campoEntrada.getText();
            
            try {
                EstadoTurno estadoTurno;
                estadoTurno = controlador.getEstadoTurno();
            switch(estadoTurno) {
                case EstadoTurno.TOMAR_CARTA:
                    try {
                        validador.validarTomar(entrada); 
                        try {
                            controlador.tomar(entrada);
                        } catch (RemoteException ex) {
                        System.getLogger(VistaConsola.class.getName()).log(System.Logger.Level.ERROR, (String) null, ex);
                        }
                    } catch (IllegalArgumentException eTomar){ concatenar(" - TOMAR: " + eTomar.getMessage()); } 
                case EstadoTurno.COMBINAR_DESCARTAR_CARTA:
                    try {
                        ArrayList<Pair <Valor, Palo>> pares = validador.validarCombinar(entrada);
                        try {
                            controlador.combinacion(pares);
                        } catch (RemoteException ex) {
                        System.getLogger(VistaConsola.class.getName()).log(System.Logger.Level.ERROR, (String) null, ex);
                        }
                    } catch (IllegalArgumentException eCombinar) { concatenar(" - COMBINAR: " + eCombinar.getMessage());  }
                    try {
                        Pair <Valor, Palo> par = validador.validarDescartar(entrada);
                        try {
                            controlador.descartar(par);
                        } catch (RemoteException ex) {
                        System.getLogger(VistaConsola.class.getName()).log(System.Logger.Level.ERROR, (String) null, ex);
                        }   
                    } catch (IllegalArgumentException eDescartar) { concatenar(" - DESCARTAR: " + eDescartar.getMessage()); }
            }
            } catch (RemoteException ex) {
                System.getLogger(VistaConsola.class.getName()).log(System.Logger.Level.ERROR, (String) null, ex);
            }
        }   
    }//GEN-LAST:event_campoEntradaKeyPressed

    
    public void mostrarCombinacionesPuntaje() {
        try {
            int numEquipo = 1;
            List<? extends IEquipo> equipos = controlador.getEquipos();
            for (IEquipo equipo : equipos) {
                List<? extends ICombinacion> combinaciones = equipo.getCombinaciones();
                concatenar("Combinaciones equipo " + equipo.getId());
                if (combinaciones.isEmpty()) concatenar("no hay combinaciones");
                for (ICombinacion combinacion : combinaciones) {
                    ICarta carta = combinacion.getCartaNaturalTres();
                    concatenar("   " + carta.getValor() + "   " + carta.getPalo() + "   " + carta.getColor() + " x" + combinacion.getListaCombinacion().size());
                }
                numEquipo++;
                concatenar("puntaje total: " + equipo.getPuntaje());
            }
        } catch (RemoteException ex) {
            System.getLogger(VistaConsola.class.getName()).log(System.Logger.Level.ERROR, (String) null, ex);
        }
    }
    
    public void mostrarMano() {
        try {
            IJugador jugador = controlador.getJugador();
            IMano mano = jugador.getMano();
            List<? extends ICarta> cartas = mano.getCartas();
            concatenar("Mano del jugador: ");
            for (ICarta carta : cartas) concatenar("   " + carta.getValor() + "   " + carta.getPalo() + "   " + carta.getColor());
        } catch (RemoteException ex) {
            System.getLogger(VistaConsola.class.getName()).log(System.Logger.Level.ERROR, (String) null, ex);
        }
    }
    
    public void mostrarCantidadMazo() {
        try {
            Integer cantidadMazo;
            cantidadMazo = controlador.getCantidadMazo();
            concatenar("Cantidad cartas mazo: " + cantidadMazo);
        } catch (RemoteException ex) {
            System.getLogger(VistaConsola.class.getName()).log(System.Logger.Level.ERROR, (String) null, ex);
        }
        
    }
    
    public void mostrarTopeDescarte()  {
        try {
            ICarta topeDescarte;
            topeDescarte = controlador.getTopeDescarte();
            concatenar("tope pila descarte:    " + topeDescarte.getValor() + "   " + topeDescarte.getPalo() + "   " + topeDescarte.getColor());
        } catch (RemoteException ex) {
            System.getLogger(VistaConsola.class.getName()).log(System.Logger.Level.ERROR, (String) null, ex);
        }
    }
    
    public void turnoJugador() {
        try {
            IEquipo equipo;
            equipo = controlador.getJugador().getEquipo();
            concatenar("equipo " + equipo.getId());
            concatenar("jugador " + controlador.getJugador().getId());
        } catch (RemoteException ex) {
            System.getLogger(VistaConsola.class.getName()).log(System.Logger.Level.ERROR, (String) null, ex);
        } 
        if (!evaluarEntrada()) { 
            concatenar("No puede ejecutar operaciones, no es su turno");
            campoEntrada.setEnabled(false);
        }
        else {
            try {
                concatenar("Puede ejecutar operaciones, es su turno");
                campoEntrada.setEnabled(true);
                EstadoTurno estadoTurno = controlador.getEstadoTurno();
                switch (estadoTurno) {
                    case(EstadoTurno.TOMAR_CARTA):
                        concatenar("Debe tomar una carta");
                        concatenar("comando: MAZO / DESCARTE ");
                        break;
                    case(EstadoTurno.COMBINAR_DESCARTAR_CARTA):
                        concatenar("Ahora puede descartar una carta o realizar combinaciones");
                        concatenar("comando descartar: DESCARTAR [VALOR] [PALO]");
                        concatenar("comando combinar: COMBINAR [VALOR] [PALO] ... [VALOR] [PALO]]");
                        break;
                }
            } catch (RemoteException ex) {
                System.getLogger(VistaConsola.class.getName()).log(System.Logger.Level.ERROR, (String) null, ex);
            }
        }
    }
    
    private void concatenar(String str) {
        areaSalida.append(str + "\n");
        areaSalida.setCaretPosition(areaSalida.getDocument().getLength());
    }
    
    @Override
    public IVista getVista() {
        return this;
    }
    
    public boolean evaluarEntrada() {
        boolean turno = false;
        try {
            IJugador jugador = controlador.getJugador();
            IJugador jugadorEnTurno = controlador.getJugadorEnTurno();
            if (jugador.equals(jugadorEnTurno)) turno = true;
        } catch (RemoteException ex) {
            System.getLogger(VistaConsola.class.getName()).log(System.Logger.Level.ERROR, (String) null, ex);
        }
        return turno;
    }
    
    @Override
    public void mostrarFin() {
        IEquipo equipo;
        try {
            equipo = controlador.getGanador();
            concatenar("Se han acabado las cartas del mazo");
            concatenar("el equipo " + equipo.getId() + " es el ganador");
        } catch (RemoteException ex) {
            System.getLogger(VistaConsola.class.getName()).log(System.Logger.Level.ERROR, (String) null, ex);
        }

    }
   
    @Override
    public void responderSolicitudRetiro() {
        
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTextArea areaSalida;
    private javax.swing.JTextField campoEntrada;
    private gui.Interfaz interfaz1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    // End of variables declaration//GEN-END:variables
}
