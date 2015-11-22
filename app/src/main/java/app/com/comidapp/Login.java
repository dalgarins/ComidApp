package app.com.comidapp;

import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import app.com.comidapp.entities.AbstractActivity;
import app.com.comidapp.service.LocalService;

public class Login extends AbstractActivity {

    private EditText txtUser;
    private EditText txtPassword;
    private Button btnLogin;
    private Button btnClean;

    private void initialize(){
        txtUser = (EditText) findViewById(R.id.txtUser);
        txtPassword = (EditText) findViewById(R.id.txtPasswd);
        btnLogin = (Button) findViewById(R.id.btnOk);
        btnClean = (Button) findViewById(R.id.btnClean);
        btnClean.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                clean();
            }
        });
    }

    public void clean() {
        txtPassword.setText("");
        txtUser.setText("");
    }

    public boolean isEmpty() {
        if (txtUser.getText().toString().trim().equals("") || (txtPassword.getText().toString().trim().equals(""))) {
            return true;
        }
        return false;
    }

    @Override
    public void getDataOnServiceConnect(LocalService connectService) {

    }

    @Override
    public void hookOnCreate() {
        setContentView(R.layout.activity_login);
        initialize();
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
