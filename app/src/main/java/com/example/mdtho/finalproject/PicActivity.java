package com.example.mdtho.finalproject;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import com.squareup.picasso.Picasso;

import java.io.InputStream;
import java.net.URI;
import java.net.URL;

public class PicActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pics);

        final ImageView catPic = (ImageView) findViewById(R.id.imageView);
        catPic.setVisibility(View.INVISIBLE);
        Button home = (Button) findViewById(R.id.button5);

        home.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent myIntent = new Intent(PicActivity.this, MainActivity.class);
                startActivity(myIntent);
            }
        });

        final String imgURL  = "http://thecatapi.com/api/images/get";

        Button picButton = (Button) findViewById(R.id.button8);

        picButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                //Picasso.get().load("http://thecatapi.com/api/images/get?type=jpg").into(catPic);
                ImageView imageView;
                imageView = (ImageView) findViewById(R.id.imageView);
                Picasso.get().load("https://78.media.tumblr.com/Jjkybd3nSaqklngxLHjCTds3_500.jpg").noPlaceholder().into(imageView);
                imageView.setVisibility(View.VISIBLE);
            }
        });
    }
}