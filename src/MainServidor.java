import Modelo.Canasta;
import ar.edu.unlu.rmimvc.RMIMVCException;
import java.rmi.RemoteException;
import ar.edu.unlu.rmimvc.servidor.Servidor;


public class MainServidor {
    private static final String servidorHost = "127.0.0.1";
    private static final int servidorPort = 1234;
    
    public static void main(String[] args) throws RMIMVCException {
        Servidor servidor = new Servidor(servidorHost, servidorPort);
        try {
            Canasta modelo = new Canasta();
            servidor.iniciar(modelo);
        } catch (RemoteException e) {
        }
    }
}
