package Vista;

import Modelo.Enum.*;
import Modelo.Interfaz.ICarta;
import Modelo.Interfaz.ICombinacion;
import Modelo.Interfaz.IControlador;
import Modelo.Interfaz.IEquipo;
import Modelo.Interfaz.IJugador;
import Modelo.Interfaz.IVista;
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Image;
import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.List;
import javafx.util.Pair;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JToggleButton;
import javax.swing.SwingConstants;

public class VistaGrafica extends javax.swing.JFrame implements IVista{
    
    private static final java.util.logging.Logger logger = java.util.logging.Logger.getLogger(VistaGrafica.class.getName());
    private IControlador controlador;
    private ArrayList<JToggleButton> listaCombinacion;
    private ICarta cartaBloqueante;
    
    public VistaGrafica(IControlador controlador) {
        this.controlador = controlador;
        listaCombinacion = new ArrayList();
        
        PanelFondo panelFondo = new PanelFondo("/png/fondo.jpg");
        panelFondo.setLayout(new java.awt.BorderLayout());
        this.setContentPane(panelFondo);
        
        initComponents();
        txtInfo.setForeground(java.awt.Color.BLACK);
        txtInfo.setEnabled(false);
        lblPuntaje1.setEnabled(false);
        lblPuntajeMin.setEnabled(false);
        
        this.setSize(800, 486);
        this.setResizable(false);
        this.setTitle("Canasta - Vista Gráfica");
        this.setLocationRelativeTo(null);
    }

    @SuppressWarnings("unchecked") 
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        panelBtns = new javax.swing.JPanel();
        btnCombinar = new javax.swing.JButton();
        btnDescartar = new javax.swing.JButton();
        btnRetirarse = new javax.swing.JButton();
        panelJ1 = new javax.swing.JPanel();
        panelCombE = new javax.swing.JPanel();
        panelJ3 = new javax.swing.JPanel();
        panelCombR = new javax.swing.JPanel();
        panelJ2 = new javax.swing.JPanel();
        panelCartas = new javax.swing.JPanel();
        btnCDescarte = new javax.swing.JButton();
        btnMazo = new javax.swing.JButton();
        panelJ4 = new javax.swing.JPanel();
        txtInfo = new javax.swing.JTextField();
        lblPuntajeMin = new javax.swing.JLabel();
        lblPuntaje1 = new javax.swing.JLabel();
        btnF = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        panelBtns.setOpaque(false);

