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

        final String[] catResponses = new String[100];
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
        catResponses[10] = "Such a pretty kitty! 10/10.";
        catResponses[11] = "Aww, look at that face! 12/10.";
        catResponses[12] = "Beautiful cat, great eyes!";
        catResponses[13] = "Lookin' good, 10/10.";
        catResponses[14] = "Meow, 10/10.";
        catResponses[15] = "Soft kitty!";
        catResponses[16] = "What a little ball of fur... 11/10.";
        catResponses[17] = "Happy kitty, 9/10.";
        catResponses[18] = "Purrrrrrr, 8/10.";
        catResponses[19] = "Purr purr purr, 9/10.";
        catResponses[20] = "A+ snuggler, 10/10.";
        catResponses[21] = "Clearly evil. Good. 10/10.";
        catResponses[22] = "This cannot be rated on the meowf scale. 10/10 tho.";
        catResponses[23] = "Not a nice kit. 9/10.";
        catResponses[24] = "H*ckin' handsome. 10/10.";
        catResponses[25] = "Wanna boop the snoot. 8/10.";
        catResponses[26] = "Good lookin' upcat. 10/10. What's upcat? Not much, hbu?";
        catResponses[27] = "Good boy, off the h*ckin charts. 13/10.";
        catResponses[28] = "Looks like he needs an extended snuggle, 9/10.";
        catResponses[29] = "14/10 truly exceptional.";
        catResponses[30] = "12/10 FanCATstic! I'm sorry.";
        catResponses[31] = "12/10 would protect at all costs.";
        catResponses[32] = "8/10 would boop.";
        catResponses[33] = "Not good. April fools, looks great 10/10.";
        catResponses[34] = "18/10 truly impressive.";
        catResponses[35] = "11/10 would definitely pet.";
        catResponses[36] = "11/10 would get scratched to all hell.";
        catResponses[37] = "CATacular! 11/10.";
        catResponses[38] = "Marvelous. 9/10.";
        catResponses[39] = "Weird looking cat, 10/10.";
        catResponses[40] = "Downright awe-inspiring 12/10.";
        catResponses[41] = "The bee's knees 8/10.";
        catResponses[42] = "The cat's pajamas... no pajamas? 9/10.";
        catResponses[43] = "Never have I ever had a cat this good 10/10.";
        catResponses[44] = "110/10 an icon.";
        catResponses[45] = "\"Is that a chicken?\" No Kylie it's a cat, 8/10.";
        catResponses[46] = "12/10 very fierce.";
        catResponses[47] = "Good girl, 10/10.";
        catResponses[48] = "12/10 h*ckin relatable.";
        catResponses[49] = "14/10 keep it up cat.";
        catResponses[50] = "Very sad boi :( 9/10.";
        catResponses[51] = "13/10.";
        catResponses[52] = "11/10 would give him whatever he wants forever.";
        catResponses[53] = "Thank you... 13/10.";
        catResponses[54] = "12/10 would love forever.";
        catResponses[55] = "Purrrrrfect 10/10.";
        catResponses[56] = "11/10 someone help him.";
        catResponses[57] = "10/10 my best friend.";
        catResponses[58] = "11/10 would not disturb.";
        catResponses[59] = "13/10 pretty as h*ck.";
        catResponses[60] = "Angery cat. Would still pet 11/10.";
        catResponses[61] = "Exotic cat 8/10.";
        catResponses[62] = "WOULD HUG SOFTLY 10/10.";
        catResponses[63] = "Please send more 7/10.";
        catResponses[64] = "Churlish kitty. 9/10 tho.";
        catResponses[65] = "13/10 impeccable.";
        catResponses[66] = "9/10 would protect at all costs.";
        catResponses[67] = "Would support his dreams 13/10.";
        catResponses[68] = "12/10 passionate kitty.";
        catResponses[69] = "14/10 would be an honor to pet.";
        catResponses[70] = "Would cherish, 13/10.";
        catResponses[71] = "H*ckin' 10/10.";
        catResponses[72] = "10/10 Is it 2023 yet? (Vietnamese year of the cat)";
        catResponses[73] = "12/10 would treasure.";
        catResponses[74] = "Would snug 10/10.";
        catResponses[75] = "Mlem 8/10.";
        catResponses[76] = "It's good he has 9 lives, I want him around forever. 10/10";
        catResponses[77] = "Would love unconditionally 11/10.";
        catResponses[78] = "Handsomest boy, 13/10.";
        catResponses[79] = "9/10 such a good boy.";
        catResponses[80] = "13/10 adorable.";
        catResponses[81] = "10/10 simply breathtaking.";
        catResponses[82] = "12/10 would make a wonderful model.";
        catResponses[83] = "I want to look at her whenever I'm stressed to feel better.";
        catResponses[84] = "This is one pretty cat. 8/10.";
        catResponses[85] = "14/10 would hug softly.";
        catResponses[86] = "That is one happy h*ckin cat. 10/10.";
        catResponses[87] = "11/10 inspiring af.";
        catResponses[88] = "9/10 h*ckin' cute.";
        catResponses[89] = "Infinity/10. By far my favorite cat.";
        catResponses[90] = "15/10 would die for.";
        catResponses[91] = "Oh h*ck too cute, 14/10.";
        catResponses[92] = "This picture is heartwarming as h*ck. 12/10";
        catResponses[93] = "11/10 would volunteer as tribute to pet.";
        catResponses[94] = "14/10 snazzy cat.";
        catResponses[95] = "11/10 breathtaking.";
        catResponses[96] = "Best one yet 13/10.";
        catResponses[97] = "Smol cat. 11/10.";
        catResponses[98] = "What a good boy, 9/10.";
        catResponses[99] = "14/10 fantastic cat.";

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
                int i = generator.nextInt(100);
                if (photoView == null) {
                    text.setText(noPic);
                } else if (photoView.getVisibility() == View.VISIBLE) {
                    if (text.getText().equals("")) {
                        while (i == getCurrent()) {
                            i = generator.nextInt(100);
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
