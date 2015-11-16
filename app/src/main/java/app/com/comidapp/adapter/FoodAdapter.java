package app.com.comidapp.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.util.ArrayList;

import app.com.comidapp.R;
import app.com.comidapp.entities.Comida;
import app.com.comidapp.entities.Restaurante;

/**
 * Created by NoaD on 16/11/2015.
 */
public class FoodAdapter extends ArrayAdapter<Comida> {

    public FoodAdapter(Context context, ArrayList<Comida> valuesList){
        super(context, 0, valuesList);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        LayoutInflater inflater = (LayoutInflater) getContext()
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);


        //Salvando la referencia del View de la fila
        View listItemView = convertView;

        //Comprobando si el View no existe
        if (null == convertView) {
            //Si no existe, entonces inflarlo con list_item_food.xml
            listItemView = inflater.inflate(
                    R.layout.list_item_food,
                    parent,
                    false);
        }

        Comida comida = getItem(position);

        TextView txtName = (TextView)listItemView.findViewById(R.id.txtname_item_food);
        txtName.setText(comida.getNombre());

        TextView txtPrice = (TextView)listItemView.findViewById(R.id.txtprice_item_food);
        txtPrice.setText(String.valueOf(comida.getPrecio()));

        EditText txtCantidad = (EditText)listItemView.findViewById(R.id.txtquantity_item_food);
        txtCantidad.setText("0");

        Button btnComprar = (Button) listItemView.findViewById(R.id.btncomprar_item_food);

        return listItemView;

    }
}
