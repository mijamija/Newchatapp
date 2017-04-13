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
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;


public class RegisterActivity extends AppCompatActivity {

    EditText username, eMail, password;
    Button register;
    TextView login;
    ProgressDialog progressDialog;
    FirebaseAuth firebaseAuth;
    DatabaseReference databaseReference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        firebaseAuth = FirebaseAuth.getInstance();

        databaseReference = FirebaseDatabase.getInstance().getReference();

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
                    registerUser();
                }

            }
        });
    }

    public void registerUser()
    {

        String email = eMail.getText().toString();
        String pass = password.getText().toString();

        final User newUser = new User(username.getText().toString(), email);

        progressDialog = new ProgressDialog(RegisterActivity.this);
        progressDialog.setMessage("Registering user... please wait");
        progressDialog.show();

        firebaseAuth.createUserWithEmailAndPassword(email, pass)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        //checking if success
                        if(task.isSuccessful()){

                            FirebaseUser user = firebaseAuth.getCurrentUser();

                            databaseReference.child("users").child(user.getUid()).setValue(newUser);

                            finish();
                            startActivity(new Intent(getApplicationContext(), ChatUsers.class));
                        }else{
                            //display some message here
                            Toast.makeText(RegisterActivity.this,"Registration Error",Toast.LENGTH_LONG).show();
                        }
                        progressDialog.dismiss();
                    }
                });

    }

}
