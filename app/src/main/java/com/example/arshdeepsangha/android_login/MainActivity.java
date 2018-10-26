package com.example.arshdeepsangha.android_login;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class MainActivity extends AppCompatActivity {

    private EditText etEmail;
    private EditText etPassword;
    private Button btnLogin;
    private Button btnRegister;
    private Button btnForgot;
    private String email;
    private String password;
    private ProgressBar progressBar2;

    private FirebaseAuth firebaseAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        firebaseAuth = FirebaseAuth.getInstance();

        etEmail = findViewById(R.id.etEmailLog);
        etPassword = findViewById(R.id.etPassword);
        btnLogin = findViewById(R.id.btnLogin);
        btnRegister = findViewById(R.id.btnRegister);
        btnForgot = findViewById(R.id.btnForgot);
        progressBar2 = findViewById(R.id.progressBar2);

        btnLogin.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                getValues();
                validate();
            }
        });

        btnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {
                Intent intent = new Intent(MainActivity.this,registerActivity.class);
                startActivity(intent);
            }
        });

        btnForgot.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this,forgotPasswordActivity.class);
                startActivity(intent);
            }
        });
    }

    private void getValues()
    {
        email = etEmail.getText().toString();
        password = etPassword.getText().toString();

    }

    private void validate()
    {

        if (email.equals("") || password.equals(""))
        {
            Toast.makeText(this,"Please fill in the requirements", Toast.LENGTH_LONG).show();
        }
        else if (password.length() < 6)
        {
            Toast.makeText(this,"Password must be 6 characters long!", Toast.LENGTH_LONG).show();
        }
        else
        {
            progressBar2.setVisibility(View.VISIBLE);
            progressBar2.isShown();
            firebaseAuth.signInWithEmailAndPassword(email,password).addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                @Override
                public void onComplete(@NonNull Task<AuthResult> task) {
                    if (task.isSuccessful())
                    {
                        // Added this feature for Demo 2,
                        //This is to verify if the user has clicked on the link they got in their mail.
                        if(firebaseAuth.getCurrentUser().isEmailVerified())
                        {
                            finish();
                            Intent intent = new Intent(getApplicationContext(),successfulActivity.class);
                            startActivity(intent);
                        }
                        else
                        {
                            Toast.makeText(MainActivity.this,"Please verify your email to login.", Toast.LENGTH_LONG).show();
                            progressBar2.setVisibility(View.INVISIBLE);
                        }

                    }
                    else
                    {
                        Toast.makeText(MainActivity.this,"Login Failed , Please try again !", Toast.LENGTH_LONG).show();
                        progressBar2.setVisibility(View.INVISIBLE);
                    }
                }
            });

        }
    }
}
