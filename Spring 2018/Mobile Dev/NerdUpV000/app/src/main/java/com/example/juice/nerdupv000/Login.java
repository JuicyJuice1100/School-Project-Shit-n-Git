package com.example.juice.nerdupv000;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class Login extends BaseActivity {
    private Button login;
    private EditText loginUsername, loginPassword;
    private TextView createLink;

    @Override
    public void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
    }

    @Override
    public void onStart(){
        super.onStart();

        login = findViewById(R.id.loginButton);
        loginUsername = findViewById(R.id.login_username);
        loginPassword = findViewById(R.id.login_password);
        createLink = findViewById(R.id.createLink);
        getListeners();
    }

    public void getListeners(){
        login.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                if (event.getAction() == MotionEvent.ACTION_DOWN) {
                    //will have to change this to go to database
                    if(loginUsername.getText().toString().equals("JuicyJuice1100") && loginPassword.getText().toString().equals("password")){
                        getIntent().putExtra("username", loginUsername.getText().toString());
                        goToProfile();
                    } else {
                        Toast.makeText(getApplicationContext(), "Incorrect Username or Password", Toast.LENGTH_SHORT).show();
                    }
                    return true;
                } else {
                    return false;
                }
            }
        });

        createLink.setOnTouchListener(new View.OnTouchListener() {

            @Override
            public boolean onTouch(View v, MotionEvent event){
                if (event.getAction() == MotionEvent.ACTION_DOWN) {
                    goToCreateLogin();
                    return true;
                } else {
                    return false;
                }
            }
        });
    }


}

