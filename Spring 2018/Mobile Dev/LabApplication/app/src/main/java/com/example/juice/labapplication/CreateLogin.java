package com.example.juice.labapplication;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class CreateLogin extends AppCompatActivity {
    private Button create;
    private EditText createUsername, createPassword;
    private TextView loginLink;

    @Override
    public void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.create_account);
    }

    @Override
    public void onStart(){
        super.onStart();

        create = findViewById(R.id.createButton);
        createUsername = findViewById(R.id.create_username);
        createPassword = findViewById(R.id.create_password);
        loginLink = findViewById(R.id.loginLink);
        getListeners();
    }

    public boolean validUsername(){
        if(createUsername.getText().toString().length() >= 4){
            return true;
        } else {
            Toast.makeText(getApplicationContext(), "Username must be at least 4 characters long", Toast.LENGTH_LONG).show();
            return false;
        }
    }

    public boolean validPassword(){
        if(createPassword.getText().toString().matches("^(?=.*[a-z])(?=.*[A-Z])(?=.*[0-9]).{6,}$")) {
            return true;
        } else{
            Toast.makeText(getApplicationContext(), "Password must be at least 6 character long, contains a capital letter, a lowercase letter and a number", Toast.LENGTH_LONG).show();
        }
        return false;
    }

    public void getListeners(){
        create.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                if (event.getAction() == MotionEvent.ACTION_DOWN) {
                    if(validUsername() && validPassword()){
                        Toast.makeText(getApplicationContext(), "Account Created", Toast.LENGTH_SHORT).show();
                        finish();
                    }
                    return true;
                } else {
                    return false;
                }
            }
        });


        loginLink.setOnTouchListener(new View.OnTouchListener() {

            @Override
            public boolean onTouch(View v, MotionEvent event){
                if (event.getAction() == MotionEvent.ACTION_DOWN) {
                    finish();
                    return true;
                } else {
                    return false;
                }
            }
        });
    }
}
