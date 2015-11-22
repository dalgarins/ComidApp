package app.com.comidapp.asynctask;

import android.content.Intent;

import com.request.core.conexion.ConexionGenerica;
import com.request.core.schema.Opcion;

import java.util.LinkedList;

import app.com.comidapp.AbstractActivity;
import app.com.comidapp.ListRestaurantActivity;
import app.com.comidapp.R;
import app.com.comidapp.entities.ListaComidas;

/**
 * Created by NoaD on 22/11/2015.
 */
public class GetFoodAsync extends ExecuteAbstractAsync<ListaComidas> {

    public GetFoodAsync(AbstractActivity mainActivity, Intent intent) {
        super(mainActivity, intent);
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
        getActivity().launchProgressDialog(getActivity(), getActivity().getString(R.string.title_restaurante_list), getActivity().getString(R.string.msg_restaurante_list));
    }

    @Override
    public void hookOnPostExecute(ListaComidas listaComidas) {
        getActivity().getConnectService().setListaComidasByRestaurant(listaComidas);
        executeNextStep();
    }

    @Override
    protected ListaComidas doInBackground(String... params) {
        if (getActivity() instanceof ListRestaurantActivity){

            ListRestaurantActivity lstRestActivity = (ListRestaurantActivity) getActivity();

            if (lstRestActivity.getConnectService() != null && params.length > 0){
                LinkedList<Opcion> datosValidar = new LinkedList<Opcion>();
                datosValidar.add(new Opcion(getActivity().getString(R.string.param_restaurant_id), params[0]));

                ConexionGenerica<ListaComidas> cv = new ConexionGenerica<ListaComidas>(lstRestActivity.getConnectService().getData(),
                        lstRestActivity.getConnectService().getData().getRequestList().get(getActivity().getString(R.string.url_comidas)),
                        lstRestActivity.getConnectService().getHttpClient(), datosValidar){};

                if (cv.getResponseCode() == 200){
                    return cv.getResponseOfRequest();
                }

            }
        }
        return null;
    }
}
