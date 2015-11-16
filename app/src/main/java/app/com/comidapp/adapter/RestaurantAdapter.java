package app.com.comidapp.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

import app.com.comidapp.R;
import app.com.comidapp.entities.Restaurante;

/**
 * Created by dalgarin on 16/11/2015.
 */
public class RestaurantAdapter extends ArrayAdapter<Restaurante> {


    public RestaurantAdapter(Context context, ArrayList<Restaurante> valuesList){
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
            //Si no existe, entonces inflarlo con list_item_restaurant.xml
            listItemView = inflater.inflate(
                    R.layout.list_item_restaurant,
                    parent,
                    false);
        }

        Restaurante restaurante = getItem(position);

        TextView txtName = (TextView)listItemView.findViewById(R.id.txtname_item_restaurant);
        txtName.setText(restaurante.getNombre());

        TextView txtAddress = (TextView)listItemView.findViewById(R.id.txtaddress_item_restaurant);
        txtAddress.setText(restaurante.getDireccion());

        TextView txtPhone = (TextView)listItemView.findViewById(R.id.txtphone_item_restaurant);
        txtPhone.setText(restaurante.getDireccion());

        /*ImageView imageViewPhoto = (ImageView) listItemView.findViewById(R.id.img_item_restaurant);

        //Agrega el icono si el prosesor ha sido evaluado o no
        if (!restaurante.getEstadoEval().equalsIgnoreCase(listItemView.getResources().getString(R.string.estadoProfesorFinalizado))) {
            imageViewPhoto.setImageDrawable(getContext().getResources().getDrawable(R.drawable.error_check));
        }else {
            imageViewPhoto.setImageDrawable(getContext().getResources().getDrawable(R.drawable.ok_ckeck));
        }*/
        return listItemView;
    }
}
