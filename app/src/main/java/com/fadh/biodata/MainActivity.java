package com.fadh.biodata;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
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

public class MainActivity extends AppCompatActivity {
    EditText username , password;
    Button login , register;
    private FirebaseAuth mAuth;
    FirebaseUser currenUser;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
      super.onCreate(savedInstanceState);
      setContentView(R.layout.activity_main);
      username = findViewById(R.id.editTextTextEmailAddress);
      password = findViewById(R.id.editTextTextPassword);
      login = findViewById(R.id.btnLogin);
      register = findViewById(R.id.btnRegister);
      mAuth = FirebaseAuth.getInstance();
      currenUser = mAuth.getCurrentUser();

      register.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View view) {
          startActivity(new Intent(MainActivity.this,Register.class));
        }
      });
    }
    public void Login(View view){
      mAuth.signInWithEmailAndPassword(username.getText().toString(),password.getText().toString()).addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
        @Override
        public void onComplete(@NonNull Task<AuthResult> task) {
          if (task.isSuccessful()){
            FirebaseUser user = mAuth.getCurrentUser();
            String pesan = "Isinya adalah" + username.getText().toString()+"passwordnya adalah" + password.getText().toString();
            Toast.makeText(MainActivity.this, pesan , Toast.LENGTH_LONG).show();
              Intent nextScreen = new Intent(MainActivity.this,MainMenu.class);
              nextScreen.putExtra("username" , username.getText().toString());
              nextScreen.putExtra("password" , password.getText().toString());
              startActivity(nextScreen);
          }else{
            Toast.makeText(MainActivity.this, "Auth Failde" , Toast.LENGTH_SHORT).show();
          }
        }
      });
    }
}