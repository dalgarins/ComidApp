package app.com.comidapp.asynctask;

import com.request.core.conexion.ConexionGenerica;
import com.request.core.schema.Opcion;

import java.util.LinkedList;

import app.com.comidapp.Login;
import app.com.comidapp.R;
import app.com.comidapp.entities.AbstractActivity;
import app.com.comidapp.entities.Cliente;

/**
 * Created by NoaD on 22/11/2015.
 */
public class AuthUserAsync extends ExecuteAbstractAsync<Cliente> {

    public AuthUserAsync(AbstractActivity mainActivity, ExecuteAbstractAsync nextTask) {
        super(mainActivity, nextTask);
    }

    @Override
    public void hookOnPostExecute(Cliente cliente) {

        if (cliente.isStatus()){
            getActivity().getConnectService().setCliente(cliente);
            executeNextStep();
        }else{
            ((AbstractActivity)getActivity()).dismissProgressDialog();
            ((AbstractActivity)getActivity()).showMessage(getActivity().getString(R.string.errorTitleMsg), getActivity().getString(R.string.errorAuthMessage));
        }
    }

    @Override
    protected Cliente doInBackground(String... params) {
        if (getActivity() instanceof Login){
            Login login = (Login) getActivity();
            if (login.getConnectService() != null && params.length > 1){
                LinkedList<Opcion> datosValidar = new LinkedList<Opcion>();
                datosValidar.add(new Opcion(getActivity().getString(R.string.param_user), params[0]));
                datosValidar.add(new Opcion(getActivity().getString(R.string.param_passwd), params[1]));

                ConexionGenerica<Cliente> cv = new ConexionGenerica<Cliente>(login.getConnectService().getData(),
                        login.getConnectService().getData().getRequestList().get(getActivity().getString(R.string.url_login)),
                        login.getConnectService().getHttpClient(), datosValidar){};

                if (cv.getResponseCode() == 200){
                    return cv.getResponseOfRequest();
                }
            }
        }
        return null;
    }
}
