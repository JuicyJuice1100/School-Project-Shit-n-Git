package com.example.juice.labapplication;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.res.TypedArray;
import android.graphics.Color;
import android.graphics.Rect;
import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.Random;
import java.util.Stack;


public class MainActivity extends AppCompatActivity {
    private ImageView playerCardLeft;
    private ImageView playerCardCenter;
    private ImageView playerCardRight;
    private ImageView playerCardPlayed;
    private ImageView opponentCardPlayed;
    private boolean isPlayerCardLeftFiltered;
    private boolean isPlayerCardCenterFiltered;
    private boolean isPlayerCardRightFiltered;
    private boolean isMathWar = false;
    private TextView chat;
    private EditText enterText;
    private ImageButton sendButton;
    private FragmentManager fragmentManager;
    private Intent intent;
    private LocalDatabase localDb;
    private int playerCardLeftId, playerCardCenterId, playerCardRightId;
    private TypedArray cards;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_landscape);
        opponentCardPlayed = findViewById(R.id.opponentCardPlayed);
        isPlayerCardLeftFiltered = false;
        isPlayerCardRightFiltered = false;
        isPlayerCardCenterFiltered = false;
        chat = findViewById(R.id.chat);
        enterText = findViewById(R.id.enterText);
        sendButton = findViewById(R.id.chatSendButton);
        fragmentManager = getSupportFragmentManager();
        localDb = new LocalDatabase(this);
        playerCardLeft = findViewById(R.id.playerCardLeft);
        playerCardCenter = findViewById(R.id.playerCardCenter);
        playerCardRight = findViewById(R.id.playerCardRight);
        playerCardPlayed = findViewById(R.id.playerCardPlayed);
        cards = getResources().obtainTypedArray((R.array.cards));
        getListeners();
        if(savedInstanceState == null) {
            randomCards();
        } else {
            playerCardLeftId = savedInstanceState.getInt("playerCardLeftId");
            playerCardCenterId = savedInstanceState.getInt("playerCardCenterId");
            playerCardRightId = savedInstanceState.getInt("playerCardRightId");
            playerCardLeft.setImageResource(cards.getResourceId(playerCardLeftId, -1));
            playerCardCenter.setImageResource(cards.getResourceId(playerCardCenterId, -1));
            playerCardRight.setImageResource(cards.getResourceId(playerCardRightId, -1));
        }
    }

    @Override
    protected void onStart() {
        super.onStart();
/*        intent = new Intent(this, Login.class);
        startActivity(intent);*/
/*        getWindow().setSoftInputMode(
                WindowManager.LayoutParams.SOFT_INPUT_STATE_HIDDEN);*/
    }

    @Override
    protected void onDestroy() {
        localDb.close();
        super.onDestroy();
    }

    @Override
    protected void onSaveInstanceState(Bundle bundle){
        super.onSaveInstanceState(bundle);
        bundle.putInt("playerCardLeftId", playerCardLeftId);
        bundle.putInt("playerCardCenterId", playerCardCenterId);
        bundle.putInt("playerCardRightId", playerCardRightId);
    }

    public void randomCards() {
        Stack<Integer> randomStack = new Stack<>();
        Random random = new Random();
        int randomCard;

        for (int i = 0; i < 3; i++) {
            do {
                randomCard = random.nextInt(52);
            } while (randomStack.contains(randomCard));
            randomStack.push(randomCard);
        }

        playerCardLeftId = randomStack.peek();
        int x = cards.getResourceId(playerCardLeftId, -1);
        Log.i("important", x + "");
        playerCardLeft.setImageResource(cards.getResourceId(randomStack.pop(), -1));
        playerCardCenterId = randomStack.peek();
        playerCardCenter.setImageResource(cards.getResourceId(randomStack.pop(), -1));
        playerCardRightId = randomStack.peek();
        playerCardRight.setImageResource(cards.getResourceId(randomStack.pop(), -1));
    }



    @SuppressLint("ClickableViewAccessibility")
    public void getListeners() {
        playerCardLeft.setOnTouchListener(new View.OnTouchListener() {
            private Rect rect;

            public boolean onTouch(View v, MotionEvent event) {
                if (event.getAction() == MotionEvent.ACTION_DOWN && !isMathWar) {
                    if (!isPlayerCardLeftFiltered) {
                        playerCardLeft.setColorFilter(Color.argb(80, 0, 0, 0));
                        rect = new Rect(v.getLeft(), v.getTop(), v.getRight(), v.getBottom());
                        isPlayerCardLeftFiltered = true;
                    } else {
                        if (playerCardPlayed.getDrawable() == playerCardLeft.getDrawable()) {
                            playerCardPlayed.setImageDrawable(null);
                        } else if (opponentCardPlayed.getDrawable() == playerCardLeft.getDrawable()) {
                            opponentCardPlayed.setImageDrawable(null);
                        }
                        playerCardLeft.setColorFilter(Color.argb(0, 0, 0, 0));
                        isPlayerCardLeftFiltered = false;
                    }
                    return true;
                } else if (event.getAction() == MotionEvent.ACTION_UP && !isMathWar) {
                    if (isPlayerCardLeftFiltered) {
                        if (playerCardPlayed.getDrawable() != playerCardLeft.getDrawable()
                                && opponentCardPlayed.getDrawable() != playerCardLeft.getDrawable()) {
                            if (playerCardPlayed.getDrawable() != playerCardCenter.getDrawable()
                                    && playerCardPlayed.getDrawable() != playerCardRight.getDrawable()) {
                                playerCardPlayed.setImageDrawable(playerCardLeft.getDrawable());
                            } else if (opponentCardPlayed.getDrawable() != playerCardCenter.getDrawable()
                                    && opponentCardPlayed.getDrawable() != playerCardRight.getDrawable()) {
                                opponentCardPlayed.setImageDrawable(playerCardLeft.getDrawable());
                            } else {
                                playerCardLeft.setColorFilter(Color.argb(0, 0, 0, 0));
                                isPlayerCardLeftFiltered = false;
                            }
                        }
                    }
                    return true;
                } else {
                    return false;
                }
            }
        });

        playerCardRight.setOnTouchListener(new View.OnTouchListener() {
            private Rect rect;

            @Override
            public boolean onTouch(View v, MotionEvent event) {
                if (event.getAction() == MotionEvent.ACTION_DOWN && !isMathWar) {
                    if (!isPlayerCardRightFiltered) {
                        playerCardRight.setColorFilter(Color.argb(80, 0, 0, 0));
                        rect = new Rect(v.getLeft(), v.getTop(), v.getRight(), v.getBottom());
                        isPlayerCardRightFiltered = true;
                    } else {
                        if (playerCardPlayed.getDrawable() == playerCardRight.getDrawable()) {
                            playerCardPlayed.setImageDrawable(null);
                        } else if (opponentCardPlayed.getDrawable() == playerCardRight.getDrawable()) {
                            opponentCardPlayed.setImageDrawable(null);
                        }
                        playerCardRight.setColorFilter(Color.argb(0, 0, 0, 0));
                        isPlayerCardRightFiltered = false;
                    }
                    return true;
                } else if (event.getAction() == MotionEvent.ACTION_UP && !isMathWar) {
                    if (isPlayerCardRightFiltered) {
                        if (playerCardPlayed.getDrawable() != playerCardRight.getDrawable()
                                && opponentCardPlayed.getDrawable() != playerCardRight.getDrawable()) {
                            if (playerCardPlayed.getDrawable() != playerCardLeft.getDrawable()
                                    && playerCardPlayed.getDrawable() != playerCardCenter.getDrawable()) {
                                playerCardPlayed.setImageDrawable(playerCardRight.getDrawable());
                            } else if (opponentCardPlayed.getDrawable() != playerCardLeft.getDrawable()
                                    && opponentCardPlayed.getDrawable() != playerCardCenter.getDrawable()) {
                                opponentCardPlayed.setImageDrawable(playerCardRight.getDrawable());
                            } else {
                                playerCardRight.setColorFilter(Color.argb(0, 0, 0, 0));
                                isPlayerCardRightFiltered = false;
                            }
                        }
                    }
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
                if (event.getAction() == MotionEvent.ACTION_DOWN && !isMathWar) {
                    if (!isPlayerCardCenterFiltered) {
                        playerCardCenter.setColorFilter(Color.argb(80, 0, 0, 0));
                        rect = new Rect(v.getLeft(), v.getTop(), v.getRight(), v.getBottom());
                        isPlayerCardCenterFiltered = true;
                    } else {
                        if (playerCardPlayed.getDrawable() == playerCardCenter.getDrawable()) {
                            playerCardPlayed.setImageDrawable(null);
                        } else if (opponentCardPlayed.getDrawable() == playerCardCenter.getDrawable()) {
                            opponentCardPlayed.setImageDrawable(null);
                        }
                        playerCardCenter.setColorFilter(Color.argb(0, 0, 0, 0));
                        isPlayerCardCenterFiltered = false;
                    }
                    return true;
                } else if (event.getAction() == MotionEvent.ACTION_UP && !isMathWar) {
                    if (isPlayerCardCenterFiltered) {
                        if (playerCardPlayed.getDrawable() != playerCardCenter.getDrawable()
                                && opponentCardPlayed.getDrawable() != playerCardCenter.getDrawable()) {
                            if (playerCardPlayed.getDrawable() != playerCardLeft.getDrawable()
                                    && playerCardPlayed.getDrawable() != playerCardRight.getDrawable()) {
                                playerCardPlayed.setImageDrawable(playerCardCenter.getDrawable());
                            } else if (opponentCardPlayed.getDrawable() != playerCardLeft.getDrawable()
                                    && opponentCardPlayed.getDrawable() != playerCardRight.getDrawable()) {
                                opponentCardPlayed.setImageDrawable(playerCardCenter.getDrawable());
                            } else {
                                playerCardCenter.setColorFilter(Color.argb(0, 0, 0, 0));
                                isPlayerCardCenterFiltered = false;
                            }
                        }
                    }
                    return true;
                } else {
                    return false;
                }
            }
        });

        playerCardPlayed.setOnTouchListener(new View.OnTouchListener() {
            private Rect rect;

            @Override
            public boolean onTouch(View v, MotionEvent event) {
                if (event.getAction() == MotionEvent.ACTION_DOWN && !isMathWar) {
                    if (playerCardPlayed.getDrawable() == playerCardLeft.getDrawable()) {
                        playerCardLeft.setColorFilter(Color.argb(0, 0, 0, 0));
                        isPlayerCardLeftFiltered = false;
                    } else if (playerCardPlayed.getDrawable() == playerCardRight.getDrawable()) {
                        playerCardRight.setColorFilter(Color.argb(0, 0, 0, 0));
                        isPlayerCardRightFiltered = false;
                    } else if (playerCardPlayed.getDrawable() == playerCardCenter.getDrawable()) {
                        playerCardCenter.setColorFilter(Color.argb(0, 0, 0, 0));
                        isPlayerCardCenterFiltered = false;
                    }
                    playerCardPlayed.setImageDrawable(null);
                    return true;
                } else {
                    return false;
                }
            }
        });

        opponentCardPlayed.setOnTouchListener(new View.OnTouchListener() {
            private Rect rect;

            @Override
            public boolean onTouch(View v, MotionEvent event) {
                if (event.getAction() == MotionEvent.ACTION_DOWN && !isMathWar) {
                    if (opponentCardPlayed.getDrawable() == playerCardLeft.getDrawable()) {
                        playerCardLeft.setColorFilter(Color.argb(0, 0, 0, 0));
                        isPlayerCardLeftFiltered = false;
                    } else if (opponentCardPlayed.getDrawable() == playerCardRight.getDrawable()) {
                        playerCardRight.setColorFilter(Color.argb(0, 0, 0, 0));
                        isPlayerCardRightFiltered = false;
                    } else if (opponentCardPlayed.getDrawable() == playerCardCenter.getDrawable()) {
                        playerCardCenter.setColorFilter(Color.argb(0, 0, 0, 0));
                        isPlayerCardCenterFiltered = false;
                    }
                    opponentCardPlayed.setImageDrawable(null);
                    return true;
                } else {
                    return false;
                }
            }
        });

        sendButton.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                if (event.getAction() == MotionEvent.ACTION_DOWN) {
                    if(enterText.getText().toString().equalsIgnoreCase("math")){
                        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                        fragmentTransaction.replace(R.id.ChatArea, new MathView(), "app");
                        fragmentTransaction.addToBackStack("app");
                        fragmentTransaction.commit();
                        isMathWar = true;
                        chat.setText("");
                    } else {
                        chat.setText("Player: " + enterText.getText().toString() + "\n" + chat.getText());
                        enterText.setText("");
                    }
                    return true;
                } else {
                    return false;
                }
            }
        });

    }
}
