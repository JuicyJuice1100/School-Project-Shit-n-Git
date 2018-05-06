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

    public void goToEditProfile(){
        Intent intent = new Intent(this, EditProfile.class);
        startActivity(intent);
    }

    public void goToNewUser(){
        Intent intent = new Intent(this, NewUser.class);
        startActivity(intent);
    }

    public void goToEditProfileGoogle(){
        Intent intent = new Intent(this, EditProfileGoogle.class);
        startActivity(intent);
    }

    public void goToSettings(){
        Intent intent = new Intent(this, Settings.class);
        startActivity(intent);
    }

    public void goToResetPassword(){
        Intent intent = new Intent(this, PasswordReset.class);
        startActivity(intent);
    }

    public void goToSearchUserProfile(){
        Intent intent = new Intent(this, SearchUserProfile.class);
        startActivity(intent);
    }

    static String encodeUserEmail(String userEmail) {
        return userEmail.replace(".", ",");
    }

    static String decodeUserEmail(String userEmail) {
        return userEmail.replace(",", ".");
    }
}
