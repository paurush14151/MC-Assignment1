package com.example.paurush.assignment1;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Random;

public class MainActivity extends AppCompatActivity {

    private Button correct;
    private Button incorrect;
    private Button next;
    private TextView numberView;
    private TextView scoreView;
    private int num;
    private String savedNumber="savedNumber";
    private String savedScore="savedScore";
    private int score = 0;
    private int flag = 0;

    private TextView check;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        if(savedInstanceState!=null)
        {
            num = savedInstanceState.getInt(savedNumber);
            score = savedInstanceState.getInt(savedScore);
        }
        else
        {
            num = new Random().nextInt(1000)+1;
        }

        displayQuestion();

        correct = (Button) findViewById(R.id.correct_button);
        incorrect = (Button) findViewById(R.id.incorrect_button);
        next = (Button) findViewById(R.id.next_button);
        correct.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Boolean chk;
                chk=checkPrime(num);
                if(chk) {
                    if(flag == 0)
                    {
                        score++;
                        flag=1;
                    }
                    Toast t = Toast.makeText(MainActivity.this, "Correct answer :)", Toast.LENGTH_SHORT);
                    t.setGravity(Gravity.BOTTOM | Gravity.CENTER_HORIZONTAL, 0, 380);
                    t.show();
                }else{
                    if(flag == 0)
                    {
                        score--;
                        flag=1;
                    }
                    Toast t = Toast.makeText(MainActivity.this, "Incorrect answer :(", Toast.LENGTH_SHORT);
                    t.setGravity(Gravity.BOTTOM | Gravity.CENTER_HORIZONTAL, 0, 380);
                    t.show();
                }
            }
        });
        incorrect.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Boolean chk;
                chk=checkPrime(num);
                if(chk) {
                    if(flag == 0)
                    {
                        score--;
                        flag=1;
                    }
                    Toast t = Toast.makeText(MainActivity.this, "Incorrect answer :(", Toast.LENGTH_SHORT);
                    t.setGravity(Gravity.BOTTOM | Gravity.CENTER_HORIZONTAL, 0, 380);
                    t.show();
                }else{
                    if(flag == 0)
                    {
                        score++;
                        flag=1;
                    }
                    Toast t = Toast.makeText(MainActivity.this, "Correct answer :)", Toast.LENGTH_SHORT);
                    t.setGravity(Gravity.BOTTOM | Gravity.CENTER_HORIZONTAL, 0, 380);
                    t.show();
                }
            }
        });
        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                flag = 0;
                num = new Random().nextInt(1000)+1;
                displayQuestion();
            }
        });
    }

    private Boolean checkPrime(int num) {
        for(int i=2; i<=num/2; i++){
            if(num % i == 0){
                return false;
            }
        }
        return true;
    }

    private void displayQuestion()
    {
        numberView = (TextView) findViewById(R.id.check);
        numberView.setText(Integer.toString(num));
        scoreView = (TextView) findViewById(R.id.textView4);
        scoreView.setText(Integer.toString(score));
    }

    @Override
    public void onSaveInstanceState(Bundle outState){
        super.onSaveInstanceState(outState);
        outState.putInt(savedNumber, num);
        outState.putInt(savedScore, score);
    }
}

