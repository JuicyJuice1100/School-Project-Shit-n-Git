package com.example.juice.nerdupv000;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;

public class BaseActivity extends AppCompatActivity {

    public void goToCreateLogin(){
        Intent intent = new Intent(this, CreateLogin.class);
        startActivity(intent);
    }

    public void goToLogin(){
        Intent intent = new Intent(this, Login.class);
        startActivity(intent);
    }

    public void goToProfile(){
        Intent intent = new Intent(this, Profile.class);
        startActivity(intent);
    }
}
