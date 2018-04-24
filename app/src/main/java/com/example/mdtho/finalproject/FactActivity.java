package com.example.mdtho.finalproject;

import android.support.v7.app.AppCompatActivity;
import android.view.View.OnClickListener;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.Random;

public class FactActivity extends AppCompatActivity {
    int current;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_facts);

        final String[] catFacts = new String[10];
        catFacts[0] = "Cats come back to full alertness from the sleep state faster than any other creature.";
        catFacts[1] = "There are approximately 100 breeds of cat.";
        catFacts[2] = "Abraham Lincoln loved cats. He had four of them while he lived in the White House.";
        catFacts[3] = "Many cats cannot properly digest cow's milk. Milk and milk products give them diarrhea.";
        catFacts[4] = "A cat can’t climb head first down a tree because every claw on a cat’s paw points the same way. To get down from a tree, a cat must back down.";
        catFacts[5] = "A cat’s nose pad is ridged with a unique pattern, just like the fingerprint of a human.";
        catFacts[6] = "Approximately 1/3 of cat owners think their pets are able to read their minds.";
        catFacts[7] = "Many Egyptians worshipped the goddess Bast, who had a woman’s body and a cat’s head.";
        catFacts[8] = "The little tufts of hair in a cat’s ear that help keep out dirt direct sounds into the ear, and insulate the ears are called \"ear furnishings.\"";
        catFacts[9] = "Approximately 40,000 people are bitten by cats in the U.S. annually.";

        final TextView text = (TextView) findViewById(R.id.textView) ;
        final String tex = (String) text.getText();
        Random generator = new Random();
        int i = generator.nextInt(10);
        text.setText(catFacts[i]);
        setCurrent(i);

        Button home = (Button) findViewById(R.id.button4);
        home.setOnClickListener(new OnClickListener() {
            public void onClick(View v) {
                Intent myIntent = new Intent(FactActivity.this, MainActivity.class);
                startActivity(myIntent);
            }
        });

        Button factButton = (Button) findViewById(R.id.button7);
        factButton.setOnClickListener(new OnClickListener() {
            public void onClick(View v) {
                Random generator = new Random();
                int i = generator.nextInt(10);
                while (i == getCurrent()) {
                    i = generator.nextInt(10);
                }
                text.setText(catFacts[i]);
                setCurrent(i);
            }
        });

    }
    public void setCurrent(int a) {
        current = a;
    }
    public int getCurrent() {
        return current;
    }
}
