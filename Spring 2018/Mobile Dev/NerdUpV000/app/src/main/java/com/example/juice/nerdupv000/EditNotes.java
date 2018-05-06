package com.example.juice.nerdupv000;

import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class EditNotes extends BaseActivity{
    public EditText notes;
    public String player;
    public DatabaseReference playerNotes;
    private ValueEventListener noteListener;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_notes);

        Toolbar toolbar = findViewById(R.id.editNotesToolbar);
        setSupportActionBar(toolbar);

        player = getIntent().getStringExtra("player");
        notes = findViewById(R.id.notes);

        getNotes();
    }

    @Override
    public void onResume(){
        super.onResume();
        getNotes();
    }

    @Override
    public void onStop(){
        super.onStop();

        if(noteListener != null){
            playerNotes.removeEventListener(noteListener);
        }
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
            uploadNotes();
            finish();
        } else if (id == R.id.cancel){
            finish();
        }
        return super.onOptionsItemSelected(item);
    }

    public void getNotes(){
        playerNotes = FirebaseDatabase.getInstance().getReference().child("userProfiles").child(player).child("notes");

        ValueEventListener dataListener = new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                String playerNotes = dataSnapshot.getValue(String.class);

                if(playerNotes != null)
                    notes.setText(playerNotes);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                Toast.makeText(getApplicationContext(), "Unable to retrieve notes.", Toast.LENGTH_SHORT).show();
            }
        };

        playerNotes.addListenerForSingleValueEvent(dataListener);

        noteListener = dataListener;
    }

    public void uploadNotes(){
        playerNotes = FirebaseDatabase.getInstance().getReference().child("userProfiles").child(player).child("notes");

        playerNotes.setValue(notes.getText().toString());

        Toast.makeText(this, "Updated Notes", Toast.LENGTH_SHORT).show();
    }
}