        btnCombinar.setText("Combinar");
        btnCombinar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCombinarActionPerformed(evt);
            }
        });

        btnDescartar.setText("Descartar");
        btnDescartar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnDescartarActionPerformed(evt);
            }
        });

        btnRetirarse.setText("Retirarse");
        btnRetirarse.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnRetirarseActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout panelBtnsLayout = new javax.swing.GroupLayout(panelBtns);
        panelBtns.setLayout(panelBtnsLayout);
        panelBtnsLayout.setHorizontalGroup(
            panelBtnsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelBtnsLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(panelBtnsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(btnDescartar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btnCombinar, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                    .addComponent(btnRetirarse, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap(10, Short.MAX_VALUE))
        );
        panelBtnsLayout.setVerticalGroup(
            panelBtnsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelBtnsLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(btnCombinar)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnDescartar)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnRetirarse)
                .addContainerGap(7, Short.MAX_VALUE))
        );

        panelJ1.setOpaque(false);

        panelCombE.setOpaque(false);

        panelJ3.setOpaque(false);

        javax.swing.GroupLayout panelJ3Layout = new javax.swing.GroupLayout(panelJ3);
        panelJ3.setLayout(panelJ3Layout);
        panelJ3Layout.setHorizontalGroup(
            panelJ3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 60, Short.MAX_VALUE)
        );
        panelJ3Layout.setVerticalGroup(
            panelJ3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 100, Short.MAX_VALUE)
        );

        panelCombR.setOpaque(false);

        panelJ2.setOpaque(false);

        javax.swing.GroupLayout panelJ2Layout = new javax.swing.GroupLayout(panelJ2);
        panelJ2.setLayout(panelJ2Layout);
        panelJ2Layout.setHorizontalGroup(
            panelJ2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 150, Short.MAX_VALUE)
        );
        panelJ2Layout.setVerticalGroup(
            panelJ2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 100, Short.MAX_VALUE)
        );

        panelCartas.setOpaque(false);

        btnCDescarte.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCDescarteActionPerformed(evt);
            }
        });

        btnMazo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnMazoActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout panelCartasLayout = new javax.swing.GroupLayout(panelCartas);
        panelCartas.setLayout(panelCartasLayout);
        panelCartasLayout.setHorizontalGroup(
            panelCartasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelCartasLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(btnMazo)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 36, Short.MAX_VALUE)
                .addComponent(btnCDescarte)
                .addGap(21, 21, 21))
        );
        panelCartasLayout.setVerticalGroup(
            panelCartasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelCartasLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(panelCartasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(btnMazo, javax.swing.GroupLayout.DEFAULT_SIZE, 119, Short.MAX_VALUE)
                    .addComponent(btnCDescarte, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );

        panelJ4.setOpaque(false);

        javax.swing.GroupLayout panelJ4Layout = new javax.swing.GroupLayout(panelJ4);
        panelJ4.setLayout(panelJ4Layout);
        panelJ4Layout.setHorizontalGroup(
            panelJ4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 150, Short.MAX_VALUE)
        );
        panelJ4Layout.setVerticalGroup(
            panelJ4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 100, Short.MAX_VALUE)
        );

        lblPuntajeMin.setText("jLabel1");

        lblPuntaje1.setText("jLabel1");

        btnF.setText("F");
        btnF.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnFActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(panelCombE, javax.swing.GroupLayout.PREFERRED_SIZE, 213, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(panelJ2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(18, 18, 18)
                                .addComponent(panelCartas, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(panelJ3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(118, 118, 118)))
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(panelCombR, javax.swing.GroupLayout.PREFERRED_SIZE, 213, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                .addComponent(panelJ4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(28, 28, 28))))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(txtInfo)
                                .addGap(264, 264, 264))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(panelJ1, javax.swing.GroupLayout.PREFERRED_SIZE, 542, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 29, Short.MAX_VALUE)))
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(btnF, javax.swing.GroupLayout.PREFERRED_SIZE, 49, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(panelBtns, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(lblPuntaje1, javax.swing.GroupLayout.DEFAULT_SIZE, 199, Short.MAX_VALUE)
                            .addComponent(lblPuntajeMin, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(panelCombE, javax.swing.GroupLayout.DEFAULT_SIZE, 131, Short.MAX_VALUE)
                            .addComponent(panelCombR, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(layout.createSequentialGroup()
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(panelJ4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(panelJ2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(panelJ3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(panelCartas, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(txtInfo, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(lblPuntajeMin)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(lblPuntaje1)))
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(18, 18, Short.MAX_VALUE)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(panelBtns, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(panelJ1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(30, 30, 30)
                        .addComponent(btnF)
                        .addGap(0, 0, Short.MAX_VALUE))))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnCombinarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCombinarActionPerformed
        ArrayList<Pair<Valor, Palo>> pares = new ArrayList<>();

        for (JToggleButton btnCarta : listaCombinacion) {
            if (btnCarta.isSelected()) {

                Valor v = (Valor) btnCarta.getClientProperty("valor");
                Palo p = (Palo) btnCarta.getClientProperty("palo");

                if (v != null && p != null) {
                    pares.add(new Pair<>(v, p));
                }
            }
        }

        if (pares.isEmpty()) {
            System.out.println("No seleccionaste ninguna carta para combinar.");
        } else {
            try {
                controlador.combinacion(pares);
            } catch (Exception ex) {
                mostrarMensajeError(ex.getMessage());
            }
            System.out.println("Intentando combinar " + pares.size() + " cartas.");
        }
    }//GEN-LAST:event_btnCombinarActionPerformed

    private void btnDescartarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnDescartarActionPerformed
        try {
            int cantSelec = 0;
            Pair<Valor, Palo> par = null;
            
            for (JToggleButton btnCarta : listaCombinacion) {
                if (btnCarta.isSelected()) {
                    cantSelec++;
                    Valor v = (Valor) btnCarta.getClientProperty("valor");
                    Palo p = (Palo) btnCarta.getClientProperty("palo");
                    par = new Pair<>(v, p);
                    System.out.println(par);
                }
                
                if (cantSelec > 1) throw new RemoteException("ERROR: trato de descartar 2 cartas a la vez");
            }
        
            controlador.descartar(par);
        } catch (Exception ex) {
            mostrarMensajeError(ex.getMessage());
        }
    }//GEN-LAST:event_btnDescartarActionPerformed

    private void btnCDescarteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCDescarteActionPerformed
        try {
            EstadoTurno estadoTurno = controlador.getEstadoTurno();
            if (estadoTurno.equals(EstadoTurno.TOMAR_CARTA)) {
                controlador.tomar("DESCARTE");
            }
        } catch (Exception ex) {
            mostrarMensajeError(ex.getMessage());
        }
    }//GEN-LAST:event_btnCDescarteActionPerformed

    private void btnMazoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnMazoActionPerformed
        try {
            EstadoTurno estadoTurno = controlador.getEstadoTurno();
            if (estadoTurno.equals(EstadoTurno.TOMAR_CARTA)) {
                controlador.tomar("MAZO");
            }
        } catch (Exception ex) {
            mostrarMensajeError(ex.getMessage());
        }
    }//GEN-LAST:event_btnMazoActionPerformed

    private void btnRetirarseActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnRetirarseActionPerformed
        try {
            btnRetirarse.setEnabled(false);
            controlador.solicitarRetiro();
        } catch (Exception ex) {
            mostrarMensajeError(ex.getMessage());
        }
    }//GEN-LAST:event_btnRetirarseActionPerformed

    private void btnFActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnFActionPerformed
        mostrarFin();
    }//GEN-LAST:event_btnFActionPerformed

    @Override
    public void responderSolicitudRetiro() {
        Object[] opciones = {"Aceptar", "Rechazar"};

        int seleccion = JOptionPane.showOptionDialog(
            this, 
            "Tu compañero quiere abandonar la partida", 
            "Solicitud de Retiro", 
            JOptionPane.YES_NO_OPTION, 
            JOptionPane.WARNING_MESSAGE, 
            null,                       
            opciones,                   
            opciones[0]                 
        );

        try {
            if (seleccion == 0) { controlador.responderRetiro(true); } 
            else { controlador.responderRetiro(false); }
        } catch (Exception ex) {
            mostrarMensajeError(ex.getMessage());
        }
    }
    
    private void mostrarPanelComb(JPanel panelComb, IEquipo equipo) throws RemoteException {
        panelComb.removeAll();
        panelComb.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.LEFT, 10, 5));
        List<? extends ICombinacion> combinaciones = equipo.getCombinaciones();

        for(ICombinacion combinacion : combinaciones) {
            ICarta carta = combinacion.getListaCombinacion().getFirst();
            int cantidad = combinacion.getListaCombinacion().size();

            ImageIcon icono = getIconoCarta(carta, 25, 45, Image.SCALE_SMOOTH);
            JLabel labelCombinacion = new JLabel();

            labelCombinacion.setIcon(icono);
            labelCombinacion.setText(String.valueOf(cantidad));

            panelComb.add(labelCombinacion);
        }
        
        panelComb.revalidate();
        panelComb.repaint();  
    }
        
    private void mostrarPanelJ(IJugador jugador, JPanel panelJ) throws RemoteException {
        panelJ.removeAll();

        panelJ.setLayout(new javax.swing.BoxLayout(panelJ, javax.swing.BoxLayout.Y_AXIS));

        String nombreJugador = jugador.getNombre(); 
        int cantidadCartas = jugador.getMano().getCartas().size();

        JLabel lblNombre = new JLabel(nombreJugador != null ? nombreJugador : "Jugador");
        lblNombre.setAlignmentX(java.awt.Component.CENTER_ALIGNMENT); 
        lblNombre.setFont(new java.awt.Font("Arial", java.awt.Font.BOLD, 14));
        lblNombre.setForeground(java.awt.Color.WHITE);

        ImageIcon iconoUsuario = cargarRecurso("/png/imagen_usuario.png", 50, 50, Image.SCALE_SMOOTH);
        JLabel lblUsuario = new JLabel(iconoUsuario);
        lblUsuario.setAlignmentX(java.awt.Component.CENTER_ALIGNMENT); 

        JPanel panelInfoCartas = new JPanel();
        panelInfoCartas.setOpaque(false);
        panelInfoCartas.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.CENTER, 5, 0));
        panelInfoCartas.setMaximumSize(new Dimension(100, 50)); 
        panelInfoCartas.setAlignmentX(java.awt.Component.CENTER_ALIGNMENT);

        JLabel lblMazoImg = new JLabel(cargarRecurso("/png/card_back_red.png", 20, 35, Image.SCALE_SMOOTH));
        JLabel lblCantidad = new JLabel("x" + cantidadCartas);
        lblCantidad.setFont(new java.awt.Font("Arial", java.awt.Font.BOLD, 14));
        lblCantidad.setForeground(java.awt.Color.WHITE);

        panelInfoCartas.add(lblMazoImg);
        panelInfoCartas.add(lblCantidad);

        panelJ.add(javax.swing.Box.createVerticalGlue()); 

        panelJ.add(lblNombre); 
        panelJ.add(javax.swing.Box.createRigidArea(new Dimension(0, 8))); 
        panelJ.add(lblUsuario);
        panelJ.add(javax.swing.Box.createRigidArea(new Dimension(0, 8))); 
        panelJ.add(panelInfoCartas); 

        panelJ.add(javax.swing.Box.createVerticalGlue()); 

        panelJ.revalidate();
        panelJ.repaint();
    }
   
    private void mostrarPanelesJ() throws RemoteException {
        mostrarPanelJ(controlador.getAmigo(controlador.getJugador()), panelJ3);
        IEquipo equipoRival = controlador.getEquipoRival(controlador.getJugador());
        List<? extends IJugador> jugadoresRivales = equipoRival.getJugadores();
        
        mostrarPanelJ(jugadoresRivales.getFirst(), panelJ2);
        mostrarPanelJ(jugadoresRivales.getLast(), panelJ4);
    }
    
    private void mostrarPanelCartas() throws RemoteException {
        ICarta topeDescarte = controlador.getTopeDescarte();
        ImageIcon icono = getIconoCarta(topeDescarte, 70, 110, Image.SCALE_SMOOTH);
        EstadoDescarte estadoDescarte = controlador.getEstadoDescarte();
        
        switch (estadoDescarte) {
            case INICIO:
                btnCDescarte.setEnabled(false);
                btnCDescarte.setIcon(icono);
                break;
            case BLOQUEADA:
                btnCDescarte.setIcon(rotarIcono(icono));
                btnCDescarte.setEnabled(false);
                cartaBloqueante = (ICarta) controlador.getTopeDescarte();
                System.out.println(cartaBloqueante);
                break;
            case CONGELADA:
                ImageIcon iconoBloqueante = getIconoCarta(cartaBloqueante, 70, 110, Image.SCALE_SMOOTH);
                ImageIcon iconoBloqueanteRotado = rotarIcono(iconoBloqueante);
                btnCDescarte.setIcon(iconoDescarteCongelada(iconoBloqueanteRotado, icono)); 
                break;
            default:
                btnCDescarte.setIcon(icono);
                break;
        }
        
        btnCDescarte.putClientProperty("valor", topeDescarte.getValor());
        btnCDescarte.putClientProperty("palo", topeDescarte.getPalo());
        
        btnMazo.setIcon(cargarRecurso("/png/card_back_red.png", 70, 110, Image.SCALE_SMOOTH));
        
        btnCDescarte.setContentAreaFilled(false);
        btnCDescarte.setBorderPainted(false);
        
        btnMazo.setContentAreaFilled(false);
        btnMazo.setBorderPainted(false);
        
        panelCartas.revalidate();
        panelCartas.repaint(); 
    }
    
    private void mostrarPanelJ1() throws RemoteException {
        panelJ1.removeAll(); 
        panelJ1.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.CENTER, -45, 5));
        listaCombinacion.clear();
        List<? extends ICarta> cartas = controlador.getJugador().getMano().getCartas();

        for (ICarta carta : cartas) {
            JToggleButton btnCarta = new JToggleButton();
            ImageIcon icono = getIconoCarta(carta, 50, 90, Image.SCALE_SMOOTH);

            if (icono != null) { btnCarta.setIcon(icono);
            } else { btnCarta.setText(carta.toString()); }

            btnCarta.setContentAreaFilled(false);
            btnCarta.setBorderPainted(false);
            btnCarta.setFocusPainted(true); 

            btnCarta.putClientProperty("valor", carta.getValor());
            btnCarta.putClientProperty("palo", carta.getPalo());

            btnCarta.addActionListener(e -> { btnCarta.setBorderPainted(btnCarta.isSelected()); });

            panelJ1.add(btnCarta); 
            listaCombinacion.add(btnCarta);
        }

        panelJ1.revalidate();
        panelJ1.repaint();       
    }
    
    private ImageIcon getIconoCarta(ICarta carta, int grosor, int altura, int escala) {
        String nombre;
        System.out.println(carta.toString());
        if (carta.getValor() == Valor.JOKER) {
            nombre = "black_joker.png";
        } else {
            String valor = carta.getValor().toString().toLowerCase();
            switch (valor) {
                case "a":
                    valor = "ace";
                    break;
                case "k":
                    valor = "king";
                    break;
                case "q":
                    valor = "queen";
                    break;
                case "j":
                    valor = "jack";
                    break;
                default:
                    valor = String.valueOf(carta.getValor().ordinal() + 2);
                    break;
            }

            String palo = carta.getPalo().toString().toLowerCase();
            switch (palo) {
                case "corazon":
                    palo = "hearts";
                    break;
                case "diamante":
                    palo = "diamonds";
                    break;
                case "trebol":
                    palo = "clubs";
                    break;
                default:
                    palo = "spades";
                    break;
            }

            nombre = valor + "_of_" + palo + ".png";
        }
        return cargarRecurso("/png/" + nombre, grosor, altura, escala);
    }

    private ImageIcon cargarRecurso(String ruta,  int grosor, int altura, int escala) {
        java.net.URL imgURL = getClass().getResource(ruta);
        if (imgURL != null) {
            java.awt.Image img = new ImageIcon(imgURL).getImage();
            return new ImageIcon(img.getScaledInstance(grosor, altura, escala));
        }
        else {
            System.out.println("ERROR: No se encontró el archivo en: " + ruta);
            return null;
        }
}
    
    private ImageIcon rotarIcono(ImageIcon icono) {
        Image img = icono.getImage();
        int ancho = icono.getIconWidth();
        int alto = icono.getIconHeight();

        java.awt.image.BufferedImage bi = new java.awt.image.BufferedImage(
            alto, ancho, java.awt.image.BufferedImage.TYPE_INT_ARGB
        );

        java.awt.Graphics2D g2d = bi.createGraphics();

        g2d.setRenderingHint(java.awt.RenderingHints.KEY_ANTIALIASING, 
                             java.awt.RenderingHints.VALUE_ANTIALIAS_ON);

        g2d.setRenderingHint(java.awt.RenderingHints.KEY_INTERPOLATION, 
                             java.awt.RenderingHints.VALUE_INTERPOLATION_BILINEAR);

        g2d.translate(alto, 0);
        g2d.rotate(Math.PI / 2);

        g2d.drawImage(img, 0, 0, null);
        g2d.dispose();

        return new ImageIcon(bi);
    }
    
    private ImageIcon iconoDescarteCongelada(ImageIcon iconoFondoRotado, ImageIcon iconoFrenteVertical) {
        int anchoMax = Math.max(iconoFondoRotado.getIconWidth(), iconoFrenteVertical.getIconWidth());
        int altoMax = Math.max(iconoFondoRotado.getIconHeight(), iconoFrenteVertical.getIconHeight());

        java.awt.image.BufferedImage bi = new java.awt.image.BufferedImage(
            anchoMax, altoMax, java.awt.image.BufferedImage.TYPE_INT_ARGB
        );

        java.awt.Graphics2D g2d = bi.createGraphics();
        g2d.setRenderingHint(java.awt.RenderingHints.KEY_ANTIALIASING, java.awt.RenderingHints.VALUE_ANTIALIAS_ON);

        int xRotada = (anchoMax - iconoFondoRotado.getIconWidth()) / 2;
        int yRotada = (altoMax - iconoFondoRotado.getIconHeight()) / 2;
        g2d.drawImage(iconoFondoRotado.getImage(), xRotada, yRotada, null);
        
        int xVertical = (anchoMax - iconoFrenteVertical.getIconWidth()) / 2;
        int yVertical = (altoMax - iconoFrenteVertical.getIconHeight()) / 2;
        g2d.drawImage(iconoFrenteVertical.getImage(), xVertical, yVertical, null);

        g2d.dispose();
        return new ImageIcon(bi);
    }
    
    private void mostrarPuntaje() {
        try {
            IEquipo equipo = controlador.getEquipo(controlador.getJugador());
            lblPuntaje1.setText("Puntaje equipo: " + equipo.getPuntaje().toString());
            lblPuntajeMin.setText("Puntaje comb. minima: " + equipo.getPuntajeCombinacionMinima().toString());
        } catch (Exception ex) {
            mostrarMensajeError(ex.getMessage());
        }
    }
    
    @Override
    public void mostrarJuego() {
        try {
            mostrarPanelesJ();
            mostrarPanelComb(panelCombR, controlador.getEquipoRival(controlador.getJugador()));
            mostrarPanelComb(panelCombE, controlador.getJugador().getEquipo());
            habilitarControles();
            mostrarPanelJ1();
            mostrarPanelCartas();
            mostrarPuntaje();
        } catch (Exception ex) {
            mostrarMensajeError(ex.getMessage());
        }
    }

    @Override
    public void mostrarFin() {
        try {
            panelCartas.removeAll();
            mostrarEquipoGanador();
            controlador.terminarJuego();
        } catch (Exception ex) {
            mostrarMensajeError(ex.getMessage());
        }
    }

    @Override
    public IVista getVista() {
        return this;
    }
    
    public void habilitarControles() 
    {        
        try {
            boolean activado = false;
            boolean turno = controlador.validarJugadorEnTurno();
            EstadoJuego estadoJuego = controlador.getEstadoJuego();
            if (estadoJuego == EstadoJuego.CORRIENDO && turno) { activado = true; }
            btnCombinar.setEnabled(activado);
            btnDescartar.setEnabled(activado);
            btnRetirarse.setEnabled(activado);
            btnMazo.setEnabled(activado);
            btnCDescarte.setEnabled(activado);
            if(activado) txtInfo.setText("Es su turno!");
            else txtInfo.setText("No es su turno!");
        } catch (Exception ex) {
            mostrarMensajeError(ex.getMessage());
        }
    }
    
    public void mostrarEquipoGanador() {
        try {
            IEquipo equipoGanador = controlador.getGanador();
            IEquipo equipoJugador = controlador.getEquipo(controlador.getJugador());

            JLabel lblResultado = new JLabel();
            lblResultado.setHorizontalAlignment(SwingConstants.CENTER);
            lblResultado.setVerticalAlignment(SwingConstants.CENTER);
            lblResultado.setFont(new Font("Arial", Font.BOLD, 60)); 

            if (equipoGanador.getId() == equipoJugador.getId()) { txtInfo.setText("¡GANASTE!"); } 
            else { txtInfo.setText("¡PERDISTE!"); }

            panelCartas.setLayout(new BorderLayout());
            panelCartas.add(lblResultado, BorderLayout.CENTER);
            panelCartas.revalidate();
            panelCartas.repaint();
                
        } catch (Exception ex) {
            mostrarMensajeError(ex.getMessage());
        }
    }
    
    public void mostrarMensajeError(String error) {
        JOptionPane.showMessageDialog(this, error);
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnCDescarte;
    private javax.swing.JButton btnCombinar;
    private javax.swing.JButton btnDescartar;
    private javax.swing.JButton btnF;
    private javax.swing.JButton btnMazo;
    private javax.swing.JButton btnRetirarse;
    private javax.swing.JLabel lblPuntaje1;
    private javax.swing.JLabel lblPuntajeMin;
    private javax.swing.JPanel panelBtns;
    private javax.swing.JPanel panelCartas;
    private javax.swing.JPanel panelCombE;
    private javax.swing.JPanel panelCombR;
    private javax.swing.JPanel panelJ1;
    private javax.swing.JPanel panelJ2;
    private javax.swing.JPanel panelJ3;
    private javax.swing.JPanel panelJ4;
    private javax.swing.JTextField txtInfo;
    // End of variables declaration//GEN-END:variables

}
