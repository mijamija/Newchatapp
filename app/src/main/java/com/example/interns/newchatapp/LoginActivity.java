package com.example.interns.newchatapp;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class LoginActivity extends AppCompatActivity {

    Button login;
    EditText user, password;
    TextView register;
    FirebaseAuth firebaseAuth;
    ProgressDialog progressDialog;
    boolean state;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        user = (EditText) findViewById(R.id.user);
        password = (EditText) findViewById(R.id.password);

        register = (TextView) findViewById(R.id.register);

        firebaseAuth = FirebaseAuth.getInstance();

        progressDialog = new ProgressDialog(LoginActivity.this);

        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                startActivity(new Intent(LoginActivity.this, RegisterActivity.class));

            }
        });

        login = (Button) findViewById(R.id.loginButton);

        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(user.getText().toString().equals(""))
                {
                    user.setError("Can't be empty! Type something!");
                }
                else if(password.getText().toString().equals(""))
                {
                    password.setError("Can't be empty! Type something!");
                }
                else
                {
                    progressDialog.setMessage("Loging In... please Wait...");
                    progressDialog.show();

                    loginUser();
                }

            }
        });
    }

    public void loginUser()
    {
        String email = user.getText().toString();
        String pass = password.getText().toString();

        firebaseAuth.signInWithEmailAndPassword(email, pass)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        progressDialog.dismiss();
                        //if the task is successfull
                        if(task.isSuccessful()){
                            //start the profile activity
                            finish();
                            startActivity(new Intent(getApplicationContext(), ChatUsers.class));
                        }
                        else
                        {
                            Toast.makeText(LoginActivity.this, "Wrong username or password... try again", Toast.LENGTH_SHORT).show();
                        }
                    }
                });
    }
}
