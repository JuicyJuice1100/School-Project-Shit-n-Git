package com.example.juice.nerdupv000;

import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
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

import java.io.ByteArrayOutputStream;
import java.util.HashMap;
import java.util.Map;

public class EditProfileGoogle extends BaseActivity {

    private ImageView profilePic;
    private TextView username, quickInfo, bio, mains, secondaries;
    private FirebaseAuth auth;
    private DatabaseReference databaseReference, userProfileReference;
    private FirebaseStorage storage;
    private StorageReference storageReference;
    private ValueEventListener profileListener;
    private String name, email, notes;
    private Uri photoUrl;
    private boolean isGoogleSignIn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_profile_google);

        Toolbar toolbar = findViewById(R.id.profileToolbar);
        setSupportActionBar(toolbar);

        isGoogleSignIn = getIntent().getBooleanExtra("isGoogleSignIn", false);
        auth = FirebaseAuth.getInstance();
        notes = getIntent().getStringExtra("notes");

        username = findViewById(R.id.username);
        profilePic = findViewById(R.id.profilePic);
        quickInfo = findViewById(R.id.quickInfo);
        bio = findViewById(R.id.bio);
        mains = findViewById(R.id.mains);
        secondaries = findViewById(R.id.secondaries);
        databaseReference = FirebaseDatabase.getInstance().getReference();
        storage = FirebaseStorage.getInstance();
        storageReference = storage.getReference();

        getListeners();
        getData();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.editprofile, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item){
        int id = item.getItemId();

        if(id == R.id.confirm){
            uploadPicture();
            updateProfile();
            finish();
        } else if (id == R.id.cancel){
            finish();
        }
        return super.onOptionsItemSelected(item);
    }

    public void uploadPicture(){
        StorageReference profilePicRef = storageReference.child("profilePictures/"+name);
        ByteArrayOutputStream output = new ByteArrayOutputStream();
        Bitmap bitmap = ((BitmapDrawable) profilePic.getDrawable()).getBitmap();
        bitmap.compress(Bitmap.CompressFormat.JPEG, 100, output);
        profilePicRef.putBytes(output.toByteArray());
    }

    public void updateProfile(){
        userProfileReference = FirebaseDatabase.getInstance().getReference().child("userProfiles");
        UserProfile userProfile = new UserProfile(username.getText().toString(), bio.getText().toString(), quickInfo.getText().toString(),
                mains.getText().toString(), secondaries.getText().toString(), notes);
        Map<String, Object> profileValues = userProfile.toMap();

        Map<String, Object> childUpdates = new HashMap<>();
        childUpdates.put("/" + name+ "/", profileValues);

        userProfileReference.updateChildren(childUpdates);

        Toast.makeText(EditProfileGoogle.this, "Updated Profile", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onStop(){
        super.onStop();

        if(profileListener != null){
            databaseReference.removeEventListener(profileListener);
        }
    }

    public void getData(){
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
        }else {
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
            Glide.with(this)
                    .load(photoUrl)
                    .apply(new RequestOptions()
                            .circleCrop())
                    .into(profilePic);
        }

        databaseReference = FirebaseDatabase.getInstance().getReference().child("userProfiles").child(name);

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
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                Toast.makeText(EditProfileGoogle.this, "Unable to retrieve profile data.", Toast.LENGTH_SHORT).show();
            }
        };

        databaseReference.addListenerForSingleValueEvent(dataListener);

        profileListener = dataListener;
    }

    public void getListeners(){

    }

}
