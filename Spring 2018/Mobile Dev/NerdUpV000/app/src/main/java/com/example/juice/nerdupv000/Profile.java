package com.example.juice.nerdupv000;

import android.content.Intent;
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
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

public class Profile extends BaseActivity {
/*    private ImageButton editNotes, uploadNotes, downloadNotes;*/
    private ImageView profilePic;
    private TextView username, quickInfo, bio, mains, secondaries, notes;
    private FirebaseAuth auth;
    private DatabaseReference database;
    private ValueEventListener profileListener;
    private String name, email;
    private Uri photoUrl;
    private boolean isGoogleSignIn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        Toolbar toolbar = findViewById(R.id.profileToolbar);
        setSupportActionBar(toolbar);
        auth = FirebaseAuth.getInstance();

        username = findViewById(R.id.username);
        profilePic = findViewById(R.id.profilePic);
        quickInfo = findViewById(R.id.quickInfo);
        bio = findViewById(R.id.bio);
        mains = findViewById(R.id.mains);
        secondaries = findViewById(R.id.secondaries);
        notes = findViewById(R.id.notes);


        if(savedInstanceState == null)
            isGoogleSignIn = getIntent().getBooleanExtra("isGoogleSignIn", false);
        else
            isGoogleSignIn = savedInstanceState.getBoolean("isGoogleSignIn", false);


    }

    @Override
    protected void onStart(){
        super.onStart();

        getListeners();
        getData();
    }

    @Override
    protected void onSaveInstanceState(Bundle bundle){
        bundle.putBoolean("isGoogleSignIn", isGoogleSignIn);
        super.onSaveInstanceState(bundle);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        if(isGoogleSignIn)
            getMenuInflater().inflate(R.menu.googleprofile, menu);
        else
            getMenuInflater().inflate(R.menu.profile, menu);
/*        if(isGoogleSignIn)
            menu.removeItem(R.id.settings);*/
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item){
        int id = item.getItemId();

        if(id == R.id.search){
            goToSearchUserProfile();
        } else if (id == R.id.edit){
            if(isGoogleSignIn)
                goToEditProfileGoogle();
            else
                goToEditProfile();
        } else if (id == R.id.settings){
                goToSettings();
        } else if (id == R.id.logout){
            FirebaseAuth.getInstance().signOut();
            goToLogin();
            finish();
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void goToEditProfileGoogle(){
        Intent intent = new Intent(this, EditProfileGoogle.class);
        intent.putExtra("isGoogleSignIn", isGoogleSignIn);
        intent.putExtra("notes", notes.getText().toString());
        startActivity(intent);
    }

    @Override
    public void goToEditProfile(){
        Intent intent = new Intent(this, EditProfile.class);
        intent.putExtra("isGoogleSignIn", isGoogleSignIn);
        intent.putExtra("notes", notes.getText().toString());
        startActivity(intent);
    }

    @Override
    public void onResume(){
        super.onResume();
        getData();
    }

    @Override
    public void onStop(){
        super.onStop();

        if(profileListener != null){
            database.removeEventListener(profileListener);
        }
    }

    @Override
    public void onDestroy(){
        super.onDestroy();

        if(profileListener != null){
            database.removeEventListener(profileListener);
        }
    }

    public void getData() {
        if (isGoogleSignIn) {
            GoogleSignInAccount acct = GoogleSignIn.getLastSignedInAccount(this);
            if (acct != null) {
                if(acct.getDisplayName() != null)
                    name = acct.getDisplayName();
                if(acct.getEmail() != null)
                    email = acct.getEmail();
                if(acct.getPhotoUrl() != null)
                    photoUrl = acct.getPhotoUrl();
            }
        } else {
            FirebaseUser user = auth.getCurrentUser();
            if (user != null) {
                if(user.getDisplayName() != null)
                    name = user.getDisplayName();
                if(user.getEmail() != null)
                    email = user.getEmail();
                if(user.getPhotoUrl() != null)
                    photoUrl = user.getPhotoUrl();
            }
        }
        username.setText(name);

        if(photoUrl != null){
            Glide.with(getApplicationContext())
                    .load(photoUrl)
                    .apply(new RequestOptions()
                            .circleCrop())
                    .into(profilePic);
        }

        database = FirebaseDatabase.getInstance().getReference().child("userProfiles").child(name);

        ValueEventListener dataListener = new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                UserProfile userProfile = dataSnapshot.getValue(UserProfile.class);

                if(userProfile != null){
                    if(userProfile.bio != null)
                        bio.setText(userProfile.bio);
                    if(userProfile.quickInfo != null)
                        quickInfo.setText(userProfile.quickInfo);
                    if(userProfile.mains != null)
                        mains.setText(userProfile.mains);
                    if(userProfile.secondaries != null)
                        secondaries.setText(userProfile.secondaries);
                    if(userProfile.notes != null)
                        notes.setText(userProfile.notes);
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                Toast.makeText(Profile.this, "Unable to retrieve profile data.", Toast.LENGTH_SHORT).show();
            }
        };

        database.addListenerForSingleValueEvent(dataListener);

        profileListener = dataListener;
    }

    public void getListeners(){
/*        editNotes.setOnTouchListener(new View.OnTouchListener() {
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
        });*/

/*        downloadNotes.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event){
                if(event.getAction() == MotionEvent.ACTION_DOWN){

                    return true;
                } else {
                    return false;
                }
            }
        });*/


    }
}
