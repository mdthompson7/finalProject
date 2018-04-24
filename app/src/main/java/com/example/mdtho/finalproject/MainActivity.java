package com.example.mdtho.finalproject;

import android.view.View.OnClickListener;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button button1 = (Button) findViewById(R.id.button);

        button1.setOnClickListener(new OnClickListener() {
            public void onClick(View v) {
                Intent myIntent = new Intent(MainActivity.this, FactActivity.class);
                startActivity(myIntent);
            }
        });

        Button button2 = (Button) findViewById(R.id.button2);

        button2.setOnClickListener(new OnClickListener() {
            public void onClick(View v) {
                Intent myIntent = new Intent(MainActivity.this, PicActivity.class);
                startActivity(myIntent);
            }
        });

        Button button3 = (Button) findViewById(R.id.button3);

        button3.setOnClickListener(new OnClickListener() {
            public void onClick(View v) {
                Intent myIntent = new Intent(MainActivity.this, RateActivity.class);
                startActivity(myIntent);
            }
        });
    }
}
