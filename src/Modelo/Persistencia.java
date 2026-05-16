package Modelo;

import java.io.*;
import java.rmi.RemoteException;
import java.util.*;


public class Persistencia {
    private static String ruta = "puntaje.dat";
    
    public static void guardar(ArrayList<RegistroPuntajes> registro) throws IOException, RemoteException {
        try (ObjectOutputStream objectOS = new ObjectOutputStream(new FileOutputStream(ruta))) { objectOS.writeObject(registro); }
    }
    
    public static ArrayList<RegistroPuntajes> leer() throws IOException, ClassNotFoundException {
        File archivo = new File(ruta);
        if (!archivo.exists() || archivo.length() == 0) { return null; }
        
        try (ObjectInputStream objectIS = new ObjectInputStream(new FileInputStream(archivo))) {
            return (ArrayList<RegistroPuntajes>) objectIS.readObject();
        }
    }
    
    /*
    public static void actualizar(ArrayList<RegistroPuntajes> nuevosRegistros) throws RemoteException {
        if (nuevosRegistros == null || nuevosRegistros.isEmpty()) { return; }
        boolean registroModificado = false;
        
        try {
            ArrayList<RegistroPuntajes> registrosGuardados = leer();
            ArrayList<RegistroPuntajes> registros = new ArrayList<>();

            for (RegistroPuntajes nuevoRegistro : nuevosRegistros) {
                registroModificado = false;
                String nuevoNombreJ1 = nuevoRegistro.getNombreJugador1();
                String nuevoNombreJ2 = nuevoRegistro.getNombreJugador2();
                int puntaje = nuevoRegistro.getPuntaje();
                for (RegistroPuntajes  registroGuardado : registrosGuardados) {
                    String viejoNombreJ1 = registroGuardado.getNombreJugador1();
                    String viejoNombreJ2 = registroGuardado.getNombreJugador2();
                    
                    if ((nuevoNombreJ1.equals(viejoNombreJ1)) && (nuevoNombreJ2.equals(viejoNombreJ2)) || 
                    (nuevoNombreJ1.equals(viejoNombreJ2)) && (nuevoNombreJ2.equals(viejoNombreJ1))) {
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
    
    */
    
    public static void actualizar(ArrayList<RegistroPuntajes> nuevosRegistros) throws RemoteException, IOException, ClassNotFoundException {
        if (nuevosRegistros == null || nuevosRegistros.isEmpty()) { return; }

        
            ArrayList<RegistroPuntajes> registrosGuardados = leer();
            
            if (registrosGuardados == null) {
                guardar(nuevosRegistros);
                return;
            }

            for (RegistroPuntajes nuevoRegistro : nuevosRegistros) {
                boolean registroModificado = false;
                String nuevoNombreJ1 = nuevoRegistro.getNombreJugador1().trim();
                String nuevoNombreJ2 = nuevoRegistro.getNombreJugador2().trim();
                int puntaje = nuevoRegistro.getPuntajeAnterior();
                
                for (RegistroPuntajes registroGuardado : registrosGuardados) {
                    String viejoNombreJ1 = registroGuardado.getNombreJugador1().trim();
                    String viejoNombreJ2 = registroGuardado.getNombreJugador2().trim();
                    
                    if ((nuevoNombreJ1.equalsIgnoreCase(viejoNombreJ1) && nuevoNombreJ2.equalsIgnoreCase(viejoNombreJ2)) || 
                        (nuevoNombreJ1.equalsIgnoreCase(viejoNombreJ2) && nuevoNombreJ2.equalsIgnoreCase(viejoNombreJ1))) {
                        
                        registroGuardado.setPuntajeAnterior(puntaje);
                        registroModificado = true;
                        break; 
                    }
                }
                
                if (!registroModificado) {
                    registrosGuardados.add(nuevoRegistro);
                }
            }

            guardar(registrosGuardados);
    }
}


