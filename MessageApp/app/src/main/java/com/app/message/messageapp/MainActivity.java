package com.app.message.messageapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        Button chatRoom = (Button)findViewById(R.id.chatButton);
        Button chatRoomTwo = (Button)findViewById(R.id.chatButtonTwo);
        Button chatRoomThree = (Button)findViewById(R.id.chatButtonThree);
        Button signOutBtn = (Button)findViewById(R.id.signOutBtn);

        chatRoom.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(),FirstChatActivity.class);
                startActivity(intent);
            }
        });

        chatRoomTwo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent (getApplicationContext(),Main3Activity.class);
                startActivity(intent);
            }
        });

        chatRoomThree.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent (getApplicationContext(),Main4Activity.class);
                startActivity(intent);
            }
        });

        signOutBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent (getApplicationContext(),LoginActivity.class);
                startActivity(intent);
            }
        });
    }

}
