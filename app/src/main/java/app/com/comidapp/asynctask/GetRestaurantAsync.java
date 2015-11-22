package app.com.comidapp.asynctask;

import android.content.Intent;

import com.request.core.conexion.ConexionGenerica;

import app.com.comidapp.Login;
import app.com.comidapp.R;
import app.com.comidapp.entities.AbstractActivity;
import app.com.comidapp.entities.ListaRestaurantes;

/**
 * Created by NoaD on 22/11/2015.
 */
public class GetRestaurantAsync extends ExecuteAbstractAsync<ListaRestaurantes> {

    public GetRestaurantAsync(AbstractActivity mainActivity, Intent intent) {
        super(mainActivity, intent);
    }

    @Override
    public void hookOnPostExecute(ListaRestaurantes listaRestaurantes) {
        getActivity().getConnectService().setListaRestaurantes(listaRestaurantes);
        executeNextStep();
    }

    @Override
    protected ListaRestaurantes doInBackground(String... params) {
        if (getActivity() instanceof Login){
            Login login = (Login) getActivity();
            if (login.getConnectService() != null ){

                ConexionGenerica<ListaRestaurantes> cv = new ConexionGenerica<ListaRestaurantes>(login.getConnectService().getData(),
                        login.getConnectService().getData().getRequestList().get(getActivity().getString(R.string.url_login)),
                        login.getConnectService().getHttpClient(), null){};

                if (cv.getResponseCode() == 200){
                    return cv.getResponseOfRequest();
                }
            }
        }
        return null;
    }
}
