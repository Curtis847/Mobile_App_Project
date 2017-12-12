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

public class Main2Activity extends AppCompatActivity {

    private static final int REQUEST_CODE_IMAGE_CAPTURE = 100;
    ImageView ivImage;

    private EditText editMessage;
    private DatabaseReference mDatabase;
    private RecyclerView mMessageList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        editMessage = (EditText) findViewById(R.id.addMessageOne);
        mDatabase = FirebaseDatabase.getInstance().getReference().child("MessagesOne");
        mMessageList = (RecyclerView) findViewById(R.id.messageRec);
        mMessageList.setHasFixedSize(true);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        linearLayoutManager.setStackFromEnd(true);
        mMessageList.setLayoutManager(linearLayoutManager);

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

    public void sendButtonClicked(View view) {
        final String messageValue = editMessage.getText().toString().trim();
        if (!TextUtils.isEmpty(messageValue)) {
            final DatabaseReference newPost = mDatabase.push();
            newPost.child("content").setValue(messageValue);
        }
    }

    Query query = FirebaseDatabase.getInstance()
            .getReference()
            .child("MessagesOne")
            .limitToLast(50);





    FirebaseRecyclerOptions<Message> options =
            new FirebaseRecyclerOptions.Builder<Message>()
            .setQuery(query, Message.class)
            .build();

    FirebaseRecyclerAdapter FBRA = new FirebaseRecyclerAdapter<Message, MessageViewHolder>(options) {

        @Override
        public MessageViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View view = LayoutInflater.from(parent.getContext())
                    .inflate(R.layout.message_layout, parent, false);

            return new MessageViewHolder(view);
        }
        @Override
        protected void onBindViewHolder(MessageViewHolder holder, int position, Message model) {
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
