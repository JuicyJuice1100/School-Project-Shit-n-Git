package com.example.juice.labapplication;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.provider.BaseColumns;
import android.support.v7.app.AppCompatActivity;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class CreateLogin extends AppCompatActivity {
    private Button create;
    private EditText createUsername, createPassword, checkPassword;
    private TextView loginLink;
    private LocalDatabase localDb;

    @Override
    public void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.create_account);
        localDb = new LocalDatabase(this);
    }

    @Override
    public void onStart(){
        super.onStart();

        create = findViewById(R.id.createButton);
        createUsername = findViewById(R.id.create_username);
        createPassword = findViewById(R.id.create_password);
        checkPassword = findViewById(R.id.check_password);
        loginLink = findViewById(R.id.loginLink);
        getListeners();
    }

    @Override
    protected void onDestroy() {
        localDb.close();
        super.onDestroy();
    }

    public boolean validUsername(){
        if(createUsername.getText().toString().length() >= 4 && getAllUsernames().size() == 0){
            return true;
        } else {
            if(createUsername.getText().toString().length() < 4){
                Toast.makeText(getApplicationContext(), "Username must be at least 4 characters long", Toast.LENGTH_LONG).show();
            } else {
                Toast.makeText(getApplicationContext(), "Username already exists", Toast.LENGTH_LONG).show();
            }

            return false;
        }
    }

    public boolean validPassword(){
        if(createPassword.getText().toString().matches("^(?=.*[a-z])(?=.*[A-Z])(?=.*[0-9]).{6,}$") && createPassword.getText().toString().equals(checkPassword.getText().toString())) {
            return true;
        } else{
            Toast.makeText(getApplicationContext(), "Password must be at least 6 character long, contains a capital letter, a lowercase letter and a number", Toast.LENGTH_LONG).show();
        }
        return false;
    }

    public List<String> getAllUsernames(){
        SQLiteDatabase db = localDb.getReadableDatabase();

        // Define a projection that specifies which columns from the database
        //  you will actually use after this query.
        String[] projection = {
                LocalDatabaseContract.User.COLUMN_USERNAME
        };

        String selection = LocalDatabaseContract.User.COLUMN_USERNAME + " = ?";
        String[] selectionArgs = { createUsername.getText().toString() };

        Cursor cursor = db.query(
                LocalDatabaseContract.User.TABLE_NAME,   // The table to query
                projection,             // The array of columns to return (pass null to get all)
                selection,              // The columns for the WHERE clause
                selectionArgs,          // The values for the WHERE clause
                null,                   // don't group the rows
                null,                   // don't filter by row groups
                null              // The sort order
        );

        List<String> data = new ArrayList<>();
        while(cursor.moveToNext()){
            data.add(cursor.getString(cursor.getColumnIndex(LocalDatabaseContract.User.COLUMN_USERNAME)));
        };

        cursor.close();

        return data;
    }

    public void createUsername(){
        SQLiteDatabase db = localDb.getWritableDatabase();

        // Create a new map of values, where column names are the keys
        ContentValues values = new ContentValues();
        values.put(LocalDatabaseContract.User.COLUMN_USERNAME, createUsername.getText().toString());
        values.put(LocalDatabaseContract.User.COLUMN_PASSWORD, createPassword.getText().toString());

        // Insert the new row, returning the primary key value of the new row
        long newRowId = db.insert(LocalDatabaseContract.User.TABLE_NAME, null, values);
    }
    public void getListeners(){
        create.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                if (event.getAction() == MotionEvent.ACTION_DOWN) {
                    if(validUsername() && validPassword()){
                        Toast.makeText(getApplicationContext(), "Account Created", Toast.LENGTH_SHORT).show();
                        createUsername();
                        finish();
                    }
                    return true;
                } else {
                    return false;
                }
            }
        });


        loginLink.setOnTouchListener(new View.OnTouchListener() {

            @Override
            public boolean onTouch(View v, MotionEvent event){
                if (event.getAction() == MotionEvent.ACTION_DOWN) {
                    finish();
                    return true;
                } else {
                    return false;
                }
            }
        });
    }
}
