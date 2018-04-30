package com.example.juice.nerdupv000;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.List;

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

    public void gotToEditProfile(){
        Intent intent = new Intent(this, EditProfile.class);
        startActivity(intent);
    }
}
