package com.example.arshdeepsangha.android_login;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private EditText etName;
    private EditText etPassword;
    private Button btnLogin;
    private Button btnRegister;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        etName = findViewById(R.id.etName);
        etPassword = findViewById(R.id.etPassword);
        btnLogin = findViewById(R.id.btnLogin);
        btnRegister = findViewById(R.id.btnRegister);

        btnLogin.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                validate();
            }
        });
    }

    private void validate()
    {
        if (etName.getText().toString().equals("") || etPassword.getText().toString().equals(""))
        {
            Toast.makeText(this,"Please fill in the requirements", Toast.LENGTH_LONG).show();
        }
        else
        {
            Intent intent = new Intent(this,successfulActivity.class);
            startActivity(intent);
        }
    }
}
