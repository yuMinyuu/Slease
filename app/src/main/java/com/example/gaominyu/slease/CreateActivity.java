package com.example.gaominyu.slease;

import android.content.Intent;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class CreateActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create);

        // Programmatically initialize the spinner for categories
        initSimpleSpinner();

        // Get images from PhotoActivity
        Intent intent = getIntent();
        Bundle extras = intent.getExtras();
    }

    private void initSimpleSpinner() {

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

                //Toast.makeText(parent.getContext(), "Android Simple Spinner Example Output..." + item, Toast.LENGTH_LONG).show();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }

        });
    }// Do not touch this !!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!


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
