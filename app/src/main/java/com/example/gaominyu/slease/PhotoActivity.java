package com.example.gaominyu.slease;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.hardware.Camera;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Toast;

public class PhotoActivity extends AppCompatActivity {

    private static final String TAG = "PhotoActivity";
    private FrameLayout photoFrame;
    private Camera camera;
    private CameraView cameraView;
    private LinearLayout linearLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_photo);

        // Object instantiation
        Button btnTakePhoto = findViewById(R.id.photo_button1);
        photoFrame = findViewById(R.id.photo_frame);
        linearLayout = findViewById(R.id.photo_thumbnails);

        // Open the camera
        camera = Camera.open();

        // Show a surface view of the camera inside our FrameLayout
        cameraView = new CameraView(this, camera);
        photoFrame.addView(cameraView);

//        // Initialize the HorizontalScrollView for images taken
//        HorizontalGridView horizontalGridView = findViewById(R.id.photo_gridView);
//        GridElementAdapter adapter = new GridElementAdapter(this);
//        horizontalGridView.setAdapter(adapter);

        // Check if max 9 pics is met before allowing another picture to be taken
        btnTakePhoto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                captureImage(view);
//                Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
//                startActivityForResult(intent, 0);
            }
        });
    }

    public  void captureImage(View view) {
        if(camera != null){
            camera.takePicture(null, null, new Camera.PictureCallback() {
                @Override
                public void onPictureTaken(byte[] data, Camera camera) {

                    // Save this picture to the page's list of thumbnails
                    Toast.makeText(getApplicationContext(), "taken", Toast.LENGTH_LONG).show();

                    ImageView image = (ImageView) linearLayout.getChildAt(0);
                    Bitmap bmp = BitmapFactory.decodeByteArray(data, 0, data.length);
                    image.setImageBitmap(Bitmap.createScaledBitmap(bmp, 320,426, false));
                    image.setVisibility(View.VISIBLE);

                    // Restart camera after taking one picture
                    camera.startPreview();


                }
            });
        }
    }
}
