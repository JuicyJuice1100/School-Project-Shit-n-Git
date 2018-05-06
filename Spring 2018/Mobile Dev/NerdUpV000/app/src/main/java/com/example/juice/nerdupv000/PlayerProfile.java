package com.example.juice.nerdupv000;


import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;


public class PlayerProfile extends android.app.Fragment {
    private ImageView profilePic;
    private ImageButton editButton;
    private TextView username, quickInfo, bio, mains, secondaries, notes;
    private FirebaseAuth auth;
    private DatabaseReference database;
    private ValueEventListener profileListener;
    private String player;

    public PlayerProfile() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        player = getArguments().getString("player");
        return inflater.inflate(R.layout.fragment_player_profile, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState){
        super.onViewCreated(view, savedInstanceState);

        username = view.findViewById(R.id.username);
        profilePic = view.findViewById(R.id.profilePic);
        quickInfo = view.findViewById(R.id.quickInfo);
        bio = view.findViewById(R.id.bio);
        mains = view.findViewById(R.id.mains);
        secondaries = view.findViewById(R.id.secondaries);
        notes = view.findViewById(R.id.notes);
        editButton = view.findViewById(R.id.editButton);


        getData();
        getListeners();
    }

    @Override
    public void onStop(){
        super.onStop();

        if(profileListener != null){
            database.removeEventListener(profileListener);
        }
    }

    @Override
    public void onResume(){
        super.onResume();
        getData();
    }

    public void getData() {
        username.setText(player);

        FirebaseStorage storage = FirebaseStorage.getInstance();
        StorageReference storageReference = storage.getReference().child("profilePictures").child(player);
        storageReference.getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
            @Override
            public void onSuccess(Uri uri) {
                Glide.with(getActivity().getApplicationContext())
                        .load(uri)
                        .apply(new RequestOptions()
                                .circleCrop())
                        .into(profilePic);
            }
        });


        database = FirebaseDatabase.getInstance().getReference().child("userProfiles").child(player);

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
                Toast.makeText(getActivity().getApplicationContext(), "Unable to retrieve profile data.", Toast.LENGTH_SHORT).show();
            }
        };

        database.addListenerForSingleValueEvent(dataListener);

        profileListener = dataListener;
    }

    public void goToEditNotes(){
        Intent intent = new Intent(getActivity().getApplicationContext(), EditNotes.class);
        intent.putExtra("player", player);
        startActivity(intent);
    }

    public void getListeners(){
        editButton.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                if (event.getAction() == MotionEvent.ACTION_DOWN) {
                    goToEditNotes();
                    return true;
                } else {
                    return false;
                }
            }
        });
    }
}
