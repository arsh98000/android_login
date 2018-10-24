package com.example.arshdeepsangha.android_login;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class successfulActivity extends AppCompatActivity
{
    private TextView txtEmail;
    private Button btnSignout;
    private FirebaseAuth firebaseAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_successful);

        txtEmail = findViewById(R.id.txtEmail);
        btnSignout = findViewById(R.id.btnSignout);

        firebaseAuth = FirebaseAuth.getInstance();

        FirebaseUser user = firebaseAuth.getCurrentUser();

        txtEmail.setText("Hi " + user.getEmail() + " !" );

        btnSignout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                firebaseAuth.signOut();
                finish();
                Intent intent = new Intent(successfulActivity.this,MainActivity.class);
                startActivity(intent);
            }
        });
    }
}
