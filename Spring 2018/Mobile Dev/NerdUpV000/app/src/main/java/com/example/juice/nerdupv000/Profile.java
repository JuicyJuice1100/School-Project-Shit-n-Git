package com.example.juice.nerdupv000;

import android.net.Uri;
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
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class Profile extends BaseActivity {
    private ImageButton editNotes, uploadNotes, downloadNotes;
    private ImageView profilePic;
    private TextView username;
    private FirebaseAuth database;
    private String name, email;
    private Uri photoUrl;
    private boolean isGoogleSignIn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        Toolbar toolbar = findViewById(R.id.profileToolbar);
        setSupportActionBar(toolbar);
        database = FirebaseAuth.getInstance();
        isGoogleSignIn = getIntent().getBooleanExtra("isGoogleSignIn", false);
    }

    @Override
    protected void onStart(){
        super.onStart();
        editNotes = findViewById(R.id.editButton);
        uploadNotes = findViewById(R.id.uploadButton);
        downloadNotes = findViewById(R.id.downloadButton);
        username = findViewById(R.id.username);
        profilePic = findViewById(R.id.profilePic);
        getListeners();
        setData();
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
            goToEditProfile();
        } else if (id == R.id.logout){
            FirebaseAuth.getInstance().signOut();
            goToLogin();
            finish();
        }
        return super.onOptionsItemSelected(item);
    }


    public void setData() {
        if (isGoogleSignIn) {
            GoogleSignInAccount acct = GoogleSignIn.getLastSignedInAccount(this);
            if (acct != null) {
                name = acct.getDisplayName();
                email = acct.getEmail();
                photoUrl = acct.getPhotoUrl();
            }
        }else {
            FirebaseUser user = database.getCurrentUser();
            if (user != null) {
                name = user.getDisplayName();
                email = user.getEmail();
                photoUrl = user.getPhotoUrl();
            }
        }
        username.setText(name);
        Glide.with(this)
                .load(photoUrl)
                .apply(new RequestOptions()
                    .circleCrop())
                .into(profilePic);
    }


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
