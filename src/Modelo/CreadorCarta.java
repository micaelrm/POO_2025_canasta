
package Modelo;

import Modelo.Enum.Color;
import Modelo.Enum.Palo;
import Modelo.Enum.Tipo;
import Modelo.Enum.Valor;


public class CreadorCarta {
        private static Color color;
        private static Palo palo;
        private static Valor valor;
        private static int puntaje;
        private static Tipo tipo;  
        private static boolean rotado;
        
        public static Carta crear(Palo palo, Valor valor) 
        {
            switch (valor) 
            {
                case A:
                    if (palo.equals(Palo.DIAMANTE) || palo.equals(Palo.CORAZON)) {
                        return crearDos(Color.ROJO, palo, valor, 20);
                    } else {
                        return crearCartaNatural(Color.NEGRO, palo, valor, 20);
                    }
                    //break;
                case K, Q, J, DIEZ, NUEVE, OCHO:
                    if (palo.equals(Palo.DIAMANTE) || palo.equals(Palo.CORAZON)) {
                        return crearCartaNatural(Color.ROJO, palo, valor, 10);
                    } else {
                        return crearCartaNatural(Color.NEGRO, palo, valor, 10);
                    }    
                    //break;
                case SIETE, SEIS, CINCO, CUATRO:
                    if (palo.equals(Palo.DIAMANTE) || palo.equals(Palo.CORAZON)) {
                        return crearCartaNatural(Color.ROJO, palo, valor, 5);
                    } else {
                        return crearCartaNatural(Color.NEGRO, palo, valor, 5);
                    }
                    //break;
                case TRES:
                    if (palo.equals(Palo.DIAMANTE) || palo.equals(Palo.CORAZON)) {
                        return crearTres(Color.ROJO, palo, valor, 100);
                    } else {
                        return crearTres(Color.NEGRO, palo, valor, 0);
                    }
                case DOS:
                    if (palo.equals(Palo.DIAMANTE) || palo.equals(Palo.CORAZON)) {
                        return crearDos(Color.ROJO, palo, valor, 20);
                    } else {
                        return crearDos(Color.NEGRO, palo, valor, 20);
                    }
                case JOKER:
                    return crearJoker();
            }
        return null;
        }
        
        public static Carta crearCartaNatural(Color color, Palo palo, Valor valor, int puntaje) {
            tipo = Tipo.NATURAL;
            rotado = false;
            
            return new Carta(color, palo, valor, puntaje, tipo, rotado);    
        }
        
        public static Carta crearDos(Color color, Palo palo, Valor valor, int puntaje) {
            tipo = Tipo.COMODIN;
            rotado = false;
            
            return new Carta(color, palo, valor, puntaje, tipo, rotado);     
        }
        
        public static Carta crearTres(Color color, Palo palo, Valor valor, int puntaje) {
            tipo = Tipo.TRES;
            rotado = false;
            
            return new Carta(color, palo, valor, puntaje, tipo, rotado);     
        }
        
        public static Carta crearJoker() {
            color = null;
            palo = null;
            valor = Valor.JOKER;
            puntaje = 50;
            tipo = Tipo.COMODIN;  
            rotado = false; 
            
            return new Carta(color, palo, valor, puntaje, tipo, rotado); 
        }        
}
