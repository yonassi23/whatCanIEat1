package com.coral.whatcanieat;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.backendless.Backendless;
import com.backendless.BackendlessUser;
import com.backendless.async.callback.AsyncCallback;
import com.backendless.exceptions.BackendlessFault;

public class login extends AppCompatActivity {
    EditText email;
    EditText pass;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        email = (EditText)findViewById(R.id.nameLog);
        pass = (EditText)findViewById(R.id.passLog);
        Backendless.initApp( this,
                Defaults.APPLICATION_ID,
                Defaults.API_KEY);
    }
    //
    public void signup(View v) {
        startActivity(new Intent(this,SignUp.class));
    }
    //
    public void CheckLog(View view) {

        String name = email.getText().toString();
        String password = pass.getText().toString();

        Backendless.UserService.login(name, password, new AsyncCallback<BackendlessUser>() {
            @Override
            public void handleResponse(BackendlessUser response) {
                Toast.makeText(getApplicationContext(),"log in",Toast.LENGTH_LONG).show();
            }
            @Override
            public void handleFault(BackendlessFault fault) {
                System.out.println(fault.getCode());
                Toast.makeText(getApplicationContext(),"failed "+fault.getCode(),Toast.LENGTH_LONG).show();
            }
        },true);

    }
}
