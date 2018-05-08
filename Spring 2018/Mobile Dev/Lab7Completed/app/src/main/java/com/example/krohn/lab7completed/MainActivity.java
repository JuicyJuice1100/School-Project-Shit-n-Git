//MOSTLY FUNCTIONAL, GO THROUGH AND CLEAN IT UP.
package com.example.krohn.lab7completed;

import android.content.Intent;
import android.content.res.Configuration;
import android.content.res.TypedArray;
import android.graphics.Color;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Random;

import layout.MathFragment;
import layout.TableFragment;

public class MainActivity extends AppCompatActivity implements TableFragment.OnTableFragmentInteractionListener, MathFragment.OnBiddingFragmentInteractionListener{

    //private variables to hold information across events
    private ImageView chosenCard;
    private int tableSpot = 0;
    private int[] tableCard;
    private MathFragment mathFragment = null;
    private TableFragment tableFragment = null;
    private boolean tableVisible;
    private String username;
    private boolean landscape;
    private int chosenCardId;
    private String oldChatStart, oldChat;
    private int first = -1, second = -1, operator = -1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if(getResources().getConfiguration().orientation==Configuration.ORIENTATION_LANDSCAPE) {
            setContentView(R.layout.activity_landscape);
            landscape = true;
        } else{
            setContentView(R.layout.activity_portrait);
            landscape = false;
        }

        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_HIDDEN);

        int NUM_CARDS = 3;
        if(savedInstanceState==null) {
            //get cards and views into an array
            TypedArray cards = getResources().obtainTypedArray(R.array.my_cards);
            TypedArray loc = getResources().obtainTypedArray(R.array.my_locations);
            //A way to determine which cards have been chosen already.
            ArrayList<Integer> available = new ArrayList<>();
            for (int i = 0; i < 52; i++) {
                available.add(i);
            }

            //randomly pick from the cards to put into the locations
            Random r = new Random();
            for (int num = 0; num < NUM_CARDS; num++) {
                int rn = r.nextInt(available.size());  //which random number am I taking
                int spot = available.get(rn);  //get the actual random number
                available.remove(rn); //remove that random number from the list of available ones
                ((ImageView) findViewById(loc.getResourceId(num, 0))).setImageResource(cards.getResourceId(spot, 0));
                findViewById(loc.getResourceId(num, 0)).setTag(spot);
            }

            chosenCard = null;
            chosenCardId = -1;

            //add the table fragment to the screen
            tableFragment = new TableFragment();
            tableFragment.setArguments(getIntent().getExtras());
            FragmentTransaction f = getSupportFragmentManager().beginTransaction();
            f.add(R.id.fragment_table, tableFragment);
            f.commit();

            //set table boolean to true because we see the table initially
            tableVisible = true;

            //get the username from the login activity
            Intent intent = getIntent();
            username = intent.getStringExtra(LoginActivity.MY_USERNAME);

            //in the scoring region, set username to whatever was entered
            ((TextView) findViewById(R.id.text_score1)).setText(username);

            //some clean up, recycle typed array variables
            cards.recycle();
            loc.recycle();
        } else{
            //retrieve values from saved instance because we've been here before
            username = savedInstanceState.getString("username");
            tableSpot = savedInstanceState.getInt("tableSpot");
            tableVisible = savedInstanceState.getBoolean("tableVisible");
            int numCardsInHand = savedInstanceState.getInt("numCardsInHand");
            //restore the cards, table/math, scoreboard and chat
            if(!tableVisible) {
                mathFragment = (MathFragment) getSupportFragmentManager().findFragmentById(R.id.fragment_table);
                first = savedInstanceState.getInt("first");
                second = savedInstanceState.getInt("second");
                operator = savedInstanceState.getInt("operator");
            } else{
                //not in math part, put table back in.
                //can't add cards to table yet because it's a fragment.
                //any cards are added to the table in the onStart method.
                tableFragment = (TableFragment) getSupportFragmentManager().findFragmentById(R.id.fragment_table);
            }

            tableCard = new int[tableSpot];
            for(int num=0; num<tableSpot; num++){
                tableCard[num] = savedInstanceState.getInt("tableCard"+num);
            }

            //put all of the cards back in the hand
            TypedArray loc = getResources().obtainTypedArray(R.array.my_locations);
            TypedArray cards = getResources().obtainTypedArray(R.array.my_cards);
            for(int num=0; num<numCardsInHand; num++){
                int spot = savedInstanceState.getInt("card"+num);
                ((ImageView) findViewById(loc.getResourceId(num, 0))).setImageResource(cards.getResourceId(spot, 0));
                findViewById(loc.getResourceId(num, 0)).setTag(spot);
                //if the card was selected before rotation, set background to red, set as current chosen card
                if(savedInstanceState.getInt("chosenCardId")==spot){
                    findViewById(loc.getResourceId(num, 0)).setBackgroundColor(Color.RED);
                    chosenCard = (ImageView)findViewById(loc.getResourceId(num, 0));
                    chosenCardId = spot;
                }
            }

            //since there are locations for 3 cards, remove the ones not being used.
            //would be better to add image views to my linear layout instead of this hack.
            //this is functional, but not very scalable. you should probably fix it if you are using this.
            for(int num = NUM_CARDS; num>numCardsInHand; num--) {
                ImageView v = (ImageView)(findViewById(loc.getResourceId(num-1, 0)));
                ((LinearLayout) v.getParent()).removeView(v);
            }

            //add scoreboard information
            ((TextView) findViewById(R.id.text_score1)).setText(savedInstanceState.getString("score1"));
            ((TextView) findViewById(R.id.text_score2)).setText(savedInstanceState.getString("score2"));

            loc.recycle();
            cards.recycle();

            //reset all of chat information
            if(landscape){
                //in landscape mode so put it on the screen
                ((EditText)findViewById(R.id.editText_chat)).setText(savedInstanceState.getString("chat_start"));
                ((TextView)findViewById(R.id.text_chat)).setText(savedInstanceState.getString("chat"));
            } else{
                //in portrait mode so save to instance variables for future sending
                oldChatStart = savedInstanceState.getString("chat_start");
                oldChat = savedInstanceState.getString("chat");
            }
        }
    }

    public int getFirst(){
        return first;
    }

    public int getSecond(){
        return second;
    }

    public int getOperator(){
        return operator;
    }

    @Override
    public void onStart(){
        super.onStart();
    }

    //a useless method, could simply call the getFirst method in the MathFragment
    public boolean equationExists(){
        return first!=-1;
    }

    public void setEquation(int first, int second, int operator){
        this.first = first;
        this.second = second;
        this.operator = operator;
    }

    public void addChat(View v){
        //add the text to the correct location and clear out the edittext
        String value = ((EditText)findViewById(R.id.editText_chat)).getText().toString();
        ((TextView)findViewById(R.id.text_chat)).setText(username+": " + value+ "\n" +((TextView)findViewById(R.id.text_chat)).getText());
        ((EditText)findViewById(R.id.editText_chat)).setText("");
        //check if the user typed in math and the table is visible
        if(value.equals("math") && tableVisible){
            //go into math mode
            mathFragment = new MathFragment();
            mathFragment.setArguments(getIntent().getExtras());
            FragmentTransaction f = getSupportFragmentManager().beginTransaction();
            f.replace(R.id.fragment_table, mathFragment);
            f.commit();

            //used to ensure the background doesn't change on the cards
            tableVisible = false;
        }

        if(value.equals("table") && !tableVisible){
            //go into table mode
            tableFragment = new TableFragment();
            tableFragment.setArguments(getIntent().getExtras());
            FragmentTransaction f = getSupportFragmentManager().beginTransaction();
            f.replace(R.id.fragment_table, tableFragment);
            f.commit();

            //used to ensure the background doesn't change on the cards
            tableVisible = true;
        }
    }

    //a method called from the TableFragment to add cards to the table.
    public void addCards(){
        //add cards back to table
        TypedArray table = getResources().obtainTypedArray(R.array.my_table);
        TypedArray cards = getResources().obtainTypedArray(R.array.my_cards);
        for(int num=0; num<tableSpot; num++){
            int spot = tableCard[num];
            ((ImageView) findViewById(table.getResourceId(num, 0))).setImageResource(cards.getResourceId(spot, 0));
            findViewById(table.getResourceId(num, 0)).setTag(spot);
        }
        table.recycle();
        cards.recycle();
    }

    public void changeBackground(View v){
        //check if we are in math mode first, if we are, don't select a card
        if(tableVisible) {
            if (chosenCard == null) {
                //if no card were chosen yet
                v.setBackgroundColor(Color.RED);
                chosenCard = (ImageView) v;
                chosenCardId = (Integer)(chosenCard.getTag());
            } else {
                if (v == chosenCard) {
                    //chose a selected card
                    v.setBackgroundColor(0);
                    chosenCard = null;
                    chosenCardId = -1;

                    //copy card from hand to table
                    TypedArray table = getResources().obtainTypedArray(R.array.my_table);
                    ((ImageView) findViewById(table.getResourceId(tableSpot, 0))).setImageDrawable(((ImageView) v).getDrawable());
                    //get tag for future copying
                    findViewById(table.getResourceId(tableSpot, 0)).setTag(v.getTag());

                    //remove card from hand
                    ((LinearLayout) v.getParent()).removeView(v);

                    //go to next spot in table for next card to be placed
                    tableSpot++;

                    table.recycle();
                } else {
                    //chose a new card
                    chosenCard.setBackgroundColor(0);
                    v.setBackgroundColor(Color.RED);
                    chosenCard = (ImageView) v;
                }
            }
        } //else not playing so ignore any clicks on the cards
    }

    public void checkMath(View v){
        //see if the answer entered is actually the solution, if yes, add it to the scoreboard
        int answer = mathFragment.getSolution();
        if(!((TextView)findViewById(R.id.edit_math)).getText().toString().equals("") && Integer.parseInt(((TextView)findViewById(R.id.edit_math)).getText().toString())==answer){
            String original = ((TextView)(findViewById(R.id.text_score1))).getText().toString();
            ((TextView)(findViewById(R.id.text_score1))).setText(original + "\n" + mathFragment.getInfo());
            mathFragment.addMath();
        }
        //clear out the text either way
        ((TextView)findViewById(R.id.edit_math)).setText("");
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);

        //save the username, cards, table, scoreboard and chat
        outState.putString("username", username);
        outState.putBoolean("tableVisible", tableVisible);
        outState.putInt("tableSpot", tableSpot);

        //cards in hand
        LinearLayout cards = (LinearLayout)findViewById(R.id.layout_cards);
        for(int i=0; i<cards.getChildCount(); i++){
            ImageView iv = (ImageView)cards.getChildAt(i);
            outState.putInt("card"+i, (Integer)(iv.getTag()));
        }
        outState.putInt("numCardsInHand", cards.getChildCount());

        //cards on table, if showing
        if(tableVisible) {
            TypedArray table = getResources().obtainTypedArray(R.array.my_table);
            for (int i = 0; i < tableSpot; i++) {
                outState.putInt("tableCard" + i, (Integer) (findViewById(table.getResourceId(i, 0))).getTag());
            }
            table.recycle();
        } else{
            for(int i=0; i<tableSpot; i++){
                outState.putInt("tableCard" + i, tableCard[i]);
            }
        }

        //selected card, if any
        outState.putInt("chosenCardId", chosenCardId);

        //scoreboard
        outState.putString("score1", ((TextView)findViewById(R.id.text_score1)).getText().toString());
        outState.putString("score2", ((TextView)findViewById(R.id.text_score2)).getText().toString());

        //chat
        if(!landscape){
            //in portrait mode so get the saved chat status
            outState.putString("chat_start", oldChatStart);
            outState.putString("chat", oldChat);
        } else{
            //in landscape mode so get it off the screen
            outState.putString("chat_start", ((EditText) findViewById(R.id.editText_chat)).getText().toString());
            outState.putString("chat", ((TextView) findViewById(R.id.text_chat)).getText().toString());
        }

        //math equation
        outState.putInt("first", first);
        outState.putInt("second", second);
        outState.putInt("operator", operator);
    }

    //a debug method to copy toast code from to see if I get to a certain point in code, used in future to test
    //public void doStuff(View v){
    //    Toast.makeText(getApplicationContext(), "My toast test!", Toast.LENGTH_SHORT).show();
    //}
}