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

        login = (TextView) findViewById(R.id.login);

        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity( new Intent(RegisterActivity.this, LoginActivity.class));
            }
        });

        register = (Button) findViewById(R.id.registerButton);

        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(username.getText().toString().equals(""))
                {
                    username.setError("Can't be empty!");
                }
                else if(!username.getText().toString().matches("[A-Za-z0-9]+"))
                {
                    username.setError("Only letters and number allowed!");
                }
                else if(username.getText().toString().length() < 5)
                {
                    username.setError("Need to be longer than 5 characters");
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
