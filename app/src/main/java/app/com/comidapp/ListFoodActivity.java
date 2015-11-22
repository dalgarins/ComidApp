package app.com.comidapp;

import android.view.Menu;
import android.widget.ListView;

import app.com.comidapp.adapter.FoodAdapter;
import app.com.comidapp.service.LocalService;

public class ListFoodActivity extends AbstractActivity {

    private ListView lstComidas;
    private FoodAdapter foodAdapter;

    @Override
    public void getDataOnServiceConnect(LocalService connectService) {
        if (connectService != null){

            foodAdapter = new FoodAdapter(getApplicationContext(),connectService.getListaComidasByRestaurant().getComidas());
            lstComidas.setAdapter(foodAdapter);
        }
    }

    @Override
    public void hookOnCreate() {

        setContentView(R.layout.activity_list_food);
        lstComidas = (ListView) findViewById(R.id.lstComidas);
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
