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
import com.google.firebase.auth.FirebaseAuth;

public class forgotPasswordActivity extends AppCompatActivity {

    private EditText etEmailReset;
    private Button btnSendReset;
    private FirebaseAuth firebaseAuth;
    private String email;
    private ProgressBar progressBar3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forgot_password);

        etEmailReset = findViewById(R.id.etEmailReset);
        btnSendReset = findViewById(R.id.btnSendReset);
        progressBar3 = findViewById(R.id.progressBar3);

        firebaseAuth = FirebaseAuth.getInstance();

        btnSendReset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                validate();
            }
        });
    }

    private void validate()
    {
        email = etEmailReset.getText().toString();

        if(email.equals(""))
        {
            Toast.makeText(forgotPasswordActivity.this,"Please enter a valid email address", Toast.LENGTH_LONG).show();
        }
        else
        {
            //This sends an email to your own email with link for a password reset.
            progressBar3.isShown();
            progressBar3.setVisibility(View.VISIBLE);
            firebaseAuth.sendPasswordResetEmail(email).addOnCompleteListener(new OnCompleteListener<Void>() {
                @Override
                public void onComplete(@NonNull Task<Void> task) {
                    if (task.isSuccessful())
                    {
                        Toast.makeText(forgotPasswordActivity.this,"Successfully sent a reset email",Toast.LENGTH_LONG).show();
                        etEmailReset.setText("");
                        progressBar3.setVisibility(View.INVISIBLE);

                    }
                    else
                    {
                        Toast.makeText(forgotPasswordActivity.this,task.getException().getMessage(),Toast.LENGTH_LONG).show();
                        progressBar3.setVisibility(View.INVISIBLE);
                    }
                }
            });
        }
    }
}
