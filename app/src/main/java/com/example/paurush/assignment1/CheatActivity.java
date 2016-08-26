package com.example.paurush.assignment1;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;


public class CheatActivity extends AppCompatActivity {

    private int num_fac = 2;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cheat);
        savedInstanceState = getIntent().getExtras();
        int num = savedInstanceState.getInt("number");

        TextView message = (TextView)findViewById(R.id.num_msg);
        message.setText(""+num);
        int total_fac = checkFactors(num);
        TextView total = (TextView) findViewById(R.id.total);
        total.setText(""+total_fac);
    }
    private int checkFactors(int num) {
        for(int i=2; i<num; i++){

            if(num % i == 0){
                num_fac++;

            }
        }
        return num_fac;
    }
}
