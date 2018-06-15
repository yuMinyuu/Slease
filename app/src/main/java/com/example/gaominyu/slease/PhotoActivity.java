package com.example.gaominyu.slease;

import android.content.Intent;
import android.graphics.Bitmap;
import android.hardware.Camera;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ImageView;

public class PhotoActivity extends AppCompatActivity {

    FrameLayout photoFrame;
    Camera camera;
    ShowCamera showCamera;

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
        showCamera = new ShowCamera(this, camera);
        photoFrame.addView(showCamera);



//        btnTakePhoto.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
//                startActivityForResult(intent, 0);
//            }
//        });
    }

//    @Override
//    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
//        super.onActivityResult(requestCode, resultCode, data);
//        Bitmap bitmap = (Bitmap)data.getExtras().get("data");
//        photoPreview.setImageBitmap(bitmap);
//    }
}
