package app.com.comidapp.entities;

import java.io.Serializable;
import java.util.LinkedList;

/**
 * Created by dalgarin on 16/11/2015.
 */
public class Pedido implements Serializable{

    private LinkedList<Comida> listaPedidos;

    public Pedido() {
        listaPedidos = new LinkedList<>();
    }

    public LinkedList<Comida> getListaPedidos() {
        return listaPedidos;
    }

    public void setListaPedidos(LinkedList<Comida> listaPedidos) {
        this.listaPedidos = listaPedidos;
    }
}
