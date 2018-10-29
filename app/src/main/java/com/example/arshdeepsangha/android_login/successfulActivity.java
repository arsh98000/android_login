package com.example.arshdeepsangha.android_login;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class successfulActivity extends AppCompatActivity
{
    private TextView txtEmail;
    private Button btnSignout;
    private Button btnDeleteAccount;
    private FirebaseAuth firebaseAuth;
    private ProgressBar progressBar;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_successful);

        txtEmail = findViewById(R.id.txtEmail);
        btnSignout = findViewById(R.id.btnSignout);
        btnDeleteAccount = findViewById(R.id.btnDeleteAccount);
        progressBar = findViewById(R.id.progressBar4);

        firebaseAuth = FirebaseAuth.getInstance();

        final FirebaseUser user = firebaseAuth.getCurrentUser();

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

        btnDeleteAccount.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {
                //Alert dialog to confirm the user's decision
                AlertDialog.Builder dialog = new AlertDialog.Builder(successfulActivity.this);
                dialog.setTitle("Confirmation");
                dialog.setMessage("Are you sure you want to delete your Account?");

                //Set if user selects yes
                dialog.setPositiveButton("Yes", new DialogInterface.OnClickListener() {

                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                        progressBar.setVisibility(View.VISIBLE);
                        progressBar.isShown();
                        user.delete().addOnCompleteListener(new OnCompleteListener<Void>() {
                            @Override
                            public void onComplete(@NonNull Task<Void> task)
                            {
                                //if successful , delete user
                                if (task.isSuccessful())
                                {
                                    Toast.makeText(successfulActivity.this, "Account Deleted", Toast.LENGTH_SHORT).show();
                                    Intent intent = new Intent(successfulActivity.this,MainActivity.class);
                                    startActivity(intent);
                                    finish();
                                }
                                //if problem occurs , cancel
                                else
                                {
                                    progressBar.setVisibility(View.INVISIBLE);
                                    Toast.makeText(successfulActivity.this, "We are unable to Delete account at the moment", Toast.LENGTH_SHORT).show();
                                }
                            }
                        });
                    }
                });

                //Set if user selects No
                dialog.setNegativeButton("No", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                });

                //Dialog box to show up
                AlertDialog alertDialog = dialog.create();
                alertDialog.show();
            }
        });
    }
}
