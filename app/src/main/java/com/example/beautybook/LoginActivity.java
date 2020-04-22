package com.example.beautybook;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class LoginActivity extends AppCompatActivity {

    EditText etUsername, etPassword;
    TextView tvUsername, tvPassword;
    private String parentbd = "users";
    Button bLogin, bRegister;
    ProgressBar pbProgress;

    // [START declare_auth]
    private FirebaseAuth mAuth;
    // [END declare_auth]

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        // Initialize views
        bLogin = findViewById(R.id.bLogin);
        bRegister = findViewById(R.id.bRegister);
        etPassword = findViewById(R.id.etPassword);
        etUsername = findViewById(R.id.etUsername);
        pbProgress = findViewById(R.id.pbProgress);
        pbProgress.setVisibility(View.INVISIBLE);

        // Handle button clicks
        bRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Start RegisterActivity
                startActivity(new Intent(LoginActivity.this, RegisterActivity.class));
            }
        });

        bLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String uname = etUsername.getText().toString();
                String pass = etPassword.getText().toString();

                if (TextUtils.isEmpty(uname)) {
                    Toast.makeText(LoginActivity.this, "Username cannot be blank", Toast.LENGTH_SHORT).show();
                } else if (TextUtils.isEmpty(pass)) {
                    Toast.makeText(LoginActivity.this, "Password cannot be blank", Toast.LENGTH_SHORT).show();
                } else {
                    bLogin.setVisibility(View.INVISIBLE);
                    pbProgress.setVisibility(View.VISIBLE);

                    validateDetails(uname, pass);

                }
            }
        });
            // [START initialize_auth]
            // Initialize Firebase Auth
            mAuth = FirebaseAuth.getInstance();
            // [END initialize_auth]
    }

    private void validateDetails(final String uname, final String pass) {
        final DatabaseReference rootref;
        rootref = FirebaseDatabase.getInstance().getReference();
        rootref.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                if (dataSnapshot.child(parentbd).child(uname).exists()) {
                    User userdata = dataSnapshot.child(parentbd).child(uname).getValue(User.class);

                    if (userdata.getUsername().equals(uname)) {

                        if (userdata.getPassword().equals(pass)) {
                            Toast.makeText(LoginActivity.this, "Login successful", Toast.LENGTH_SHORT).show();

                            Intent intent = new Intent(LoginActivity.this, MainActivity.class);
                            startActivity(intent);
                            bLogin.setVisibility(View.VISIBLE);
                            pbProgress.setVisibility(View.INVISIBLE);
                        } else {
                            Toast.makeText(LoginActivity.this, "Incorrect password", Toast.LENGTH_SHORT).show();
                            pbProgress.setVisibility(View.INVISIBLE);
                            bLogin.setVisibility(View.VISIBLE);
                        }
                    }
                } else {
                    Toast.makeText(LoginActivity.this, "This user does not exist", Toast.LENGTH_SHORT).show();
                    pbProgress.setVisibility(View.INVISIBLE);
                    bLogin.setVisibility(View.VISIBLE);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }
}

