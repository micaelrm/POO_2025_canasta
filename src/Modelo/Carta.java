
package Modelo;

import Modelo.Enum.*;
import Modelo.Interfaz.ICarta;
import java.io.Serializable;

public class Carta implements ICarta, Serializable{
    private final Color color;
    private final Palo palo;
    private final Valor valor;
    private final int puntaje;
    private final Tipo tipo;  
    private boolean rotado;

    public Carta(Color color, Palo palo, Valor valor, int puntaje, Tipo tipo, boolean rotado) {
        this.color = color;
        this.palo = palo;
        this.valor = valor;
        this.puntaje = puntaje;
        this.tipo = tipo;
        this.rotado = rotado;
    }
    
    public Carta(Palo palo, Valor valor) {
        this(null, palo, valor, 0, null, false);
    }

    @Override
    public Color getColor() { return color; }

    @Override
    public Palo getPalo() { return palo; }

    @Override
    public Valor getValor() { return valor; }

    @Override
    public int getPuntaje() { return puntaje; }

    @Override
    public Tipo getTipo() { return tipo; }

    @Override
    public boolean getRotado() { return rotado; }
    
    public void rotado() { this.rotado = !this.rotado; }

    @Override
    public String toString() {
        return "Carta{" + "color=" + color + ", palo=" + palo + ", valor=" + valor + ", puntaje=" + puntaje + ", tipo=" + tipo +  ", rotado=" + rotado + '}';
    }
}
