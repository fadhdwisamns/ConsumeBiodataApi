package com.fadh.biodata;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.FirebaseApp;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class Register extends AppCompatActivity {

    private FirebaseAuth mAuth;
    private EditText txtUsername, txtPassword;
    Button btnSimpan;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        mAuth = FirebaseAuth.getInstance();
        txtUsername = findViewById(R.id.txtUsername);
        txtPassword = findViewById(R.id.edrpassword);
        btnSimpan = findViewById(R.id.btnsimpan);
        FirebaseApp.initializeApp(this);

        btnSimpan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mAuth.createUserWithEmailAndPassword(txtUsername.getText().toString(),txtPassword.getText().toString()).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
//                            Toast.makeText(Register.this, "Registered successfully", Toast.LENGTH_LONG).show();
//                            Intent intent = new Intent(Register.this, MainActivity.class);
//                            startActivity(intent);
                            FirebaseUser user = mAuth.getCurrentUser();
                            finish();
                        } else {
                            Toast.makeText(Register.this, "Auth Failed" ,Toast.LENGTH_SHORT).show();
                        }
                    }
                    });
                }
            });
        }
    }
