package com.example.juice.nerdupv000;

import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.io.IOException;
import java.util.List;

public class CreateLogin extends BaseActivity {
    private Button create;
    private EditText createEmail, createPassword, checkPassword;
    private TextView loginLink;
    private FirebaseAuth auth;

    @Override
    public void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_login);
        auth = FirebaseAuth.getInstance();
    }

    @Override
    public void onStart(){
        super.onStart();

        create = findViewById(R.id.createButton);
        createEmail = findViewById(R.id.create_email);
        createPassword = findViewById(R.id.create_password);
        checkPassword = findViewById(R.id.check_password);
        loginLink = findViewById(R.id.loginLink);
        getListeners();
    }


    public void createUser(String username, String password){
        auth.createUserWithEmailAndPassword(username, password)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if(task.isSuccessful()) {
                            Toast.makeText(getApplicationContext(), "Account Created", Toast.LENGTH_SHORT).show();
                            finish();
                        } else {
                            Toast.makeText(getApplicationContext(), "Unable to create account please check email.", Toast.LENGTH_SHORT).show();
                        }
                    }
                });
        }

    public boolean validPassword(){
        if(createPassword.getText().toString().matches("^(?=.*[a-z])(?=.*[A-Z])(?=.*[0-9]).{6,}$")) {
            return true;
        } else{
            Toast.makeText(getApplicationContext(), "Password must be at least 6 character long, contains a capital letter, a lowercase letter and a number", Toast.LENGTH_LONG).show();
        }
        return false;
    }

    public boolean passwordsMatch(){
        if(createPassword.getText().toString().equals(checkPassword.getText().toString())){
            return true;
        } else {
            Toast.makeText(getApplicationContext(), "Passwords don't match", Toast.LENGTH_LONG).show();
        }
        return false;
    }

    public void getListeners(){
        create.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                if (event.getAction() == MotionEvent.ACTION_DOWN) {
                    if(validPassword() && passwordsMatch()){
                        try{
                            createUser(createEmail.getText().toString(), createPassword.getText().toString());
                        } catch(Exception e){
                            Toast.makeText(getApplicationContext(), "Error with database. Unable to create account.", Toast.LENGTH_SHORT).show();
                        }
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

