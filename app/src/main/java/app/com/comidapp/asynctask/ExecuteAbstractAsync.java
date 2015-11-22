package app.com.comidapp.asynctask;

import android.content.Intent;
import android.os.AsyncTask;

import app.com.comidapp.R;
import app.com.comidapp.AbstractActivity;

/**
 * Permite encadenar conexiones http asincronas
 * Created by dalgarin on 26/06/2015.
 */
public abstract class ExecuteAbstractAsync<T> extends AsyncTask<String, Float, T> {

    private ExecuteAbstractAsync nextTask;
    private Intent intent;
    protected AbstractActivity activity;


    public ExecuteAbstractAsync(AbstractActivity activity){

        this.activity = activity;
        this.intent = null;
    }

    public ExecuteAbstractAsync(AbstractActivity mainActivity, ExecuteAbstractAsync nextTask){

        this.activity = mainActivity;
        this.nextTask = nextTask;
    }

    public ExecuteAbstractAsync(AbstractActivity mainActivity, Intent intent){

        this.activity = mainActivity;
        this.intent = intent;
    }

    public AbstractActivity getActivity() {
        return activity;
    }

    public ExecuteAbstractAsync getNextTask() {
        return nextTask;
    }

    public void setNextTask(ExecuteAbstractAsync nextTask) {
        this.nextTask = nextTask;
    }

    public Intent getIntent() {
        return intent;
    }

    public void setIntent(Intent intent) {
        this.intent = intent;
    }

    protected void executeNextStep(String... params){
        if (nextTask != null){
            nextTask.execute(params);
        }
        if(intent != null){
            if (activity != null){
                activity.startActivity(intent);
            }
        }
    }

    @Override
    protected void onPostExecute(T t) {
        if (t != null){
            hookOnPostExecute(t);
        }else{
            ((AbstractActivity)getActivity()).dismissProgressDialog();
            ((AbstractActivity)getActivity()).showMessage(getActivity().getString(R.string.errorTitleMsg), getActivity().getString(R.string.errorMsgConexion));
        }
    }

    public abstract void hookOnPostExecute(T t);
}
