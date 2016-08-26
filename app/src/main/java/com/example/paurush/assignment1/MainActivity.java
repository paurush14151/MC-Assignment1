package com.example.paurush.assignment1;

import android.content.Intent;
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
    private Button cheat;
    private Button hint;
    private TextView numberView;
    private TextView scoreView;
    private int num;
    private String savedNumber="savedNumber";
    private String savedScore="savedScore";

    private Boolean cheat_flag;
    private Boolean hint_flag;


    private double score = 0;
    private int flag = 0;
    private int num_factors = 0;

    private TextView check;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        if(savedInstanceState!=null)
        {
            num = savedInstanceState.getInt(savedNumber);
            score = savedInstanceState.getDouble(savedScore);
            hint_flag = savedInstanceState.getBoolean("hint_flag");
            cheat_flag = savedInstanceState.getBoolean("cheat_flag");

        }
        else
        {
            num = new Random().nextInt(1000)+2;
            hint_flag = false;
            cheat_flag = false;
        }

        displayQuestion();

        correct = (Button) findViewById(R.id.correct_button);
        incorrect = (Button) findViewById(R.id.incorrect_button);
        next = (Button) findViewById(R.id.next_button);
        cheat = (Button) findViewById(R.id.cheat_button);
        hint = (Button) findViewById(R.id.hint_button);

        correct.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Boolean chk;
                chk=checkPrime(num);
                if(chk) {
                    if(flag == 0 && cheat_flag == false && hint_flag == false)
                    {
                        score++;
                        flag=1;
                    }
                    else if(flag == 0 && cheat_flag == false && hint_flag == true)
                    {
                        score = score + 0.5;
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

                scoreView = (TextView) findViewById(R.id.textView4);
                scoreView.setText(Double.toString(score));
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
                    if(flag == 0 && cheat_flag == false && hint_flag == false)
                    {
                        score++;
                        flag=1;
                    }
                    else if(flag == 0 && cheat_flag == false && hint_flag == true)
                    {
                        score = score + 0.5;
                        flag=1;
                    }
                    Toast t = Toast.makeText(MainActivity.this, "Correct answer :)", Toast.LENGTH_SHORT);
                    t.setGravity(Gravity.BOTTOM | Gravity.CENTER_HORIZONTAL, 0, 380);
                    t.show();
                }

                scoreView = (TextView) findViewById(R.id.textView4);
                scoreView.setText(Double.toString(score));
            }
        });
        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                flag = 0;
                hint_flag = false;
                cheat_flag = false;
                num = new Random().nextInt(1000)+1;
                displayQuestion();
            }
        });

        hint.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (hint_flag == false)
                {
                    Intent i = new Intent(MainActivity.this, HintActivity.class);
                    i.putExtra("number",num);
                    hint_flag = true;
                    startActivity(i);
                }
                else{
                    Toast t = Toast.makeText(MainActivity.this, "You have already used the hint!", Toast.LENGTH_SHORT);
                    t.setGravity(Gravity.BOTTOM | Gravity.CENTER_HORIZONTAL, 0, 380);
                    t.show();
                }
            }
        });

        cheat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (cheat_flag == false)
                {
                    Intent i = new Intent(MainActivity.this, CheatActivity.class);
                    i.putExtra("number",num);
                    cheat_flag = true;
                    startActivity(i);
                }
                else{
                    Toast t = Toast.makeText(MainActivity.this, "You have already used the cheat!", Toast.LENGTH_SHORT);
                    t.setGravity(Gravity.BOTTOM | Gravity.CENTER_HORIZONTAL, 0, 380);
                    t.show();
                }

            }
        });


    }

    Boolean checkPrime(int num) {
        for(int i=2; i<=num/2; i++){

            if(num % i == 0){
                return false;
            }
        }
        return true;
    }

    private void displayQuestion()
    {
        numberView = (TextView) findViewById(R.id.numberntext);
        numberView.setText(Integer.toString(num));
        scoreView = (TextView) findViewById(R.id.textView4);
        scoreView.setText(Double.toString(score));
    }

    @Override
    public void onSaveInstanceState(Bundle outState){
        super.onSaveInstanceState(outState);
        outState.putInt(savedNumber, num);
        outState.putDouble(savedScore, score);
        outState.putBoolean("hint_flag", hint_flag);
        outState.putBoolean("cheat_flag", cheat_flag);
    }
}

