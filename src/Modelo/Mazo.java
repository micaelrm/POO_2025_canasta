
package Modelo;
import Modelo.Enum.*;
import java.io.Serializable;

import java.util.*;

public class Mazo {
    private Stack<Carta> cartas;

    public Mazo() { 
        this.cartas = new Stack<Carta>(); 
        crearMazo();
        mezclarMazo();
    }
    
    public void crearMazo() 
    {    
        Carta nuevaCarta;
        int jokers = 0;
        for (int numMazo = 1; numMazo < 3; numMazo++) 
        {
            for (Palo palo : Palo.values()) 
            {
                for (Valor valor : Valor.values()) 
                {
                    if (!valor.equals(Valor.JOKER) || jokers < 4) 
                    { 
                        if (valor.equals(Valor.JOKER)) jokers += 1; 
                        nuevaCarta = CreadorCarta.crear(palo, valor);
                        cartas.push(nuevaCarta);
                    }
                }
            }
        }
    }
    
    public void mezclarMazo() { Collections.shuffle(cartas); }
    
    public void mostrarMazo() {
        for (Carta carta : cartas) {
            System.out.println(carta.toString());
        }
    }
    
    public Integer getCantidad() {
        return cartas.size();
    }
    
    public Carta cederCarta() {  return cartas.isEmpty() ? null : cartas.pop(); }    
    
    public Carta cederNaturalTres() {
        Stack<Carta> aux = new Stack<>();
        Carta cartaBuscada = null;

        while (!cartas.isEmpty()) {
            Carta carta = cartas.pop();
            if (carta.getTipo() == Tipo.NATURAL || (carta.getTipo() == Tipo.TRES && carta.getColor() == Color.NEGRO)) {
                cartaBuscada = carta;
                break; 
            } else {
                aux.push(carta);
            }
        }

        while (!aux.isEmpty()) {
            cartas.push(aux.pop());
        }

        return cartaBuscada; 
    }
    
    public boolean siCero() { 
        return getCantidad() == 0; 
    }

    @Override
    public String toString() {
        return "Mazo{" + "cartas=" + cartas + '}';
    }
}