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
import android.widget.Toast;


import com.example.gaominyu.slease.Login.LoginActivity;
import com.example.gaominyu.slease.Main.BrowseActivity;
import com.example.gaominyu.slease.Model.Item;
import com.example.gaominyu.slease.Model.ItemPreview;
import com.example.gaominyu.slease.R;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.regex.Pattern;

public class CreateActivity extends AppCompatActivity {

    private static final int TAKE_MORE_PHOTO_REQUEST = 1;  // The request code for callback
    private GridLayout gridLayout;
    private Button submit_button;
    private Spinner frequencyDropDown;
    private Spinner categoryDropDown;
    private TextView inputItemName;
    private TextView inputDescription;
    private TextView inputDeposit;
    private TextView inputRate;
    private CheckBox checkCash;
    private CheckBox checkTransfer;
    private TextView errorTxtPaymentMethod;
    private DatabaseReference FirebaseDatabaseItem;
    private DatabaseReference FirebaseDatabaseItemPreview;
    private DatabaseReference FirebaseDatabaseCategories;
    private DatabaseReference FirebaseDatabaseFrequencies;
    private FirebaseDatabase mFirebaseInstance;
    private FirebaseAuth mAuth;
    private FirebaseUser user;
    private String userId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create);

        // Declare layout elements and firebase item directory
        categoryDropDown = findViewById(R.id.spinnerCategory);
        frequencyDropDown = findViewById(R.id.spinnerFrequency);
        inputItemName = findViewById(R.id.txtVName);
        inputDescription = findViewById(R.id.txtVDescription);
        inputDeposit = findViewById(R.id.txtVDeposit);
        inputRate = findViewById(R.id.txtVRatePerDay);
        checkCash = findViewById(R.id.checkBox_Cash);
        checkTransfer = findViewById(R.id.checkBox_Transfer);
        errorTxtPaymentMethod = findViewById(R.id.checkBox_ErrorDisplay);

        // Get images from ImageHolder to set up image GridList
        initGridLayout(false);

        // Set up connection point to Firebase
        mFirebaseInstance = FirebaseDatabase.getInstance();

        // get reference to 'items' node
        FirebaseDatabaseItem = mFirebaseInstance.getReference("items");
        FirebaseDatabaseItemPreview = mFirebaseInstance.getReference("items_preview");
        FirebaseDatabaseCategories = mFirebaseInstance.getReference("categories");
        FirebaseDatabaseFrequencies = mFirebaseInstance.getReference("frequencies");

        // load current user's data
        mAuth = FirebaseAuth.getInstance();
        user = mAuth.getCurrentUser();

        // Programmatically initialize the spinner for categories and frequency
        initFirstSpinner();
        initSecondSpinner();

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

        //categoryDropDown.setPrompt("Select a category");

        // Spinner Drop down elements with some trumped-up examples of choices
        final List<String> categoryList = new ArrayList<>();
        categoryList.add("Select One");
        FirebaseDatabaseCategories.addListenerForSingleValueEvent(
                new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {
                        for (DataSnapshot child : dataSnapshot.getChildren()) {
                            categoryList.add(child.getValue().toString());
                        }
                    }

                    @Override
                    public void onCancelled(DatabaseError databaseError) {
                        //handle databaseError
                    }
                });

        // Creating adapter for spinner and disable the first item in the list from selection
        ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, categoryList) {
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
        final List<String> frequencyList = new ArrayList<>();
        frequencyList.add("Select One");
        FirebaseDatabaseFrequencies.addListenerForSingleValueEvent(
                new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {
                        for (DataSnapshot child : dataSnapshot.getChildren()) {
                            frequencyList.add(child.getValue().toString());
                        }
                    }

                    @Override
                    public void onCancelled(DatabaseError databaseError) {
                        //handle databaseError
                    }
                });

        // Creating adapter for spinner and disable the first item in the list from selection
        ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, frequencyList){
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
        imageButton.setBackgroundResource(R.drawable.plus24);
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

                // Retrieve inputs
                String title = inputItemName.getText().toString().trim();
                String description = inputDescription.getText().toString().trim();
                int categoryID = categoryDropDown.getSelectedItemPosition();
                TextView errorTxtCategory = (TextView) categoryDropDown.getSelectedView();
                List<String> imageUrls = new ArrayList<>(2);
                imageUrls.add("URL1");
                imageUrls.add("URL2"); // TO DO
                String deposit = inputDeposit.getText().toString().trim();
                String rate = inputRate.getText().toString().trim();
                int frequencyID = frequencyDropDown.getSelectedItemPosition();
                TextView errorTxtFrequency = (TextView) frequencyDropDown.getSelectedView();
                boolean allowCash = checkCash.isChecked();
                boolean allowTransfer = checkTransfer.isChecked();

                // Validate inputs
                if (!isValidText(title, 50)) {
                    inputItemName.setError("Item title cannot be empty nor longer than 50 characters.");
                    return;
                }

                if(!isValidText(description, 500)){
                    inputDescription.setError("Description cannot be empty nor longer than 500 characters.");
                    return;
                }

                if(categoryDropDown.getSelectedItemPosition() == 0){
                    errorTxtCategory.setError("");
                    return;
                } else {
                    errorTxtCategory.setError(null);
                }

                if(!isValidDigit(deposit)){
                    inputDeposit.setError("Deposit cannot be empty and must be numeric.");
                    return;
                }

                if(!isValidDigit(rate)){
                    inputRate.setError("Rate cannot be empty and must be numeric.");
                    return;
                }

                if(frequencyDropDown.getSelectedItemPosition() == 0){
                    errorTxtFrequency.setError("");
                    return;
                } else {
                    errorTxtFrequency.setError(null);
                }

                if(!allowCash && !allowTransfer){
                    errorTxtPaymentMethod.setError("Please select at least one payment method.");
                    //errorTxtPaymentMethod.requestFocus();
                    return;
                } else {
                    errorTxtPaymentMethod.setError(null);
                }

                // Upload inputs
                if (user != null) {

                    // User is signed in.
                    userId = user.getUid();

                    Item item = new Item(title, description, categoryID, imageUrls, deposit, rate,
                            frequencyID, allowCash, allowTransfer);

                    ItemPreview itemPreview = new ItemPreview(title, categoryID, imageUrls.get(0), rate,
                            frequencyID);

                    String key = FirebaseDatabaseItemPreview.push().getKey();
                    FirebaseDatabaseItem.child(userId).child(key).setValue(item); // items with full info
                    FirebaseDatabaseItemPreview.child(key).setValue(itemPreview);// items with simple info

                    Toast.makeText(getApplicationContext(), "Your Item is on Slease!", Toast.LENGTH_LONG).show();

                    startActivity(new Intent(CreateActivity.this, BrowseActivity.class));

                } else {

                    Toast.makeText(getApplicationContext(), "Fail to create lease because you have not signed in", Toast.LENGTH_LONG).show();

                }
            }
        });
    }

    // Validations
    private boolean isValidText(String title, int maxLength) {
        if (title.length() != 0 && title.length() < maxLength) {
            return true;
        }
        return false;
    }

    private boolean isValidDigit(String value){
        if(value.length() != 0 )
        {
            Pattern pInt = Pattern.compile("^[-\\+]?[\\d]*$");
            Pattern pDouble = Pattern.compile("^[-\\+]?[.\\d]*$");
            if( pInt.matcher(value).matches() || pDouble.matcher(value).matches())
            {
                return true;
            }
        }
        return false;
    }

}