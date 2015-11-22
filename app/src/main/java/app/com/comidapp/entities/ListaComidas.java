package app.com.comidapp.entities;

import java.util.ArrayList;

/**
 * Created by NoaD on 22/11/2015.
 */
public class ListaComidas {

    private ArrayList<Comida> comidas;

    public ListaComidas() {
        this.comidas = new ArrayList<>();
    }

    public ArrayList<Comida> getComidas() {
        return comidas;
    }

    public void setComidas(ArrayList<Comida> comidas) {
        this.comidas = comidas;
    }
}
