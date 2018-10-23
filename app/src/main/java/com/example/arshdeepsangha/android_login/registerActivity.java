package com.example.arshdeepsangha.android_login;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class registerActivity extends AppCompatActivity {

    private EditText etUsername,etPasswordRegister,etRePassword;
    private String username;
    private String confirmPassword;
    private String password;
    private Button btnRegisterAccount;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        etUsername = findViewById(R.id.etUsername);
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
        username = etUsername.getText().toString();
        password = etPasswordRegister.getText().toString();
        confirmPassword = etRePassword.getText().toString();
    }

    private void validate()
    {
        if(username.equals("") || password.equals("") || confirmPassword.equals(""))
        {
            Toast.makeText(this,"Please fill in the requirements", Toast.LENGTH_LONG).show();
        }
        else if(password != confirmPassword)
        {
            Toast.makeText(this,"Passwords do not match!", Toast.LENGTH_LONG).show();
        }
        else
        {

        }
    }
}
