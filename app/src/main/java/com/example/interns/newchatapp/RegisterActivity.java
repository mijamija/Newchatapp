package com.example.interns.newchatapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class RegisterActivity extends AppCompatActivity {

    EditText username, eMail, password;
    Button register;
    TextView login;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        username = (EditText) findViewById(R.id.username);
        eMail = (EditText) findViewById(R.id.email);
        password = (EditText) findViewById(R.id.password);

        register = (Button) findViewById(R.id.registerButton);

        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(username.getText().toString().equals(""))
                {
                    username.setError("Can't be empty!");
                }
                else if(eMail.getText().toString().equals(""))
                {
                    eMail.setError("Can't be empty!");
                }
                else if(password.getText().toString().equals(""))
                {
                    password.setError("Can't be empty!");
                }
                else
                {
                    startActivity( new Intent(RegisterActivity.this, ChatUsers.class));
                }

            }
        });
    }
}
