import Controlador.Controlador;
import ar.edu.unlu.rmimvc.RMIMVCException;
import java.rmi.RemoteException;
/*
import ar.edu.unlu.rmimvc.cliente.Cliente;
import Modelo.Interfaz.IControlador;
import Modelo.Interfaz.IVista;
import Vista.VistaConsola;
import Vista.VistaGrafica;
import ar.edu.unlu.rmimvc.cliente.IControladorRemoto;

public class MainCliente {
    private static final String clienteHost = "127.0.0.1";
    private static final String servidorHost = "127.0.0.1";
    private static final int clientePort = 0;
    private static final int servidorPort = 1234;

    public static void main (String[] args) throws RemoteException {
        try {
            IControlador controlador = new Controlador(); 
            Cliente cliente = new Cliente(clienteHost, clientePort, servidorHost, servidorPort);
            cliente.iniciar((IControladorRemoto) controlador);
            //IVista vista = new VistaConsola(controlador);
            IVista vista = new VistaGrafica(controlador);
            java.awt.EventQueue.invokeLater(() -> vista.setVisible(true));
            controlador.setVista(vista);
            //controlador.subscribir();
            controlador.conectar();
        } catch (RMIMVCException e) {
        }
    }
}
*/

import ar.edu.unlu.rmimvc.cliente.Cliente;
import Modelo.Interfaz.IControlador;
import Modelo.Interfaz.IVista;
import Vista.VistaConsola;
import Vista.VistaGrafica;
import Vista.*;
import ar.edu.unlu.rmimvc.cliente.IControladorRemoto;
import javax.swing.JFrame;

public class MainCliente {
    private static final String clienteHost = "127.0.0.1";
    private static final String servidorHost = "127.0.0.1";
    private static final int clientePort = 0;
    private static final int servidorPort = 1234;

    public static void main (String[] args) throws RemoteException {
        try {
            IControlador controlador = new Controlador(); 
            Cliente cliente = new Cliente(clienteHost, clientePort, servidorHost, servidorPort);
            cliente.iniciar((IControladorRemoto) controlador);
            JFrame vistaMenu = new vistaMenu(controlador);
            java.awt.EventQueue.invokeLater(() -> vistaMenu.setVisible(true));
        } catch (RMIMVCException e) {
        }
    }
}