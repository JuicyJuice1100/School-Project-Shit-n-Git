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
}
