package com.example.mdtho.finalproject;

import android.Manifest;
import android.app.Activity;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.RequestQueue;
import com.android.volley.toolbox.Volley;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;
import com.google.gson.JsonObject;

import org.apache.commons.io.FileUtils;
import org.apache.commons.io.IOUtils;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.Locale;
import java.util.Random;

public final class RateActivity extends AppCompatActivity {
    ImageView photoView;
    String prettyJsonString;

    /**
     * Constant to perform a read file request.
     */
    private static final int READ_REQUEST_CODE = 42;

    /**
     * Constant to request an image capture.
     */
    private static final int IMAGE_CAPTURE_REQUEST_CODE = 1;
    /**
     * Current file that we are using for our image request.
     */
    private boolean photoRequestActive = false;
    /**
     * Constant to request permission to write to the external storage device.
     */
    private static final int REQUEST_WRITE_STORAGE = 112;

    /**
     * Whether we can write to public storage.
     */
    private boolean canWriteToPublicStorage = false;
    /** Request queue for our network requests. */
    private static RequestQueue requestQueue;

    /**
     * Whether a current photo request is being processed.
     */
    private File currentPhotoFile = null;
    int current;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        requestQueue = Volley.newRequestQueue(this);
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
                startOpenFile();

            }
        });

        Button rateButton = (Button) findViewById(R.id.button9);
        rateButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Random generator = new Random();
                int i = generator.nextInt(10);
                if (photoView.getVisibility() == View.VISIBLE) {
                    if (text.getText().equals("")) {
                        while (i == getCurrent()) {
                            i = generator.nextInt(10);
                        }
                        text.setText(catResponses[i]);
                        setCurrent(i);
                    }
                } else {
                    text.setText(noPic);
                }
            }
        });

        Button clear = (Button) findViewById(R.id.button13);
        clear.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                text.setText("");
                photoView.setVisibility(View.INVISIBLE);
            }
        });
    }

    @Override
    public void onActivityResult(final int requestCode, final int resultCode,
                                 final Intent resultData) {
        Uri currentPhotoURI;
        if (requestCode == READ_REQUEST_CODE) {
            currentPhotoURI = resultData.getData();
        } else if (requestCode == IMAGE_CAPTURE_REQUEST_CODE) {
            currentPhotoURI = Uri.fromFile(currentPhotoFile);
            photoRequestActive = false;
            if (canWriteToPublicStorage) {
                addPhotoToGallery(currentPhotoURI);
            }
        } else {
            return;
        }

        loadPhoto(currentPhotoURI);
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

    private void startOpenFile() {
        Intent intent = new Intent(Intent.ACTION_OPEN_DOCUMENT);
        intent.addCategory(Intent.CATEGORY_OPENABLE);
        intent.setType("image/*");
        startActivityForResult(intent, READ_REQUEST_CODE);
    }

    /**
     * Current bitmap we are working with.
     */
    private Bitmap currentBitmap;

    private void loadPhoto(final Uri currentPhotoURI) {
        if (currentPhotoURI == null) {
            Toast.makeText(getApplicationContext(), "No image selected",
                    Toast.LENGTH_LONG).show();
            return;
        }
        String uriScheme = currentPhotoURI.getScheme();

        byte[] imageData;
        try {
            switch (uriScheme) {
                case "file":
                    imageData = FileUtils.readFileToByteArray(new File(currentPhotoURI.getPath()));
                    break;
                case "content":
                    InputStream inputStream = getContentResolver().openInputStream(currentPhotoURI);
                    assert inputStream != null;
                    imageData = IOUtils.toByteArray(inputStream);
                    inputStream.close();
                    break;
                default:
                    Toast.makeText(getApplicationContext(), "Unknown scheme " + uriScheme,
                            Toast.LENGTH_LONG).show();
                    return;
            }
        } catch (IOException e) {
            Toast.makeText(getApplicationContext(), "Error processing file",
                    Toast.LENGTH_LONG).show();
            return;
        }
        photoView = findViewById(R.id.photoView);
        int targetWidth = photoView.getWidth();
        int targetHeight = photoView.getHeight();

        BitmapFactory.Options decodeOptions = new BitmapFactory.Options();
        decodeOptions.inJustDecodeBounds = true;
        BitmapFactory.decodeByteArray(imageData, 0, imageData.length, decodeOptions);

        int actualWidth = decodeOptions.outWidth;
        int actualHeight = decodeOptions.outHeight;
        int scaleFactor = Math.min(actualWidth / targetWidth, actualHeight / targetHeight);

        BitmapFactory.Options modifyOptions = new BitmapFactory.Options();
        modifyOptions.inJustDecodeBounds = false;
        modifyOptions.inSampleSize = scaleFactor;
        modifyOptions.inPurgeable = true;

        // Actually draw the image
        updateCurrentBitmap(BitmapFactory.decodeByteArray(imageData,
                0, imageData.length, modifyOptions), true);
        photoView.setVisibility(View.VISIBLE);
    }

    public void updateCurrentBitmap(final Bitmap setCurrentBitmap, final boolean resetInfo) {
        currentBitmap = setCurrentBitmap;
        ImageView photoView = findViewById(R.id.photoView);
        photoView.setImageBitmap(currentBitmap);
        boolean t = resetInfo;
    }

    void addPhotoToGallery(final Uri toAdd) {
        Intent mediaScanIntent = new Intent(Intent.ACTION_MEDIA_SCANNER_SCAN_FILE);
        mediaScanIntent.setData(toAdd);
        this.sendBroadcast(mediaScanIntent);
    }

    protected void finishProcessImage(final String jsonResult) {
        /*
         * Pretty-print the JSON into the bottom text-view to help with debugging.
         */
        TextView textView = findViewById(R.id.jsonResult);
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        JsonParser jsonParser = new JsonParser();
        JsonElement jsonElement = jsonParser.parse(jsonResult);
        prettyJsonString = gson.toJson(jsonElement);
        textView.setText(prettyJsonString);
    }


}
