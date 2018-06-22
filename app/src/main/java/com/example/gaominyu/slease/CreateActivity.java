package com.example.gaominyu.slease;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
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

        Spinner spinner = (Spinner) findViewById(R.id.spinnerCategory);

        // Spinner Drop down elements
        List<String> languages = new ArrayList<String>();
        languages.add("Andorid");
        languages.add("IOS");
        languages.add("PHP");
        languages.add("Java");
        languages.add(".Net");

        // Creating adapter for spinner
        ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, languages);

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
