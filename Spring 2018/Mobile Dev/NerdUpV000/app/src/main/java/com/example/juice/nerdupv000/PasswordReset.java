package com.example.juice.nerdupv000;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;

public class PasswordReset extends BaseActivity {
    private FirebaseAuth auth;
    private Button sendEmail;
    private EditText email;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_password_reset);

        auth = FirebaseAuth.getInstance();
        sendEmail = findViewById(R.id.sendEmail);
        email = findViewById(R.id.email);
        getListeners();
    }

    public void getListeners(){
        sendEmail.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                if (event.getAction() == MotionEvent.ACTION_DOWN) {
                    if(email.getText().toString().contains("@")){
                        auth.sendPasswordResetEmail(email.getText().toString())
                                .addOnCompleteListener(new OnCompleteListener<Void>() {
                                    @Override
                                    public void onComplete(@NonNull Task<Void> task) {
                                        if(task.isSuccessful()){
                                            Toast.makeText(PasswordReset.this, "Email Sent", Toast.LENGTH_SHORT).show();
                                            finish();
                                        } else {
                                            Toast.makeText(PasswordReset.this, "Unable to Send Email.  Please make sure email is valid.", Toast.LENGTH_SHORT).show();
                                        }
                                    }
                                });
                    } else {
                        Toast.makeText(PasswordReset.this, "Please Enter an valid email.", Toast.LENGTH_SHORT).show();
                    }
                    return true;
                } else {
                    return false;
                }
            }
        });
    }
}
