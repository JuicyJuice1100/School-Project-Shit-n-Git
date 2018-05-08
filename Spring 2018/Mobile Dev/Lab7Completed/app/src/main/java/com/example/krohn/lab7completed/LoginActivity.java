package com.example.krohn.lab7completed;

import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

public class LoginActivity extends AppCompatActivity {

    public final static String MY_USERNAME = "com.example.krohn.lab7completed.UNAME";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
    }

    @Override
    protected void onResume(){
        super.onResume();
        //on rotation, checks whether or not we are registering or logging in
        CheckBox checkBox = (CheckBox)findViewById(R.id.checkBox_register);
        if(checkBox.isChecked()){
            ((Button)findViewById(R.id.button_login)).setText(getString(R.string.registering));
        } else{
            ((Button)findViewById(R.id.button_login)).setText(getString(R.string.login));
        }
    }

    public void registerOrLogin(View v){
        /* The following code is useful for debugging. It allows the tester to simply hit
            the login button without having to type in any credentials. The username is set
            to Erik by default and the game goes on. This should not appear in the final lab.
         */
        String u = ((EditText)findViewById(R.id.edit_username)).getText().toString();
        String p = ((EditText)findViewById(R.id.edit_password)).getText().toString();
        if(p.equals("") && u.equals("")){
            Intent intent = new Intent(this, MainActivity.class);
            intent.putExtra(MY_USERNAME, "Erik");
            startActivity(intent);  //non-blocking start, the finish below will happen immediately
            finish();
            return;
        }
        /* Above is the login hack for debugging purposes. */

        CheckBox checkBox = (CheckBox)findViewById(R.id.checkBox_register);
        //a boolean to determine if the user registered or logged in correctly
        boolean good = false;
        if(checkBox.isChecked()){
            //the user is registering, check specifications
            ((Button)findViewById(R.id.button_login)).setText(getString(R.string.registering));
            String uname = ((EditText)findViewById(R.id.edit_username)).getText().toString();
            String password = ((EditText)findViewById(R.id.edit_password)).getText().toString();
            if(uname.length()<4){
                Toast.makeText(getApplicationContext(), "Username is too short.", Toast.LENGTH_SHORT).show();
            }else if(password.length()<6){
                Toast.makeText(getApplicationContext(), "Password is too short.", Toast.LENGTH_SHORT).show();
            }else if(!password.matches(".*\\d+.*")){
                Toast.makeText(getApplicationContext(), "Password must contain a number.", Toast.LENGTH_SHORT).show();
            }else if(!password.matches(".*[a-z]+.*")){
                Toast.makeText(getApplicationContext(), "Password must contain a lowercase letter.", Toast.LENGTH_SHORT).show();
            }else if(!password.matches(".*[A-Z]+.*")){
                Toast.makeText(getApplicationContext(), "Password must contain an uppercase letter.", Toast.LENGTH_SHORT).show();
            }else{
                //check to see if username is already taken
                MyDBContract.MyDbHelper mdbh = new MyDBContract.MyDbHelper(getApplicationContext());
                SQLiteDatabase rdb = mdbh.getReadableDatabase();
                //create a String array for the values to be stored into it
                String[] projection = {
                        MyDBContract.DBEntry.COLUMN_NAME_USER_ID,
                        MyDBContract.DBEntry.COLUMN_NAME_PASSWORD
                };

                //order of which rows are sorted
                String sortOrder = MyDBContract.DBEntry.COLUMN_NAME_USER_ID + " DESC";

                //how are we querying, search for usernames == to uname
                String selection = MyDBContract.DBEntry.COLUMN_NAME_USER_ID + " LIKE ?";
                String[] selectionArgs = { uname };

                //setup the actual query
                Cursor c = rdb.query(
                        MyDBContract.DBEntry.TABLE_NAME,
                        projection,
                        selection,
                        selectionArgs,
                        null,
                        null,
                        sortOrder
                );

                //if count > 0, then there is a user already registered with that name, try again
                if(c.getCount()>0){
                    Toast.makeText(getApplicationContext(), "Username already taken.", Toast.LENGTH_SHORT).show();
                } else{
                    //all good, register them and log in
                    //insert username/password into db
                    SQLiteDatabase db = mdbh.getWritableDatabase();
                    ContentValues values = new ContentValues();
                    values.put(MyDBContract.DBEntry.COLUMN_NAME_USER_ID, uname);
                    //horrible solution, should save a hash at worst
                    values.put(MyDBContract.DBEntry.COLUMN_NAME_PASSWORD, password);
                    db.insert(MyDBContract.DBEntry.TABLE_NAME,
                            null,
                            values);
                    good = true;
                }
                c.close();
            }
        } else{
            //the user is logging in, make sure it's valid
            String uname = ((EditText)findViewById(R.id.edit_username)).getText().toString();
            String password = ((EditText)findViewById(R.id.edit_password)).getText().toString();

            //check to see if username/password combo is correct
            MyDBContract.MyDbHelper mdbh = new MyDBContract.MyDbHelper(getApplicationContext());
            SQLiteDatabase rdb = mdbh.getReadableDatabase();
            //create a String array for the values to be stored into it
            String[] projection = {
                    MyDBContract.DBEntry.COLUMN_NAME_USER_ID,
                    MyDBContract.DBEntry.COLUMN_NAME_PASSWORD
            };

            //order of which rows are sorted
            String sortOrder = MyDBContract.DBEntry.COLUMN_NAME_USER_ID + " DESC";

            //how are we querying, search for usernames == to uname
            String selection = MyDBContract.DBEntry.COLUMN_NAME_USER_ID + " LIKE ?";
            String[] selectionArgs = { uname };

            //setup the actual query
            Cursor c = rdb.query(
                    MyDBContract.DBEntry.TABLE_NAME,
                    projection,
                    selection,
                    selectionArgs,
                    null,
                    null,
                    sortOrder
            );

            //there is no user with that username
            if(c.getCount()==0){
                Toast.makeText(getApplicationContext(), "Username doesn't exist, are you trying to register?", Toast.LENGTH_SHORT).show();
            } else{
                //check password here
                c.moveToFirst();
                String storedPassword = c.getString(c.getColumnIndex(MyDBContract.DBEntry.COLUMN_NAME_PASSWORD));
                if(storedPassword.equals(password)){
                    //log them in
                    good = true;
                } else{
                    Toast.makeText(getApplicationContext(), "Invalid password, try again.", Toast.LENGTH_SHORT).show();
                }
            }
            c.close();
        }

        //logged in or registered fine, switch activities and send in the username
        if(good){
            Intent intent = new Intent(this, MainActivity.class);
            String uname = ((EditText)findViewById(R.id.edit_username)).getText().toString();
            intent.putExtra(MY_USERNAME, uname);
            startActivity(intent);  //non-blocking start, the finish below will happen immediately
            finish();
        }
    }

    public void registering(View v){
        //method called when the login/register checkbox is checked
        CheckBox checkBox = (CheckBox)v;
        if(checkBox.isChecked()){
            ((Button)findViewById(R.id.button_login)).setText(getString(R.string.registering));
        } else{
            ((Button)findViewById(R.id.button_login)).setText(getString(R.string.login));
        }
    }
}