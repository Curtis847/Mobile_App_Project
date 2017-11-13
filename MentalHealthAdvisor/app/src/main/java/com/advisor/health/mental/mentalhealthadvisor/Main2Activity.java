package com.advisor.health.mental.mentalhealthadvisor;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class Main2Activity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);


        final Button checkSelf = (Button)findViewById(R.id.selfChecker);
        final Button setVisible = (Button)findViewById(R.id.button3);
        final Button hideBipolar = (Button)findViewById(R.id.button2);
        final Button copeButton = (Button)findViewById(R.id.copeButton);

        setVisible.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                TextView text = (TextView) findViewById(R.id.textView3) ;
                text.setVisibility(View.VISIBLE);
                setVisible.setVisibility(View.INVISIBLE);
                checkSelf.setVisibility(View.INVISIBLE);
                hideBipolar.setVisibility(View.VISIBLE);
                copeButton.setVisibility(View.VISIBLE);
            }
        });

        hideBipolar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                TextView text = (TextView) findViewById(R.id.textView3);
                text.setVisibility(View.INVISIBLE);
                hideBipolar.setVisibility(View.INVISIBLE);
                setVisible.setVisibility(View.VISIBLE);
                checkSelf.setVisibility(View.VISIBLE);
                copeButton.setVisibility((View.INVISIBLE));
            }
        });

    }
}
