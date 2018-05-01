package com.example.juice.nerdupv000;

import android.support.annotation.NonNull;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.UserProfileChangeRequest;

public class NewUser extends BaseActivity {
    private FirebaseAuth database;
    private FirebaseUser user;
    private Button newUserButton;
    private EditText newUser;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_user);
        newUserButton = findViewById(R.id.setUserButton);
        newUser = findViewById(R.id.newUser);
        database = FirebaseAuth.getInstance();
        user = database.getCurrentUser();
        setListeners();
    }

    @Override
    protected void onStart(){
        super.onStart();
    }

    public void setListeners(){
        newUserButton.setOnTouchListener(new View.OnTouchListener() {

            @Override
            public boolean onTouch(View v, MotionEvent event){
                if (event.getAction() == MotionEvent.ACTION_DOWN) {
                    if(newUser != null && newUser.getText().toString().length() < 16){
                        UserProfileChangeRequest profileUpdates = new UserProfileChangeRequest.Builder()
                                .setDisplayName(newUser.getText().toString())
                                .build();
                        user.updateProfile(profileUpdates)
                                .addOnCompleteListener(new OnCompleteListener<Void>() {
                                    @Override
                                    public void onComplete(@NonNull Task<Void> task) {
                                        if (task.isSuccessful()) {
                                            Toast.makeText(getApplicationContext(), "Updated profile",
                                                    Toast.LENGTH_SHORT).show();
                                            goToProfile();
                                        } else {
                                            Toast.makeText(getApplicationContext(), "Error updating profile.  Please try again.",
                                                    Toast.LENGTH_SHORT).show();
                                        }
                                    }
                                });
                    } else {
                        if(newUser == null){
                            Toast.makeText(getApplicationContext(), "Must insert username",
                                    Toast.LENGTH_SHORT).show();
                        } else {
                            Toast.makeText(getApplicationContext(), "Username must be less than 16 characters",
                                    Toast.LENGTH_SHORT).show();
                        }
                    }
                    return true;
                } else {
                    return false;
                }
            }
        });
    }
}
