package com.example.juice.labapplication;

import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Random;

/**
 * Created by Justin Espiritu on 2/8/2018.
 */

public class MathView extends Fragment {

    private TextView mathEquation;
    private EditText mathAnswer;
    private ImageButton mathSendButton;
    private ArrayList<String> equationArrayList;
    private int equationNumber;
    private int correctAnswers;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.activity_mathwar_landscape, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState){
        correctAnswers = 0;
        equationNumber = 0;
        mathEquation = getView().findViewById(R.id.mathEquations);
        mathAnswer = getView().findViewById(R.id.mathAnswers);
        mathSendButton = getView().findViewById(R.id.mathSendButton);
        equationArrayList = new ArrayList<>();
        equationArrayList.add(randomMathEquation());
        setMathArea();
        getListeners();
    }

    public void setMathArea(){
        mathEquation.setText("");
        for(String equation: equationArrayList){
            mathEquation.setText(mathEquation.getText() + equation + "\n");
        }
    }

    public String randomMathEquation() {
        String equation = "";
        Random random = new Random();

        do{
            equation = random.nextInt(16) + randomEvaluator() + random.nextInt(16);
        } while (eval(equation) < 0);

        return equation;
    }

    public String randomEvaluator(){
        Random random = new Random();
        int selector = random.nextInt(3);

        switch(selector){
            case 0: return " + ";
            case 1: return " - ";
            default: return " * ";
        }
    }

    //grabbed from stackoverflow
    public static double eval(final String str) {
        return new Object() {
            int pos = -1, ch;

            void nextChar() {
                ch = (++pos < str.length()) ? str.charAt(pos) : -1;
            }

            boolean eat(int charToEat) {
                while (ch == ' ') nextChar();
                if (ch == charToEat) {
                    nextChar();
                    return true;
                }
                return false;
            }

            double parse() {
                nextChar();
                double x = parseExpression();
                if (pos < str.length()) throw new RuntimeException("Unexpected: " + (char)ch);
                return x;
            }

            // Grammar:
            // expression = term | expression `+` term | expression `-` term
            // term = factor | term `*` factor | term `/` factor
            // factor = `+` factor | `-` factor | `(` expression `)`
            //        | number | functionName factor | factor `^` factor

            double parseExpression() {
                double x = parseTerm();
                for (;;) {
                    if      (eat('+')) x += parseTerm(); // addition
                    else if (eat('-')) x -= parseTerm(); // subtraction
                    else return x;
                }
            }

            double parseTerm() {
                double x = parseFactor();
                for (;;) {
                    if      (eat('*')) x *= parseFactor(); // multiplication
                    else if (eat('/')) x /= parseFactor(); // division
                    else return x;
                }
            }

            double parseFactor() {
                if (eat('+')) return parseFactor(); // unary plus
                if (eat('-')) return -parseFactor(); // unary minus

                double x;
                int startPos = this.pos;
                if (eat('(')) { // parentheses
                    x = parseExpression();
                    eat(')');
                } else if ((ch >= '0' && ch <= '9') || ch == '.') { // numbers
                    while ((ch >= '0' && ch <= '9') || ch == '.') nextChar();
                    x = Double.parseDouble(str.substring(startPos, this.pos));
                } else if (ch >= 'a' && ch <= 'z') { // functions
                    while (ch >= 'a' && ch <= 'z') nextChar();
                    String func = str.substring(startPos, this.pos);
                    x = parseFactor();
                    if (func.equals("sqrt")) x = Math.sqrt(x);
                    else if (func.equals("sin")) x = Math.sin(Math.toRadians(x));
                    else if (func.equals("cos")) x = Math.cos(Math.toRadians(x));
                    else if (func.equals("tan")) x = Math.tan(Math.toRadians(x));
                    else throw new RuntimeException("Unknown function: " + func);
                } else {
                    throw new RuntimeException("Unexpected: " + (char)ch);
                }

                if (eat('^')) x = Math.pow(x, parseFactor()); // exponentiation

                return x;
            }
        }.parse();
    }

    public void getListeners(){
        mathSendButton.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
            if (event.getAction() == MotionEvent.ACTION_DOWN) {
                if(mathAnswer.getText().toString().equals(Integer.toString((int)(eval(equationArrayList.get(equationNumber)))))){
                    mathAnswer.setBackgroundColor(Color.argb(0,0,0,0));
                    equationArrayList.set(equationNumber, equationArrayList.get(equationNumber) + " = " + (int)eval(equationArrayList.get(equationNumber)) + " \u2713");
                    equationNumber ++;
                    equationArrayList.add(randomMathEquation());
                    correctAnswers ++;
                    mathAnswer.setText("");
                    setMathArea();
                } else {
                    mathAnswer.setText("");
                    mathAnswer.setBackgroundColor(Color.RED);
                }
                return true;
            } else {
                return false;
            }
            }
        });
}

}
