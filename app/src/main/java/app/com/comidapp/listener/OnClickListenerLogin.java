package app.com.comidapp.listener;

import android.content.Intent;
import android.os.AsyncTask;
import android.view.View;

import app.com.comidapp.ListRestaurantActivity;
import app.com.comidapp.Login;
import app.com.comidapp.R;
import app.com.comidapp.asynctask.AuthUserAsync;
import app.com.comidapp.asynctask.GetRestaurantAsync;

/**
 * Created by NoaD on 22/11/2015.
 */
public class OnClickListenerLogin implements View.OnClickListener {

    private Login login;

    public OnClickListenerLogin(Login login) {
        this.login = login;
    }

    @Override
    public void onClick(View v) {
        if (!login.isEmpty()){

            Intent i = new Intent(login.getApplicationContext(), ListRestaurantActivity.class);
            i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);//para poder ejecutar una activity desde un contexto
            new AsyncTaskManager(login, i).execute();
        } else {
            login.showMessage(login.getString(R.string.info), login.getString(R.string.login_empty_detail));
        }
    }

    private class AsyncTaskManager extends AsyncTask<Void, Float, Void> {

        public GetRestaurantAsync getRestaurant;
        public AuthUserAsync authUserAsync;

        public AsyncTaskManager(Login login, Intent intent) {

            getRestaurant = new GetRestaurantAsync(login, intent);
            authUserAsync = new AuthUserAsync(login, getRestaurant);
        }

        @Override
        protected void onPreExecute() {

            super.onPreExecute();
            login.launchProgressDialog(login, login.getString(R.string.login), login.getString(R.string.login_msg));
            authUserAsync.execute(login.getUser(), login.getPasswd());
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);
        }

        @Override
        protected void onProgressUpdate(Float... values) {
            super.onProgressUpdate(values);
        }

        @Override
        protected Void doInBackground(Void... params) {


            return null;
        }
    }
}
