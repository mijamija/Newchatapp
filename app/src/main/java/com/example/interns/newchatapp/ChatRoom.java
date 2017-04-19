package com.example.interns.newchatapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class ChatRoom extends AppCompatActivity {

    EditText message;
    Button sendButton;
    LinearLayout space;
    String otherPerson;
    DatabaseReference databaseReference;
    //FirebaseUser user;
    FirebaseAuth firebaseAuth;
    TextView text;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chat_room);

        message = (EditText) findViewById(R.id.message);
        sendButton = (Button) findViewById(R.id.sendButton);
        space = (LinearLayout) findViewById(R.id.space);
        text = (TextView) findViewById(R.id.text);

        firebaseAuth = FirebaseAuth.getInstance();

        databaseReference = FirebaseDatabase.getInstance().getReference();

        Intent intent = getIntent();
        otherPerson = intent.getStringExtra("chatName");

        sendButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(!message.getText().toString().equals(""))
                {
                    Intent i = getIntent();
                    MessageInfo info = new MessageInfo();
                    long temp = System.currentTimeMillis();
                    String string = Long.toString(temp);
                    FirebaseUser user = firebaseAuth.getCurrentUser();

                    info.setMessage(message.getText().toString());
                    info.setTime(string);
                    info.setFromID(user.getUid());
                    info.setToID(i.getStringExtra("ID"));

                    String myID = databaseReference.child("messages").push().getKey();
                    databaseReference.child("messages").child(myID).setValue(info);
                    databaseReference.child("user-messages").child(user.getUid()).child(i.getStringExtra("ID")).child(myID).setValue(1);

                    text.setText(text.getText().toString() + "\n" + message.getText().toString());
                    message.setText("");
                }
            }
        });
    }

}
