package com.app.message.messageapp;

import android.content.Intent;
import android.os.Handler;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageButton;
import android.widget.ImageView;

public class Main3Activity extends AppCompatActivity {

    private static final int REQUEST_CODE_IMAGE_CAPTURE = 100;
    ImageView ivImage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main3);

        ImageButton cameraBtnTwo = (ImageButton) findViewById(R.id.goToCameraBtnTwo);
        cameraBtnTwo.setOnClickListener(new View.OnClickListener() {
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
}
