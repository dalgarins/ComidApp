package app.com.comidapp.entities;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * Created by NoaD on 22/11/2015.
 */
public class ListaRestaurantes implements Serializable{

    private ArrayList<Restaurante> arrayList;

    public ListaRestaurantes() {
        this.arrayList = new ArrayList<>();
    }

    public ArrayList<Restaurante> getArrayList() {
        return arrayList;
    }

    public void setArrayList(ArrayList<Restaurante> arrayList) {
        this.arrayList = arrayList;
    }
}
