package com.example.gaominyu.slease;

import android.hardware.Camera;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.Toast;

public class PhotoActivity extends AppCompatActivity {

    FrameLayout photoFrame;
    Camera camera;
    CameraView cameraView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_photo);

        // Object instantiation
        Button btnTakePhoto = findViewById(R.id.photo_takePhoto);
        photoFrame = findViewById(R.id.photo_frame);

        // Open the camera
        camera = Camera.open();

        // Show a surface view of the camera inside our FrameLayout
        cameraView = new CameraView(this, camera);
        photoFrame.addView(cameraView);



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

                    //debugger line
                    Toast.makeText(getApplicationContext(), "One photo is taken.", Toast.LENGTH_LONG).show();

                    //save this picture to the page's list of thumbnails

                }
            });
        }
    }
//    @Override
//    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
//        super.onActivityResult(requestCode, resultCode, data);
//        Bitmap bitmap = (Bitmap)data.getExtras().get("data");
//        photoPreview.setImageBitmap(bitmap);
//    }
}
