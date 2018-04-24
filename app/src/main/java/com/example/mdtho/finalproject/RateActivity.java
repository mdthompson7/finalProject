package com.example.mdtho.finalproject;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import com.google.gson.JsonArray;
import com.google.gson.JsonParser;
import com.google.gson.JsonObject;


import java.util.Random;

public class RateActivity extends AppCompatActivity {
    int current;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rate);

        final TextView text = (TextView) findViewById(R.id.textView3);
        text.setText("");

        final ImageView catPic = (ImageView) findViewById(R.id.imageView4);
        catPic.setVisibility(View.INVISIBLE);

        final ImageView dogPic = (ImageView) findViewById(R.id.imageView5);
        dogPic.setVisibility(View.INVISIBLE);

        final String[] catResponses = new String[10];
        catResponses[0] = "Your cat is a 10/10, obviously!";
        catResponses[1] = "Best boy, 11/10.";
        catResponses[2] = "Oh boy, definitely 12/10.";
        catResponses[3] = "Clearly a perfect 5/7.";
        catResponses[4] = "It's a cat. 10/10";
        catResponses[5] = "Ten.";
        catResponses[6] = "Perfect kitty!";
        catResponses[7] = "That is a cute cat.";
        catResponses[8] = "Hope that cat doesn't bite, 10/10.";
        catResponses[9] = "They're good cats, Brent.";

        final String notCat = "That's... not a cat. Try again.";

        final String noPic = "Upload a cat picture!";

        Random generator = new Random();
        int i = generator.nextInt(10);
        setCurrent(i);

        Button home = (Button) findViewById(R.id.button6);
        home.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent myIntent = new Intent(RateActivity.this, MainActivity.class);
                startActivity(myIntent);
            }
        });

        Button upButton = (Button) findViewById(R.id.button11);
        upButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                text.setText("");
                if (catPic.getVisibility() == View.INVISIBLE) {
                    catPic.setVisibility(View.VISIBLE);
                    dogPic.setVisibility(View.INVISIBLE);
                } else {
                    catPic.setVisibility(View.INVISIBLE);
                    dogPic.setVisibility(View.VISIBLE);
                }
            }
        });

        Button rateButton = (Button) findViewById(R.id.button9);
        rateButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Random generator = new Random();
                int i = generator.nextInt(10);
                if (catPic.getVisibility() == View.VISIBLE) {
                    if (text.getText().equals("")) {
                        while (i == getCurrent()) {
                            i = generator.nextInt(10);
                        }
                        text.setText(catResponses[i]);
                        setCurrent(i);
                    }
                } else if (dogPic.getVisibility() == View.VISIBLE) {
                    if (text.getText().equals("")) {
                            text.setText(notCat);
                    }
                }
                else {
                    text.setText(noPic);
                }
            }
        });

        Button clear = (Button) findViewById(R.id.button13);
        clear.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                text.setText("");
                catPic.setVisibility(View.INVISIBLE);
                dogPic.setVisibility(View.INVISIBLE);
            }
        });
    }

    public void setCurrent(int a) {
        current = a;
    }

    public int getCurrent() {
        return current;
    }
    /**
     * Determine if the image contains a cat.
     *
     * @param json          the JSON string returned by MCS API
     * @param minConfidence the minimum confidence required for the determination
     * @return true is there's a cat
     */
    public static boolean isACat(final String json, final double minConfidence) {
        try {
            JsonParser parser = new JsonParser();
            JsonObject object = parser.parse(json).getAsJsonObject();
            JsonArray o = object.getAsJsonArray("tags");
            for (int i = 0; i < o.size(); i++) {
                JsonObject temp = o.get(i).getAsJsonObject();
                if (temp.get("name").getAsString().equals("cat")) {
                    if (temp.get("confidence").getAsDouble() >= minConfidence) {
                        return true;
                    }
                }
            }
            return false;
        } catch (Exception e) {
            return false;
        }
    }

}
