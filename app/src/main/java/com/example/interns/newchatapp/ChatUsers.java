package com.example.interns.newchatapp;

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
//    EditText usernameSearch;
//    Button add;
    FirebaseAuth firebaseAuth;
    FirebaseUser user;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chat_users);

        databaseReference = FirebaseDatabase.getInstance().getReference();

        //add = (Button) findViewById(R.id.addButton);

        //usernameSearch = (EditText) findViewById(R.id.usernameSearch);

        firebaseAuth = FirebaseAuth.getInstance();

        user = firebaseAuth.getCurrentUser();

        userList = new ArrayList<>();
        listOfIDs = new ArrayList<>();

        list = (ListView) findViewById(R.id.listView);
//
//        add.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                String username = usernameSearch.getText().toString();
//                boolean flag = false;
//
//                for(String temp : userList)
//                {
//                    if(temp.equals(username)) {
//                        listViewList.add(temp);
//                        makeUserList();
//                        usernameSearch.setText("");
//                        flag = true;
//                    }
//                }
//                if(!flag)
//                {
//                    Toast.makeText(ChatUsers.this, "User not found...", Toast.LENGTH_SHORT).show();
//                    usernameSearch.setText("");
//                }
//            }
//        });

      new BuildFriendList().execute();
    }

    private class BuildFriendList extends AsyncTask<String, Void, String>
    {

        @Override
        protected String doInBackground(String... params) {

            databaseReference.child("users").addValueEventListener( new ValueEventListener()
            {
                @Override
                public void onDataChange(DataSnapshot dataSnapshot) {

                    for( DataSnapshot ds : dataSnapshot.getChildren())
                    {
                        if(!ds.getKey().equals(user.getUid())) {
                            userList.add(ds.getValue(User.class).getUserName());
                            listOfIDs.add(ds.getKey());
                        }
                    }
                }

                @Override
                public void onCancelled(DatabaseError databaseError) {
                    Toast.makeText(ChatUsers.this, "Error", Toast.LENGTH_SHORT).show();
                }
            });

//            databaseReference.child("user-messages").child(user.getUid()).addValueEventListener(new ValueEventListener() {
//                @Override
//                public void onDataChange(DataSnapshot dataSnapshot) {
//                    for( DataSnapshot ds : dataSnapshot.getChildren())
//                    {
//                        listOfFriendIDs.add(ds.getKey());
//                    }
//                }
//
//                @Override
//                public void onCancelled(DatabaseError databaseError) {
//                    Toast.makeText(ChatUsers.this, "Error", Toast.LENGTH_SHORT).show();
//                }
//            });

            return null;
        }

        @Override
        protected void onPostExecute(String s) {
            makeUserList();
        }

    }


    public void makeUserList()
    {

        adapter = new ArrayAdapter(this, android.R.layout.simple_list_item_1, userList);

        list.setAdapter(adapter);

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
