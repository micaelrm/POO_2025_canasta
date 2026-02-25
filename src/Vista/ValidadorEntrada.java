
package Vista;
import Modelo.Enum.*;
import java.util.*;
import javafx.util.Pair;

public class ValidadorEntrada {
    
    public boolean validarTomar(String entrada) {
        entrada = entrada.trim().toUpperCase();
        if ("MAZO".equals(entrada) || "DESCARTE".equals(entrada)) return true;
        else throw new IllegalArgumentException("Comando TOMAR invalido");
    }
    
    public Pair<Valor, Palo> validarDescartar(String entrada) {
        Pair<Valor, Palo> par = null;
        if (entrada == null || entrada.isBlank()) {
            throw new IllegalArgumentException("Comando DESCARTAR invalido");
        }

        String[] parsing = entrada.trim().toUpperCase().split("\\s+");

        if (parsing.length != 3) {
            throw new IllegalArgumentException("Comando DESCARTAR invalido");
        }

        if (!"DESCARTAR".equals(parsing[0])) {
            throw new IllegalArgumentException("Comando DESCARTAR invalido");
        }

        Valor valor = validarValor(parsing[1]); 
        Palo palo = validarPalo(parsing[2]);    

        if (valor == null || palo == null) {
            throw new IllegalArgumentException("Comando DESCARTAR invalido");
        }
        else par = new Pair<>(valor, palo);
        
        return par; 
    }
    
    public ArrayList<Pair <Valor, Palo>> validarCombinar(String entrada) {
        if (entrada == null || entrada.isBlank()) throw new IllegalArgumentException("[error]: entrada vacía");
        
        ArrayList<Pair <Valor, Palo>> pares = new ArrayList<>();
        Pair<Valor, Palo> par = null;
        String[] parsing = entrada.trim().toUpperCase().split("\\s+");
        Valor valor = null;
        Palo palo = null;
        
        if (parsing.length < 3) {
            throw new IllegalArgumentException("Comando COMBINAR invalido");
        }
        
        if (!"COMBINAR".equals(parsing[0])) {
            throw new IllegalArgumentException("Comando COMBINAR invalido");
        }
        
        for (int cantPalabras = 1; cantPalabras < parsing.length; cantPalabras += 2){
            valor = validarValor(parsing[cantPalabras]);
            palo = validarPalo(parsing[cantPalabras+1]);

            if (valor != null && palo != null || valor == Valor.JOKER) {
                par = new Pair<>(valor, palo);
                pares.add(par);
            }
            else {
                pares.clear();
                throw new IllegalArgumentException("Comando COMBINAR invalido");
            }
        }
        return pares;
    }
    
    
    public Valor validarValor(String palabra) {
        switch (palabra) {
            case "DOS":
                return Valor.DOS;
            case "TRES":
                return Valor.TRES;
            case "CUATRO":
                return Valor.CUATRO;
            case "CINCO":
                return Valor.CINCO;
            case "SEIS":
                return Valor.SEIS;
            case "SIETE":
                return Valor.SIETE;
            case "OCHO":
                return Valor.OCHO;
            case "NUEVE":
                return Valor.NUEVE;
            case "DIEZ":
                return Valor.DIEZ;
            case "J":
                return Valor.J;
            case "Q":
                return Valor.Q;
            case "K":
                return Valor.K;
            case "A":
                return Valor.A;
            case "JOKER":
                return Valor.JOKER;  
        }
        return null;
    }
    
    public Palo validarPalo(String palabra) {
        switch (palabra) {
            case "DIAMANTE":
                return Palo.DIAMANTE;
            case "CORAZON":
                return Palo.CORAZON;
            case "TREBOL":
                return Palo.TREBOL;
            case "PICA":
                return Palo.PICA;
        }
        
        return null;
    }
}
