package app.com.comidapp;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.ComponentName;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;

import app.com.comidapp.service.LocalService;


/**
 * Esqueleto basico para la creacion de las activities,
 * que incluye el manejo del servicio, se deben sobreescribir los hooks
 * para continuar con el funcionamiento normal de las activities
 * Created by NoaD on 30/08/2015.
 */
public abstract class AbstractActivity extends AppCompatActivity {

    private LocalService connectService;//Servicio para compartir datos dentro de la aplicacion
    private ProgressDialog progressDialog;

    /**
     * Maneja el evento de regresar entre actividades
     */
    @Override
    public void onBackPressed() {

    }
    
    /**
     * Maneja la conexion hacia el servicio, se debe sobreescribir el metodo hookOnStart
     * si se desea continuar con el funcionamiento normal
     */
    @Override
    protected void onStart() {
        super.onStart();
        doBindService();
        hookOnStart();
    }

    /**
     * Maneja el Banner de publicidad de android, se debe sobreescribir el metodo hookOnCreate
     * si se desea continuar con el funcionamiento normal
     * @param savedInstanceState
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        hookOnCreate();

        /*AdView mAdView = (AdView) findViewById(R.id.adView);
        AdRequest adRequest = new AdRequest.Builder().build();
        mAdView.loadAd(adRequest);*/
    }

    /**
     * se debe sobreescribir el metodo hookOnCreateOptionsMenu
     * si se desea continuar con el funcionamiento normal, para el manejo del menu.
     * @param menu
     * @return
     */
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        //getMenuInflater().inflate(R.menu.menu_prueba, menu);
        hookOnCreateOptionsMenu(menu);
        return true;
    }

    /**
     * Maneja los eventos click sobre el menu de opciones, para este caso el logout de la aplicaion
     * @param item
     * @return
     */
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch (item.getItemId()){
            /*case R.id.action_logout:
                Intent i = new Intent(getApplicationContext(), MainActivity.class);
                i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);//para poder ejecutar una activity desde un contexto
                startActivity(i);
                return true;*/
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    /**
     * se debe sobreescribir el metodo hookOnPause
     * si se desea continuar con el funcionamiento normal
     */
    @Override
    protected void onPause() {
        super.onPause();
        hookOnPause();
    }

    /**
     * Servicio por medio del cual se compartiran los datos
     */
    private ServiceConnection mConnection = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
            connectService = ((LocalService.ConnectBinder) service).getService();
            getDataOnServiceConnect(connectService);
        }

        @Override
        public void onServiceDisconnected(ComponentName name) {
            connectService = null;
        }
    };

    /**
     * Conexion hacia el servicio
     */
    void doBindService() {
        bindService(new Intent(this, LocalService.class), mConnection, Context.BIND_AUTO_CREATE);
    }

    /**
     * Desconexion del servicio, el servicio se destruye cuando todas las activities estas desconectadas
     */
    void doUnbindService(){
        unbindService(mConnection);
    }

    /**
     * Se ejecuta despues de realizar la conexion al servicio
     * @param connectService ConnectService con el servicio instanciado
     */
    public abstract void getDataOnServiceConnect(LocalService connectService);

    /**
     * Permiten continuar el ciclo normal de vida de las activities, sobreescribir cada uno de estos metodos
     * en lugar de los originales de las activities, para no preocuparse por el manejo de conexiones y las funciones
     */
    public abstract void hookOnCreate();
    public abstract void hookOnStart();
    public abstract void hookOnPause();
    public abstract void hookOnCreateOptionsMenu(Menu menu);

    /**
     * Obtiene un objeto del servicio
     * @return ConnectService con los datos
     */
    public LocalService getConnectService() {
        return connectService;
    }

    /**
     * Muestra un mensaje dentro de la actividad
     * @param title String con el titulo del mensaje
     * @param message String con el mensaje a mostrar
     */
    public void showMessage(String title, String message) {

        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setCancelable(true);
        builder.setTitle(title);
        builder.setMessage(message);
        builder.setNeutralButton(android.R.string.ok,
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        dialog.cancel();
                    }
                });
        builder.show();
    }

    public void launchProgressDialog(AbstractActivity activity,String title, String message){

        progressDialog = new ProgressDialog(activity);
        progressDialog.setTitle(title);
        progressDialog.setMessage(message);
        progressDialog.setCancelable(false);
        progressDialog.show();
    }

    public void dismissProgressDialog(){

        if (progressDialog != null){
            progressDialog.dismiss();
        }
    }

    public ProgressDialog getProgressDialog() {
        return progressDialog;
    }
}
