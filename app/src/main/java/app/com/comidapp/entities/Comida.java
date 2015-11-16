package app.com.comidapp.entities;

/**
 * Created by dalgarin on 16/11/2015.
 */
public class Comida {

    private String nombre;
    private long precio;
    private long nitRestaurante;

    public Comida() {
    }

    public Comida(String nombre, long precio, long nitRestaurante) {
        this.nombre = nombre;
        this.precio = precio;
        this.nitRestaurante = nitRestaurante;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public long getPrecio() {
        return precio;
    }

    public void setPrecio(long precio) {
        this.precio = precio;
    }

    public long getNitRestaurante() {
        return nitRestaurante;
    }

    public void setNitRestaurante(long nitRestaurante) {
        this.nitRestaurante = nitRestaurante;
    }
}
