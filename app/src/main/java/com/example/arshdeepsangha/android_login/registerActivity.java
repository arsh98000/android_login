package com.example.arshdeepsangha.android_login;

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

public class registerActivity extends AppCompatActivity {

    private EditText etEmail,etPasswordRegister,etRePassword;
    private String email;
    private String confirmPassword;
    private String password;
    private Button btnRegisterAccount;
    private FirebaseAuth firebaseAuth;
    private ProgressBar progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        firebaseAuth = FirebaseAuth.getInstance();
        progressBar = findViewById(R.id.progressBar);
        etEmail = findViewById(R.id.etEmail);
        etPasswordRegister = findViewById(R.id.etPasswordRegister);
        etRePassword = findViewById(R.id.etRePassword);
        btnRegisterAccount = findViewById(R.id.btnRegisterAccount);

        getValues();

        btnRegisterAccount.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                getValues();
                validate();
            }
        });

    }

    private void getValues()
    {
        email = etEmail.getText().toString().trim();
        password = etPasswordRegister.getText().toString();
        confirmPassword = etRePassword.getText().toString();
    }

    private void validate()
    {
        if(email.equals("") || password.equals("") || confirmPassword.equals(""))
        {
            Toast.makeText(this,"Please fill in the requirements", Toast.LENGTH_LONG).show();
        }
        else if (password.length() < 6 || confirmPassword.length() < 6 )
        {
            Toast.makeText(this,"Password must be 6 characters long!", Toast.LENGTH_LONG).show();
        }
        else if(!password.equals(confirmPassword))
        {
            Toast.makeText(this,"Passwords do not match!", Toast.LENGTH_LONG).show();
        }
        else
        {
            progressBar.setVisibility(View.VISIBLE);
            progressBar.isShown();
            firebaseAuth.createUserWithEmailAndPassword(email,password).addOnCompleteListener(this, new OnCompleteListener<AuthResult>()
            {
                @Override
                public void onComplete(@NonNull Task<AuthResult> task)
                {
                    if(task.isSuccessful()){

                        // Added this feature for Demo 2,
                        //Sends an email to user for verification.
                        firebaseAuth.getCurrentUser().sendEmailVerification().
                                addOnCompleteListener(new OnCompleteListener<Void>() {
                                    @Override
                                    public void onComplete(@NonNull Task<Void> task) {
                                        //If successful , then sends email to the user.
                                        if (task.isSuccessful())
                                        {
                                            Toast.makeText(registerActivity.this,"Registration Successful! , We have sent a verfication email. Please verify.", Toast.LENGTH_LONG).show();
                                            progressBar.setVisibility(View.INVISIBLE);
                                            etEmail.setText("");
                                            etPasswordRegister.setText("");
                                            etRePassword.setText("");
                                        }
                                        else
                                        {
                                            Toast.makeText(registerActivity.this,task.getException().getMessage(), Toast.LENGTH_LONG).show();
                                            progressBar.setVisibility(View.INVISIBLE);
                                        }
                                    }
                                });

                    }
                    else
                    {
                        Toast.makeText(registerActivity.this,"Registration Unsuccessful! " + task.getException().getMessage(), Toast.LENGTH_LONG).show();
                        progressBar.setVisibility(View.INVISIBLE);
                    }

                }
            });
        }
    }
}
