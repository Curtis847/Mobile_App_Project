package com.app.message.messageapp;

import android.content.Intent;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;

public class Main4Activity extends AppCompatActivity {

    private static final int REQUEST_CODE_IMAGE_CAPTURE = 100;
    ImageView ivImage;

    private EditText editMessage;
    private DatabaseReference mDatabase;
    private RecyclerView mMessageList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main4);

        editMessage = (EditText) findViewById(R.id.addMessageThree);
        mDatabase = FirebaseDatabase.getInstance().getReference().child("MessagesThree");
        mMessageList = (RecyclerView) findViewById(R.id.messageRecThree);
        mMessageList.setHasFixedSize(true);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        linearLayoutManager.setStackFromEnd(true);
        mMessageList.setLayoutManager(linearLayoutManager);

        ImageButton cameraBtnThree = (ImageButton) findViewById(R.id.goToCameraBtnThree);
        cameraBtnThree.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                captureImage();
                Intent iCamera = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                startActivityForResult(iCamera, REQUEST_CODE_IMAGE_CAPTURE);
            }
        });
        this.getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN);
    }

    public void sendButtonClicked(View view) {
        final String messageValue = editMessage.getText().toString().trim();
        if (!TextUtils.isEmpty(messageValue)) {
            final DatabaseReference newPost = mDatabase.push();
            newPost.child("content").setValue(messageValue);
        }
    }

    Query query = FirebaseDatabase.getInstance()
            .getReference()
            .child("MessagesThree")
            .limitToLast(50);



    FirebaseRecyclerOptions<Message> options =
            new FirebaseRecyclerOptions.Builder<Message>()
                    .setQuery(query, Message.class)
                    .build();

    FirebaseRecyclerAdapter FBRA = new FirebaseRecyclerAdapter<Message, Main4Activity.MessageViewHolder>(options) {

        @Override
        public Main4Activity.MessageViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View view = LayoutInflater.from(parent.getContext())
                    .inflate(R.layout.message_layout, parent, false);

            return new Main4Activity.MessageViewHolder(view);
        }
        @Override
        protected void onBindViewHolder(Main4Activity.MessageViewHolder holder, int position, Message model) {
            holder.setContent(model.getContent());
        }

    };

    @Override
    protected void onStart() {
        super.onStart();
        FBRA.startListening();

        mMessageList.setAdapter(FBRA);

    }

    @Override
    protected void onStop() {
        super.onStop();
        FBRA.stopListening();
    }

    public static class MessageViewHolder extends RecyclerView.ViewHolder {

        View mView;
        public MessageViewHolder(View itemView) {
            super(itemView);
            mView = itemView;
        }

        public void setContent(String content) {
            TextView message_content = (TextView) mView.findViewById(R.id.messageText);
            message_content.setText(content);
        }
    }

    private void captureImage() {
        cameraImplicitIntent();
    }

    private void cameraImplicitIntent() {
        Intent iCamera = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        startActivityForResult(iCamera, REQUEST_CODE_IMAGE_CAPTURE);
    }
}
