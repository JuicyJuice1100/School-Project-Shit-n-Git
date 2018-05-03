package com.example.juice.nerdupv000;

import com.google.firebase.database.IgnoreExtraProperties;

@IgnoreExtraProperties
public class UserProfile {
    public String bio, quickInfo, mains, secondaries, notes;


    public UserProfile(){

    }

    public UserProfile(String bio, String quickInfo, String mains,
                String secondaries, String notes){
        this.bio = bio;
        this.quickInfo = quickInfo;
        this.mains = mains;
        this.secondaries = secondaries;
        this.notes = notes;
    }
}
