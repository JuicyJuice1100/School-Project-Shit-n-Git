package com.example.juice.labapplication;

import android.content.res.Configuration;
import android.content.res.TypedArray;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.WindowManager;
import android.content.Context;
import android.widget.ImageView;

import java.util.Random;
import java.util.Stack;

public class MainActivity extends AppCompatActivity {

    private ImageView playerCardLeft;
    private ImageView playerCardCenter;
    private ImageView playerCardRight;
    private ImageView playerCardPlayed;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if(getResources().getConfiguration().orientation ==
                Configuration.ORIENTATION_LANDSCAPE){
            setContentView(R.layout.activity_landscape);
        } else{
            setContentView(R.layout.activity_portrait);
        }
        getWindow().setSoftInputMode(
                WindowManager.LayoutParams.SOFT_INPUT_STATE_HIDDEN);
        playerCardLeft = findViewById(R.id.playerCardLeft);
        playerCardCenter = findViewById(R.id.playerCardCenter);
        playerCardRight = findViewById(R.id.playerCardRight);
        playerCardPlayed = findViewById(R.id.playerCardPlayed);
        randomCards();
    }

    public void randomCards(){
        Stack<Integer> randomStack = new Stack<>();
        Random random = new Random();
        TypedArray cards = getResources().obtainTypedArray((R.array.cards));
        int randomCard;

        for(int i = 0; i < 3; i++){
            do {
                randomCard = random.nextInt(52);
            } while (randomStack.contains(randomCard));
            randomStack.push(randomCard);
        }
        playerCardLeft.setImageResource(cards.getResourceId(randomStack.pop(), -1));
        playerCardCenter.setImageResource(cards.getResourceId(randomStack.pop(), -1));
        playerCardRight.setImageResource(cards.getResourceId(randomStack.pop(), -1));
    }

/*    public int[] cardArray(){
        int[] cardArray = new int[52];
        for(int i = 0; i < 52; i++){
            if(i < 13){
                cardArray[i] = getDrawableId(getApplicationContext(), "R.drawable.c" + i);
            } else if (i < 26){
                cardArray[i] = getDrawableId(getApplicationContext(), "R.drawable.d" + i);
            } else if (i < 39){
                cardArray[i] = getDrawableId(getApplicationContext(), "R.drawable.h" + i);
            } else {
                cardArray[i] = getDrawableId(getApplicationContext(), "R.drawable.s" + i);
            }
        }
        return cardArray;
    }*/

    public int getDrawableId(Context context, String name){
        return context.getResources().getIdentifier(name, "drawable", context.getPackageName());
    }
}
