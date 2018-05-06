package com.example.juice.nerdupv000;

import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;

public class Settings extends BaseActivity {
    final private int EMAIL = 0;
    final private int PASSWORD = 1;
    final private int DELETE = 2;

    private Button editEmail, editPassword, deleteAccount;
    private boolean isEmailOpen, isPasswordOpen, isDeleteOpen;
    private FragmentManager fragmentManager;
    private Fragment emailFragment, passwordFragment, deleteFragment;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);

        Toolbar toolbar = findViewById(R.id.settingsToolbar);
        setSupportActionBar(toolbar);

        editEmail = findViewById(R.id.editEmail);
        editPassword = findViewById(R.id.editPassword);
        deleteAccount = findViewById(R.id.deleteAccount);
        isEmailOpen = false;
        isPasswordOpen = false;
        isDeleteOpen = false;
        emailFragment = new ChangeEmail();
        passwordFragment = new ChangePassword();
        deleteFragment = new DeleteAccount();
        fragmentManager = getFragmentManager();

        getListeners();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.settings, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item){
        int id = item.getItemId();

        if(id == R.id.back){
            if(fragmentManager.getBackStackEntryCount() == 0)
                finish();
            else
                fragmentManager.popBackStack();
        } else if (id == R.id.logout){
            FirebaseAuth.getInstance().signOut();
            Intent intent = new Intent(this, Login.class);
            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_CLEAR_TOP);
            startActivity(intent);
            finish();
        }
        return super.onOptionsItemSelected(item);
    }

    public void fragmentController(int fragment){
        FragmentTransaction fragmentTransaction;
        switch(fragment){
            case EMAIL:
                if(!isEmailOpen){
                    fragmentTransaction = fragmentManager.beginTransaction();
                    fragmentTransaction.replace(R.id.buttons, emailFragment, "email");
                    fragmentTransaction.addToBackStack("email");
                    fragmentTransaction.commit();
                    isEmailOpen = true;
                } else {
                    fragmentManager.popBackStack();
                    isEmailOpen = false;
                }
                break;
            case PASSWORD:
                if(!isPasswordOpen){
                    fragmentTransaction = fragmentManager.beginTransaction();
                    fragmentTransaction.replace(R.id.buttons, passwordFragment, "password");
                    fragmentTransaction.addToBackStack("password");
                    fragmentTransaction.commit();
                    isPasswordOpen = true;
                } else {
                    fragmentManager.popBackStack();
                    isPasswordOpen = false;
                }
                break;
            case DELETE:
                if(!isDeleteOpen){
                    fragmentTransaction = fragmentManager.beginTransaction();
                    fragmentTransaction.replace(R.id.buttons, deleteFragment, "delete");
                    fragmentTransaction.addToBackStack("delete");
                    fragmentTransaction.commit();
                    isDeleteOpen = true;
                } else {
                    fragmentManager.popBackStack();
                    isDeleteOpen = false;
                }
                break;
        }
    }

    public void getListeners(){
        editEmail.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                if (event.getAction() == MotionEvent.ACTION_DOWN) {
                    fragmentController(EMAIL);
                    return true;
                } else {
                    return false;
                }
            }
        });

        editPassword.setOnTouchListener(new View.OnTouchListener() {

            @Override
            public boolean onTouch(View v, MotionEvent event){
                if (event.getAction() == MotionEvent.ACTION_DOWN) {
                    fragmentController(PASSWORD);
                    return true;
                } else {
                    return false;
                }
            }
        });

        deleteAccount.setOnTouchListener(new View.OnTouchListener() {

            @Override
            public boolean onTouch(View v, MotionEvent event){
                if (event.getAction() == MotionEvent.ACTION_DOWN) {
                    fragmentController(DELETE);
                    return true;
                } else {
                    return false;
                }
            }
        });
    }
}
