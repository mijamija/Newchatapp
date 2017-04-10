package com.example.interns.newchatapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;

public class ChatUsers extends AppCompatActivity {

    ListView list;
    ArrayList<String> listOfUsers;
    ArrayAdapter<String> adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chat_users);

        listOfUsers = new ArrayList<>();

        list = (ListView) findViewById(R.id.listView);
        adapter = new ArrayAdapter<>(this,  android.R.layout.simple_list_item_1, listOfUsers);
        list.setAdapter(adapter);

        list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                startActivity( new Intent(ChatUsers.this, ChatRoom.class));

            }
        });
    }
}
