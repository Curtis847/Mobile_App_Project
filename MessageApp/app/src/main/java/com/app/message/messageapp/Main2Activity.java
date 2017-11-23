package com.app.message.messageapp;

import android.content.Intent;
import android.graphics.Bitmap;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.Toast;

public class Main2Activity extends AppCompatActivity {

    private static final int REQUEST_CODE_IMAGE_CAPTURE = 100;
    ImageView ivImage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        ImageButton cameraBtn = (ImageButton) findViewById(R.id.goToCameraBtn);
        cameraBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                captureImage();
                Intent iCamera = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                startActivityForResult(iCamera, REQUEST_CODE_IMAGE_CAPTURE);
            }
        });
        this.getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN);
    }
    private void captureImage() {
        cameraImplicitIntent();
    }

    private void cameraImplicitIntent() {
        Intent iCamera = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        startActivityForResult(iCamera, REQUEST_CODE_IMAGE_CAPTURE);
    }

    /*@Override
    protected void onACctivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode == REQUEST_CODE_IMAGE_CAPTURE && resultCode == RESULT_OK) {
            Toast.makeText(getApplicationContext(), "Result Back from Camera App", Toast.LENGTH_SHORT).show();
            Bundle bundle = data.getExtras();
            Bitmap bitmapImage = (Bitmap) bundle.get("data");
        }
    }*/
}
