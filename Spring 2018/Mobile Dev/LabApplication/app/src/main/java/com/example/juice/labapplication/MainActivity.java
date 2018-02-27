package com.example.juice.labapplication;

import android.content.res.Configuration;
import android.content.res.TypedArray;
import android.graphics.Color;
import android.graphics.Rect;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
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

        playerCardLeft.setOnTouchListener(new View.OnTouchListener() {
            private Rect rect;

            public boolean onTouch(View v, MotionEvent event) {
                if(event.getAction() == MotionEvent.ACTION_DOWN){
                    playerCardLeft.setColorFilter(Color.argb(80, 0, 0, 0));
                    rect = new Rect(v.getLeft(), v.getTop(), v.getRight(), v.getBottom());
                    return true;
                }else if(event.getAction() == MotionEvent.ACTION_UP){
                    playerCardLeft.setColorFilter(Color.argb(0, 0, 0, 0));
                    return true;
                } else{
                    return false;
                }
            }
        });

        playerCardRight.setOnTouchListener(new View.OnTouchListener() {
            private Rect rect;

            @Override
            public boolean onTouch(View v, MotionEvent event) {
                if(event.getAction() == MotionEvent.ACTION_DOWN){
                    playerCardRight.setColorFilter(Color.argb(80, 0, 0, 0));
                    rect = new Rect(v.getLeft(), v.getTop(), v.getRight(), v.getBottom());
                    return true;
                }else if(event.getAction() == MotionEvent.ACTION_UP){
                    playerCardRight.setColorFilter(Color.argb(0, 0, 0, 0));
                    return true;
                } else {
                    return false;
                }
            }
        });

        playerCardCenter.setOnTouchListener(new View.OnTouchListener() {
            private Rect rect;

            @Override
            public boolean onTouch(View v, MotionEvent event) {
                if(event.getAction() == MotionEvent.ACTION_DOWN){
                    playerCardCenter.setColorFilter(Color.argb(80, 0, 0, 0));
                    rect = new Rect(v.getLeft(), v.getTop(), v.getRight(), v.getBottom());
                    return true;
                }else if(event.getAction() == MotionEvent.ACTION_UP){
                    playerCardCenter.setColorFilter(Color.argb(0, 0, 0, 0));
                    return true;
                } else{
                    return false;
                }
            }
        });
    }

/*    public int getDrawableId(Context context, String name){
        return context.getResources().getIdentifier(name, "drawable", context.getPackageName());
    }*/


}
