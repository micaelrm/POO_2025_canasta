
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
    private boolean esperandoRespuestaRetiro = false;
    private boolean nuevaPartida = false;

    public VistaConsola(IControlador controlador) {
        initComponents();
        this.setLocationRelativeTo(null);
        this.setTitle("Canasta");
        this.validador = new ValidadorEntrada();
        this.controlador = controlador;
        areaSalida.setEnabled(false);
        areaSalida.setForeground(java.awt.Color.GREEN);
        areaSalida.setBackground(java.awt.Color.BLACK); 
        areaSalida.setCaretColor(java.awt.Color.WHITE);
        
        this.setSize(400, 400);
        this.setResizable(false);
        this.setTitle("Canasta - Vista Consola");
        this.setLocationRelativeTo(null);
    }
    
   
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
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
    
    @Override
    public void responderSolicitudRetiro() {
        concatenar("=======================================");
        concatenar("¡ATENCIÓN! Tu compañero ha solicitado retirarse de la partida.");
        concatenar("¿Aceptas la retirada? Escribe SI o NO:");
        concatenar("=======================================");

        campoEntrada.setEnabled(true);   
    }
    
    
    private void campoEntradaKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_campoEntradaKeyPressed

        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            String entrada = campoEntrada.getText().trim().toUpperCase(); 
            campoEntrada.setText("");

            try {
                if (nuevaPartida) {
                    if (entrada.equals("SI")) {
                        //controlador.reiniciarCanasta();
                        concatenar("Se iniciara una nueva partida");
                    } else if (entrada.equals("NO")) {
                        concatenar("No se iniciara una nueva partida");
                    } else { concatenar("Entrada inválida. Por favor, escribe SI o NO."); }
                }
                
                EstadoJugador estadoJugador = controlador.getJugadorEnTurno().getEstado();
                if (estadoJugador == EstadoJugador.SOLICITUD) {
                    if (entrada.equals("SI")) {
                        controlador.responderRetiro(true);
                        concatenar("Has aceptado la retirada.");
                    } else if (entrada.equals("NO")) {
                        controlador.responderRetiro(false);
                        esperandoRespuestaRetiro = false;
                        concatenar("Has rechazado la retirada. El juego continúa.");
                    } else {
                        concatenar("Entrada inválida. Por favor, escribe SI o NO.");
                    }
                    return; 
                }

                if (!evaluarEntrada()) {
                    concatenar("No puedes ejecutar operaciones, no es tu turno.");
                    return;
                }

                if (entrada.equals("RETIRARSE") && controlador.siRetiradaActiva()) {
                    controlador.solicitarRetiro();
                    concatenar("Has solicitado retirarte. Esperando respuesta de tu compañero...");
                    campoEntrada.setEnabled(false); 
                    return;
                } else concatenar("Aún no podes retirarte, no has completado una canasta");
                    

                EstadoTurno estadoTurno = controlador.getEstadoTurno();
                switch(estadoTurno) {
                    case TOMAR_CARTA:
                        try {
                            validador.validarTomar(entrada); 
                            controlador.tomar(entrada);
                        } catch (IllegalArgumentException eTomar){ 
                            //concatenar(" - TOMAR: " + eTomar.getMessage()); 
                        } 
                        break;

                    case COMBINAR_DESCARTAR_CARTA:
                        if (entrada.startsWith("COMBINAR")) {
                            try {
                                ArrayList<Pair <Valor, Palo>> pares = validador.validarCombinar(entrada);
                                controlador.combinacion(pares);
                            } catch (IllegalArgumentException eCombinar) { 
                                //concatenar(" - COMBINAR: " + eCombinar.getMessage());  
                            }
                        } else if (entrada.startsWith("DESCARTAR")) {
                            try {
                                Pair <Valor, Palo> par = validador.validarDescartar(entrada);
                                controlador.descartar(par);
                            } catch (IllegalArgumentException eDescartar) { 
                                //concatenar(" - DESCARTAR: " + eDescartar.getMessage()); 
                            }
                        } else {
                            concatenar("Comando no reconocido para esta fase del turno.");
                        }
                        break;
                }
            } catch (Exception ex) {
                System.getLogger(VistaConsola.class.getName()).log(System.Logger.Level.ERROR, (String) null, ex);
                mostrarMensajeError(ex.getMessage());
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
                if (equipo.getCombinaciones().isEmpty()) {
                    concatenar("Puntaje necesario para abrir: " + equipo.getPuntajeCombinacionMinima());
                }
            }
        } catch (Exception ex) {
            System.getLogger(VistaConsola.class.getName()).log(System.Logger.Level.ERROR, (String) null, ex);
            mostrarMensajeError(ex.getMessage());
        }
    }
    
    public void mostrarMano() {
        try {
            IJugador jugador = controlador.getJugador();
            IMano mano = jugador.getMano();
            List<? extends ICarta> cartas = mano.getCartas();
            concatenar("Mano del jugador: ");
            for (ICarta carta : cartas) concatenar("   " + carta.getValor() + "   " + carta.getPalo() + "   " + carta.getColor());
        } catch (Exception ex) {
            System.getLogger(VistaConsola.class.getName()).log(System.Logger.Level.ERROR, (String) null, ex);
            mostrarMensajeError(ex.getMessage());
        }
    }
    
    public void mostrarCantidadMazo() {
        try {
            Integer cantidadMazo = controlador.getCantidadMazo();
            EstadoDescarte estado = controlador.getEstadoDescarte();
            concatenar("Cantidad cartas mazo: " + cantidadMazo);
            concatenar("Estado del pozo: [" + estado + "]");
        } catch (Exception ex) {
            System.getLogger(VistaConsola.class.getName()).log(System.Logger.Level.ERROR, (String) null, ex);
            mostrarMensajeError(ex.getMessage());
        }
        
    }
    
    public void mostrarTopeDescarte()  {
        try {
            ICarta topeDescarte;
            topeDescarte = controlador.getTopeDescarte();
            concatenar("tope pila descarte:    " + topeDescarte.getValor() + "   " + topeDescarte.getPalo() + "   " + topeDescarte.getColor());
        } catch (Exception ex) {
            System.getLogger(VistaConsola.class.getName()).log(System.Logger.Level.ERROR, (String) null, ex);
            mostrarMensajeError(ex.getMessage());
        }
    }
    
    public void turnoJugador() {
        try {
            IEquipo equipo;
            equipo = controlador.getEquipo(controlador.getJugadorEnTurno());
            concatenar("equipo " + equipo.getId());
            concatenar("jugador " + controlador.getJugador().getId());
        } catch (Exception ex) {
            System.getLogger(VistaConsola.class.getName()).log(System.Logger.Level.ERROR, (String) null, ex);
            mostrarMensajeError(ex.getMessage());
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
                        concatenar("comando tomar: MAZO / DESCARTE ");
                        break;
                    case(EstadoTurno.COMBINAR_DESCARTAR_CARTA):
                        concatenar("Ahora puede descartar una carta o realizar combinaciones");
                        concatenar("comando descartar: DESCARTAR [VALOR] [PALO]");
                        concatenar("comando combinar: COMBINAR [VALOR] [PALO] ... [VALOR] [PALO]]");
                        break;
                }
            } catch (Exception ex) {
                System.getLogger(VistaConsola.class.getName()).log(System.Logger.Level.ERROR, (String) null, ex);
                mostrarMensajeError(ex.getMessage());
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
        try {
            boolean turno = controlador.validarJugadorEnTurno();;
            return turno;
        } catch (Exception ex) {
            System.getLogger(VistaConsola.class.getName()).log(System.Logger.Level.ERROR, (String) null, ex);
            mostrarMensajeError(ex.getMessage());
        }
        return false;
    }
    
    @Override
    public void mostrarFin() {
        IEquipo equipo;
        try {
            equipo = controlador.getGanador();
            concatenar("el equipo " + equipo.getId() + " es el ganador");
            controlador.terminarJuego();
        } catch (Exception ex) {
            System.getLogger(VistaConsola.class.getName()).log(System.Logger.Level.ERROR, (String) null, ex);
            mostrarMensajeError(ex.getMessage());
        }
    }
    
    public void mensajeNuevaPartida() {
        concatenar("==========================================");
        concatenar("¡ATENCIÓN! Terminó la partida");
        concatenar("¿Desea jugar otra pártida? Escribe SI o NO:");
        concatenar("==========================================");
        
        nuevaPartida = true;
        campoEntrada.setEnabled(true);   
    }
    
    public void mostrarMensajeError(String error) {
        concatenar(error);
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTextArea areaSalida;
    private javax.swing.JTextField campoEntrada;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    // End of variables declaration//GEN-END:variables
}
