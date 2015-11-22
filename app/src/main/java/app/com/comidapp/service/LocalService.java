package app.com.comidapp.service;

import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;

import com.request.core.schema.DataRequest;
import com.squareup.okhttp.OkHttpClient;

import java.io.Serializable;
import java.net.CookieManager;
import java.net.CookiePolicy;

import app.com.comidapp.R;
import app.com.comidapp.entities.Cliente;
import app.com.comidapp.entities.ListaRestaurantes;


/**
 * Created by dalgarin on 16/11/2015.
 */
public class LocalService extends Service implements Serializable {

    private DataRequest data;
    private OkHttpClient httpClient;
    private Cliente cliente;
    private ListaRestaurantes listaRestaurantes;


    private final IBinder service = new ConnectBinder();

    public LocalService() {
    }

    private void initialize() {

        if (data == null && httpClient == null) {
            data = new DataRequest();
            data = data.readXmlDataFromResource(getResources().
                    openRawResource(R.raw.datapeticion));//lee el archivo de configuracion la carpeta raw
            httpClient = new OkHttpClient();
            CookieManager cookieManager = new CookieManager();
            cookieManager.setCookiePolicy(CookiePolicy.ACCEPT_ALL);
            httpClient.setCookieHandler(cookieManager);
        }
    }

    public class ConnectBinder extends Binder {
        public LocalService getService() {
            return LocalService.this;
        }
    }

    @Override
    public IBinder onBind(Intent intent) {

        return service;
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        return START_STICKY;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        initialize();
    }

    public DataRequest getData() {
        return data;
    }

    public void setData(DataRequest data) {
        this.data = data;
    }

    public OkHttpClient getHttpClient() {
        return httpClient;
    }

    public void setHttpClient(OkHttpClient httpClient) {
        this.httpClient = httpClient;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    public ListaRestaurantes getListaRestaurantes() {
        return listaRestaurantes;
    }

    public void setListaRestaurantes(ListaRestaurantes listaRestaurantes) {
        this.listaRestaurantes = listaRestaurantes;
    }
}
