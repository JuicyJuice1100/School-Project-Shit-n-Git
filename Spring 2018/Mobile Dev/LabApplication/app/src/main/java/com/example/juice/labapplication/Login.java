package com.example.juice.labapplication;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;



public class Login extends AppCompatActivity {
    private Button login;
    private EditText loginUsername, loginPassword;
    private TextView createLink;
    private LocalDatabase localDb;
    private Intent intent;

    @Override
    public void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login_page);
        localDb = new LocalDatabase(this);
        intent = new Intent(this, MainActivity.class);
    }

    @Override
    public void onStart(){
        super.onStart();

        login = findViewById(R.id.loginButton);
        loginUsername = findViewById(R.id.login_username);
        loginPassword = findViewById(R.id.login_password);
        createLink = findViewById(R.id.createLink);
        getListeners();
    }

    @Override
    protected void onDestroy() {
        localDb.close();
        super.onDestroy();
    }

    public void createLogin(){
        Intent intent = new Intent(this, CreateLogin.class);
        startActivity(intent);
    }

    public List<String> selectUser(){
        SQLiteDatabase db = localDb.getReadableDatabase();

        // Define a projection that specifies which columns from the database
        //  you will actually use after this query.
        String[] projection = {
                LocalDatabaseContract.User.COLUMN_USERNAME,
                LocalDatabaseContract.User.COLUMN_PASSWORD
        };

        String selection = LocalDatabaseContract.User.COLUMN_USERNAME + " = ?";
        String[] selectionArgs = { loginUsername.getText().toString() };

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
            data.add(cursor.getString(1));
        };

        cursor.close();

        return data;
    }

    public void getListeners(){
        login.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                if (event.getAction() == MotionEvent.ACTION_DOWN) {
                    if(selectUser().size() == 0){
                        Toast.makeText(getApplicationContext(), "Username does not exist", Toast.LENGTH_SHORT).show();
                    }
                    else if(selectUser().get(0).equals(loginPassword.getText().toString())){
/*                        getIntent().putExtra("username", loginUsername.getText().toString());*/
                        /*finish();*/

                        startActivity(intent);
                    } else {
                        Toast.makeText(getApplicationContext(), "Incorrect Password", Toast.LENGTH_SHORT).show();
                    }
                    return true;
                } else {
                    return false;
                }
            }
        });

        createLink.setOnTouchListener(new View.OnTouchListener() {

            @Override
            public boolean onTouch(View v, MotionEvent event){
                if (event.getAction() == MotionEvent.ACTION_DOWN) {
                    createLogin();
                    return true;
                } else {
                    return false;
                }
            }
        });
    }


}
