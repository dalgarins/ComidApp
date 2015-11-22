package app.com.comidapp.listener;

import android.content.Intent;
import android.view.View;
import android.widget.AdapterView;

import app.com.comidapp.ListFoodActivity;
import app.com.comidapp.ListRestaurantActivity;
import app.com.comidapp.asynctask.GetFoodAsync;
import app.com.comidapp.entities.Restaurante;

/**
 * Created by NoaD on 22/11/2015.
 */
public class OnItemClickListenerListViewRestaurant implements AdapterView.OnItemClickListener {

    private ListRestaurantActivity listRestaurantActivity;
    private Restaurante restaurante;

    public OnItemClickListenerListViewRestaurant(ListRestaurantActivity lstRestaurant){
        this.listRestaurantActivity = lstRestaurant;
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

        restaurante = (Restaurante) parent.getItemAtPosition(position);
        Intent i = new Intent(listRestaurantActivity.getApplicationContext(), ListFoodActivity.class);
        i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);//para poder ejecutar una activity desde un contexto
        new GetFoodAsync(listRestaurantActivity, i).execute(restaurante.getNit());
    }
}
