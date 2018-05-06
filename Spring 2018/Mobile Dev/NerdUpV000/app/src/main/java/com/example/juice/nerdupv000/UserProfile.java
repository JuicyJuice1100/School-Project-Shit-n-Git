package com.example.juice.nerdupv000;

import com.google.firebase.database.Exclude;
import com.google.firebase.database.IgnoreExtraProperties;

import java.util.HashMap;
import java.util.Map;

@IgnoreExtraProperties
public class UserProfile {
    public String username, bio, quickInfo, mains, secondaries, notes;


    public UserProfile(){

    }

    public UserProfile(String username, String bio, String quickInfo, String mains,
                String secondaries, String notes){
        this.username = username;
        this.bio = bio;
        this.quickInfo = quickInfo;
        this.mains = mains;
        this.secondaries = secondaries;
        this.notes = notes;
    }

    @Exclude
    public Map<String, Object> toMap() {
        HashMap<String, Object> result = new HashMap<>();
        result.put("username", username);
        result.put("bio", bio);
        result.put("quickInfo", quickInfo);
        result.put("mains", mains);
        result.put("secondaries", secondaries);
        result.put("notes", notes);

        return result;
    }
}
