package com.example.peter.findme;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.EditText;
import android.widget.TextView;

public class UserAreaActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_area);
        // declare variables
        final EditText etUser = (EditText) findViewById(R.id.etUser);
        final EditText etEmail = (EditText) findViewById(R.id.etEmail);
        final TextView welcomeMessage = (TextView) findViewById(R.id.tvWelcomeMsg);

        // get parameter from Intent
        String name = getIntent().getStringExtra("name");
        String email = getIntent().getStringExtra("email");
        String userName  = getIntent().getStringExtra("username");

        // debug
        //System.out.println(userName);
        //System.out.println(email);

        String message = "Welcome to FindMe\nName: "+name+"\nEmail: "+email+"\nUser Name: "+userName;
        welcomeMessage.setText(message);
        //etEmail.setText("findme");
    }
}
