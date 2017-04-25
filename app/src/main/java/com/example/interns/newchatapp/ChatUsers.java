package com.example.interns.newchatapp;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class ChatUsers extends AppCompatActivity {

    ListView list;
    ArrayList<String> userList,  listOfIDs;
    ArrayAdapter<String> adapter;
    DatabaseReference databaseReference;
    FirebaseAuth firebaseAuth;
    FirebaseUser user;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chat_users);

        databaseReference = FirebaseDatabase.getInstance().getReference();

        firebaseAuth = FirebaseAuth.getInstance();

        user = firebaseAuth.getCurrentUser();

        userList = new ArrayList<>();
        listOfIDs = new ArrayList<>();

        list = (ListView) findViewById(R.id.listView);

        adapter = new ArrayAdapter(ChatUsers.this, android.R.layout.simple_list_item_1, userList);

        list.setAdapter(adapter);

        databaseReference.child("users").addListenerForSingleValueEvent( new ValueEventListener()
        {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

                userList.clear();

                for( DataSnapshot ds : dataSnapshot.getChildren())
                {
                    if(!ds.getKey().equals(user.getUid())) {
                        userList.add(ds.getValue(User.class).getName());
                        listOfIDs.add(ds.getKey());
                   }
                }

                adapter.notifyDataSetChanged();

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                Toast.makeText(ChatUsers.this, "Error", Toast.LENGTH_SHORT).show();
            }
        });

        databaseReference.child("users").addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                userList.add(dataSnapshot.getValue(User.class).getName());
                listOfIDs.add(dataSnapshot.getKey());

                adapter.notifyDataSetChanged();
            }

            @Override
            public void onChildChanged(DataSnapshot dataSnapshot, String s) {

            }

            @Override
            public void onChildRemoved(DataSnapshot dataSnapshot) {

            }

            @Override
            public void onChildMoved(DataSnapshot dataSnapshot, String s) {

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

        list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                Intent intent = new Intent(ChatUsers.this, ChatRoom.class);
                intent.putExtra("chatName", userList.get(position));
                intent.putExtra("ID", listOfIDs.get(position));
                startActivity(intent);

            }
        });

    }


}
