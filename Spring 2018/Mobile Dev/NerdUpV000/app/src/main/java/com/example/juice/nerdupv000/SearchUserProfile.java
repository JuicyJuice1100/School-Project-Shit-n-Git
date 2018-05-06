package com.example.juice.nerdupv000;

import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class SearchUserProfile extends BaseActivity {
    private AutoCompleteTextView searchProfile;
    private ArrayAdapter<String> adapter;
    private DatabaseReference databaseReference;
    private FragmentManager fragmentManager;
    private Fragment playerProfile;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_user_profile);

        Toolbar toolbar = findViewById(R.id.searchToolbar);
        setSupportActionBar(toolbar);


        searchProfile = findViewById(R.id.autoCompleteSearch);
        databaseReference = FirebaseDatabase.getInstance().getReference();

        playerProfile = new PlayerProfile();

        fragmentManager = getFragmentManager();

        setAutoComplete();
        getListeners();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.settings, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.back) {
            if (fragmentManager.getBackStackEntryCount() == 0)
                finish();
            else
                fragmentManager.popBackStack();
        } else if (id == R.id.logout) {
            FirebaseAuth.getInstance().signOut();
            Intent intent = new Intent(this, Login.class);
            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_CLEAR_TOP);
            startActivity(intent);
            finish();
        }
        return super.onOptionsItemSelected(item);
    }

    public void setAutoComplete(){
        adapter = new ArrayAdapter<>(this, R.layout.search_profile);
        databaseReference.child("userProfiles").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                adapter.clear();
                for(DataSnapshot suggestionSnapshot : dataSnapshot.getChildren()){
                    String suggestion = suggestionSnapshot.child("username").getValue(String.class);
                    Log.d("autocomplete", suggestion);
                    adapter.add(suggestion);
                }
            }


            @Override
            public void onCancelled(DatabaseError databaseError) {
                Log.d("autocomplete", "Unable to get data for autocomplete");
            }
        });
        searchProfile.clearListSelection();
        searchProfile.setDropDownAnchor(R.id.autoCompleteSearch);
        searchProfile.setAdapter(adapter);
        searchProfile.setThreshold(3);

    }
    public void getListeners(){
        searchProfile.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Bundle bundle = new Bundle();
                bundle.putString("player", searchProfile.getText().toString());
                playerProfile.setArguments(bundle);
                FragmentTransaction fragmentTransaction =  fragmentManager.beginTransaction();
                fragmentTransaction.replace(R.id.searchProfile, playerProfile, "playerProfile");
                fragmentTransaction.addToBackStack("playerProfile");
                fragmentTransaction.commit();

                //Tried to hide keyboard after selecting a player
                searchProfile.clearFocus();
            }
        });
    }
}
