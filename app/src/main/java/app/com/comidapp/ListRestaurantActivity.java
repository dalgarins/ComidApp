package app.com.comidapp;

import android.view.Menu;
import android.widget.ListView;

import app.com.comidapp.adapter.RestaurantAdapter;
import app.com.comidapp.listener.OnItemClickListenerListViewRestaurant;
import app.com.comidapp.service.LocalService;

public class ListRestaurantActivity extends AbstractActivity {

    private ListView lstRestaurante;
    private RestaurantAdapter adapter;

    @Override
    public void getDataOnServiceConnect(LocalService connectService) {
        if (connectService != null){

            adapter = new RestaurantAdapter(getApplicationContext(),connectService.getListaRestaurantes().getArrayList());
            lstRestaurante.setAdapter(adapter);
            lstRestaurante.setOnItemClickListener(new OnItemClickListenerListViewRestaurant(this));
        }
    }

    @Override
    public void hookOnCreate() {
        setContentView(R.layout.activity_list_restaurant);

        lstRestaurante = (ListView)findViewById(R.id.lstRestaurant);
    }

    @Override
    public void hookOnStart() {

    }

    @Override
    public void hookOnPause() {

    }

    @Override
    public void hookOnCreateOptionsMenu(Menu menu) {

    }
}
