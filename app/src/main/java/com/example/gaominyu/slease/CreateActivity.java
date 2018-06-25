package com.example.gaominyu.slease;

import android.graphics.Bitmap;
import android.graphics.Color;
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
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class CreateActivity extends AppCompatActivity {

    private GridLayout gridLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create);

        // Programmatically initialize the spinner for categories and frequency
        initFirstSpinner();
        initSecondSpinner();

        // Get images from ImageHolder to set up image GridList
        initGridLayout();
    }

    private void initFirstSpinner() {

        Spinner spinner = findViewById(R.id.spinnerCategory);

        spinner.setPrompt("Select a category");

        // Spinner Drop down elements with some trumped-up examples of choices
        List<String> languages = new ArrayList<String>();
        languages.add("");
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
        spinner.setAdapter(dataAdapter);
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
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

        Spinner spinner = findViewById(R.id.spinnerFrequency);

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
        spinner.setAdapter(dataAdapter);
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String item = parent.getItemAtPosition(position).toString();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }

        });
    }

    private void initGridLayout() {

        ImageHolder imageHolder = ImageHolder.getSingleton();
        if(imageHolder.isEmpty())
            return;
        ArrayList<Bitmap> images = imageHolder.getImages();

        // Create a FrameLayout of image with cross button for each Bitmap file
        gridLayout = findViewById(R.id.create_images);
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

        }
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
