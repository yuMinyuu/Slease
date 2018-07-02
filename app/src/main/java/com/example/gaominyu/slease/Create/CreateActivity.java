package com.example.gaominyu.slease.Create;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.graphics.drawable.BitmapDrawable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.FrameLayout;
import android.widget.GridLayout;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;


import com.example.gaominyu.slease.R;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class CreateActivity extends AppCompatActivity {

    private static final int TAKE_MORE_PHOTO_REQUEST = 1;  // The request code for callback
    private GridLayout gridLayout;
    private Button submit_button;
    private DatabaseReference mFirebaseDatabase;
    private Spinner frequencyDropDown;
    private Spinner categoryDropDown;
    private TextView inputItemName;
    private TextView inputDescription;
    private TextView inputDeposit;
    private TextView inputRate;
    private CheckBox checkCash;
    private CheckBox checkTransfer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create);

        // Declare layout elements and firebase item directory
        mFirebaseDatabase = FirebaseDatabase.getInstance().getReference("items");
        categoryDropDown = findViewById(R.id.spinnerCategory);
        frequencyDropDown = findViewById(R.id.spinnerFrequency);
        inputItemName = findViewById(R.id.txtVName);
        inputDescription = findViewById(R.id.txtVDescription);
        inputDeposit = findViewById(R.id.txtVDeposit);
        inputRate = findViewById(R.id.txtVRatePerDay);
        checkCash = findViewById(R.id.checkBox_Cash);
        checkTransfer = findViewById(R.id.checkBox_Transfer);

        // Programmatically initialize the spinner for categories and frequency
        initFirstSpinner();
        initSecondSpinner();

        // Get images from ImageHolder to set up image GridList
        initGridLayout(false);

        // Submit all contents to RealTime Database
        initSubmitButton();
    }

    @Override
    public void onResume()
    {  // After a pause OR at startup
        super.onResume();
        //Refresh your stuff here
    }

    private void initFirstSpinner() {

        categoryDropDown.setPrompt("Select a category");

        // Spinner Drop down elements with some trumped-up examples of choices
        List<String> languages = new ArrayList<String>();
        languages.add("Select One");
        languages.add("Drone");
        languages.add("Camera");
        languages.add("Costume");
        languages.add("Auto Mobile");
        languages.add("Bicycle");
        languages.add("Motorcycle");

        // Creating adapter for spinner and disable the first item in the list from selection
        ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, languages) {
            @Override
            public boolean isEnabled(int position) {
                if (position == 0) {
                    // Disable the second item from Spinner
                    return false;
                } else {
                    return true;
                }
            }

            @Override
            public View getDropDownView(int position, View convertView,
                                        ViewGroup parent) {
                View view = super.getDropDownView(position, convertView, parent);
                TextView tv = (TextView) view;
                if (position == 0) {
                    // Set the disable item text color
                    tv.setTextColor(Color.GRAY);
                } else {
                    tv.setTextColor(Color.BLACK);
                }
                return view;
            }
        };

        // Drop down layout style - list view with radio button
        dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        // attaching data adapter to spinner
        categoryDropDown.setAdapter(dataAdapter);
        categoryDropDown.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String item = parent.getItemAtPosition(position).toString();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }

        });
    }

    private void initSecondSpinner() {

        // Spinner Drop down elements with some trumped-up examples of choices
        List<String> languages = new ArrayList<>();
        languages.add("Day");
        languages.add("Week");
        languages.add("Month");
        languages.add("Quarter");
        languages.add("Half Year");
        languages.add("Year");

        // Creating adapter for spinner and disable the first item in the list from selection
        ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, languages);

        // Drop down layout style - list view with radio button
        dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        // attaching data adapter to spinner
        frequencyDropDown.setAdapter(dataAdapter);
        frequencyDropDown.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String item = parent.getItemAtPosition(position).toString();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }

        });
    }

    private void initGridLayout(boolean isCallBack) {

        gridLayout = findViewById(R.id.create_images);
        if(isCallBack) {
            gridLayout.removeAllViews();
        }
        ImageHolder imageHolder = ImageHolder.getSingleton();
        if(imageHolder.isEmpty())
            return;
        int size = imageHolder.count();
        ArrayList<Bitmap> images = imageHolder.getImages();
        final ImageButton imageButton = new ImageButton(getApplicationContext());

        // Create a FrameLayout of image with cross button for each Bitmap file
        for(Iterator<Bitmap> it = images.iterator(); it.hasNext();){

            final FrameLayout frameLayout = new FrameLayout(getApplicationContext());
            gridLayout.addView(frameLayout);
            final ImageView imageView = new ImageView(getApplicationContext());
            Bitmap bmp = it.next();
            imageView.setImageBitmap(Bitmap.createScaledBitmap(bmp, 320,426, false));
            LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(160, 160);
            lp.setMargins(10, 20, 10, 0);
            imageView.setLayoutParams(lp);
            frameLayout.addView(imageView);
            Button crossButton = new Button(getApplicationContext());
            crossButton.setBackgroundResource(R.drawable.cross16);
            crossButton.setLayoutParams(new FrameLayout.LayoutParams(40, 40));
            frameLayout.addView(crossButton);
            bmp.recycle();

            // Remove this entire framelayout when clicking its containing cross button and
            // delete its image file from imageList
            crossButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    gridLayout.removeView(frameLayout);
                    if(gridLayout.getChildCount() < 10) {
                        imageButton.setVisibility(View.VISIBLE);
                    }
                }
            });
        }

        // Add a imageButton behind to allow more images to be added
        imageButton.setBackgroundResource(R.drawable.plus);
        imageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                // Create static instance of ImageHolder class to hold the images temporarily
                ArrayList<Bitmap> images = new ArrayList<>();
                for(int i = 0 ; i < gridLayout.getChildCount() - 1; i++) {
                    FrameLayout frameLayout = (FrameLayout)gridLayout.getChildAt(i);
                    ImageView imageView = (ImageView)frameLayout.getChildAt(0);
                    Bitmap bitmap = ((BitmapDrawable)imageView.getDrawable()).getBitmap();
                    images.add(bitmap);
                }
                ImageHolder imageHolder = ImageHolder.getSingleton();
                imageHolder.clearHolder();
                imageHolder.setImages(images);

                // Go to PhotoActivity
                Intent intent = new Intent(CreateActivity.this, PhotoActivity.class);
                intent.putExtra("isCallBack", true);
                startActivityForResult(intent, TAKE_MORE_PHOTO_REQUEST);
            }
        });
        imageButton.setLayoutParams(new FrameLayout.LayoutParams(160, 160));
        gridLayout.addView(imageButton);

        if(size >= 9 ) {
            imageButton.setVisibility(View.GONE);
        }
    }

    // When the user is done with adding more photos, the system calls this to reset image list
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        // Check which request we're responding to
        if (requestCode == TAKE_MORE_PHOTO_REQUEST) {
            // Make sure the request was successful
            if (resultCode == RESULT_OK) {
                initGridLayout(true);
            }
        }
    }

    protected  void initSubmitButton () {

        submit_button = findViewById(R.id.submit_button);

        submit_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });
    }

// Do not touch above !!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!


//    public boolean onOptionsItemSelected(MenuItem item){
//        switch((item.getItemId())){
//            case R.id.addListing:
//                addNewListing();
//
//                //backtoMainActivity();
//                return true;
//
//        }
//        return CreateActivity.super.onOptionsItemSelected(item);
//    }
//
//    private void addNewListing(){
//        String name = txtVName.getText().toString().trim();
//        String description = txtVDescription.getText().toString().trim();
//        String category = spinnerCategory.getText().toString().trim();
//        String  deposit = txtVDeposit.getText().toString().trim();
//        String ratePerDay = txtVRatePerDay.getText().toString().trim();
//
//        //write a listing to the database
//        DatabaseReference mDatabase = FireBaseDatabase.getInstance().getReference("listings");
//        Listing listingItem = new Listing();
//        listingItem.setItemName(name);
//
//
//    }
}