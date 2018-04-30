package com.example.juice.nerdupv000;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.PopupMenu;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;

public class Profile extends BaseActivity {
    private ImageButton editNotes, uploadNotes, downloadNotes;
    FirebaseAuth database;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        Toolbar toolbar = findViewById(R.id.profileToolbar);
        setSupportActionBar(toolbar);
    }

    @Override
    protected void onStart(){
        super.onStart();
        editNotes = findViewById(R.id.editButton);
        uploadNotes = findViewById(R.id.uploadButton);
        downloadNotes = findViewById(R.id.downloadButton);
        getListeners();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.profile, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item){
        int id = item.getItemId();

        if(id == R.id.search){
            Log.i("actionBar", "search");
        } else if (id == R.id.edit){
            gotToEditProfile();
        } else if (id == R.id.logout){
            FirebaseAuth.getInstance().signOut();
            goToLogin();
            finish();
        }
        return super.onOptionsItemSelected(item);
    }
/*    public void showPopupSearch(View v) {
        PopupMenu popup = new PopupMenu(this, v);
        MenuInflater inflater = popup.getMenuInflater();
        inflater.inflate(R.menu.search, popup.getMenu());
        popup.show();
    }*/

    public void getListeners(){
        editNotes.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                if (event.getAction() == MotionEvent.ACTION_DOWN) {

                    return true;
                } else {
                    return false;
                }
            }
        });

        uploadNotes.setOnTouchListener(new View.OnTouchListener() {

            @Override
            public boolean onTouch(View v, MotionEvent event){
                if (event.getAction() == MotionEvent.ACTION_DOWN) {

                    return true;
                } else {
                    return false;
                }
            }
        });

        downloadNotes.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event){
                if(event.getAction() == MotionEvent.ACTION_DOWN){

                    return true;
                } else {
                    return false;
                }
            }
        });


    }
}
