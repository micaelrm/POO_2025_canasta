package Modelo;

import java.io.*;
import java.rmi.RemoteException;
import java.util.*;


public class Persistencia {
    private static String ruta = "puntaje.dat";
    
    private static void guardar(ArrayList<RegistroPuntajes> registro) throws IOException {
        try (ObjectOutputStream objectOS = new ObjectOutputStream(new FileOutputStream(ruta))) { objectOS.writeObject(registro); }
    }
    
    public static ArrayList<RegistroPuntajes> leer() throws IOException, ClassNotFoundException {
        File archivo = new File(ruta);
        if (!archivo.exists() || archivo.length() == 0) { return null; }
        
        try (ObjectInputStream objectIS = new ObjectInputStream(new FileInputStream(archivo))) {
            return (ArrayList<RegistroPuntajes>) objectIS.readObject();
        }
    }
    
    public static void actualizarPuntaje(ArrayList<RegistroPuntajes> nuevosRegistros) throws RemoteException {
        if (nuevosRegistros == null || nuevosRegistros.isEmpty()) { return; }

        try {
            ArrayList<RegistroPuntajes> registrosGuardados = leer();
            ArrayList<RegistroPuntajes> registros = new ArrayList<>();

            for (RegistroPuntajes nuevoRegistro : nuevosRegistros) {
                boolean registroModificado = false;
                String nuevoNombreJ1 = nuevoRegistro.getNombreJugador1();
                String nuevoNombreJ2 = nuevoRegistro.getNombreJugador2();
                int puntaje = nuevoRegistro.getPuntaje();
                for (RegistroPuntajes  registroGuardado : registrosGuardados) {
                    String viejoNombreJ1 = registroGuardado.getNombreJugador1();
                    String viejoNombreJ2 = registroGuardado.getNombreJugador2();
                    
                    if ((nuevoNombreJ1 == viejoNombreJ1 && nuevoNombreJ2 == viejoNombreJ2) || 
                    (nuevoNombreJ1 == viejoNombreJ2 && nuevoNombreJ1 == viejoNombreJ1)) {
                        registroGuardado.setPuntaje(puntaje);
                        registroModificado = true;
                        registros.add(registroGuardado);
                    }
                }
                if (!registroModificado) registros.add(nuevoRegistro);
            }

            guardar(registros);

        } catch (IOException | ClassNotFoundException e) {
            throw new RemoteException("Error al re escribir puntaje");
        }
    }
}


