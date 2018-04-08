package com.example.juice.labapplication;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;


public class Login extends AppCompatActivity {
    private Button login;
    private EditText loginUsername, loginPassword;
    private TextView createLink;

    @Override
    public void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login_page);
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

    public void createLogin(){
        Intent intent = new Intent(this, CreateLogin.class);
        startActivity(intent);
    }

    public void getListeners(){
        login.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                if (event.getAction() == MotionEvent.ACTION_DOWN) {
                    if(loginUsername.getText().toString().equals("Erik") && loginPassword.getText().toString().equals("Krohn1")){
                        getIntent().putExtra("username", loginUsername.getText().toString());
                        finish();
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
                    createLogin();
                    return true;
                } else {
                    return false;
                }
            }
        });
    }


}
