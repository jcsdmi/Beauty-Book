package com.example.beautybook;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.HashMap;

public class RegisterActivity extends AppCompatActivity {
    private Button bRegister, bCancel;
    private EditText etFirstName, etLastName, etEmail, etUserName, etPassword, etPasswordC;
    private ProgressBar pbProgress;
    private ProgressDialog loading;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        bRegister = findViewById(R.id.bRegister);
        bCancel = findViewById(R.id.bCancel);         // TODO: MAKE CANCEL FUNCTIONAL
        etFirstName = findViewById(R.id.etFirstName);
        etLastName = findViewById(R.id.etLastName);
        etEmail = findViewById(R.id.etEmail);
        etUserName = findViewById(R.id.etUsername);
        etPassword = findViewById(R.id.etPassword);
        etPasswordC = findViewById(R.id.etPasswordC);
        pbProgress = findViewById(R.id.pbProgress);
        pbProgress.setVisibility(View.INVISIBLE);

        bRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                createnow();
            }
        });
    }

    private void createnow() {
        String fname = etFirstName.getText().toString();
        String lname = etLastName.getText().toString();
        String email = etEmail.getText().toString();
        String uname = etUserName.getText().toString();
        String pass = etPassword.getText().toString();
        String conf = etPasswordC.getText().toString();
        boolean flag = false;


        if(TextUtils.isEmpty(fname)){
            Toast.makeText(this,"First name cannot be blank",Toast.LENGTH_SHORT).show();
            flag = true;
        }
        if(TextUtils.isEmpty(lname)){
            Toast.makeText(this,"Last name cannot be blank",Toast.LENGTH_SHORT).show();
            flag = true;
        }
        if(TextUtils.isEmpty(email)){
            Toast.makeText(this,"Email cannot be blank",Toast.LENGTH_SHORT).show();
            flag = true;
        }
        if(TextUtils.isEmpty(uname)){
            Toast.makeText(this,"Username cannot be blank",Toast.LENGTH_SHORT).show();
            flag = true;
        }
        if(TextUtils.isEmpty(pass)){
            Toast.makeText(this,"Password cannot be blank",Toast.LENGTH_SHORT).show();
            flag = true;
        }
        if(TextUtils.isEmpty(conf)){
            Toast.makeText(this,"Confirm password",Toast.LENGTH_SHORT).show();
            flag = true;
        }
        if(!pass.equals(conf)){
            Toast.makeText(this,"Passwords must match",Toast.LENGTH_SHORT).show();
            flag = true;
        }
        if(!flag){
            pbProgress.setVisibility(View.VISIBLE);
            bRegister.setVisibility(View.INVISIBLE);
            validateDetails(fname, lname, email, uname, pass);
        }
    }

    private void validateDetails(final String fname, final String lname, final String email, final String uname, final String pass) {
        final DatabaseReference rootref;
        rootref = FirebaseDatabase.getInstance().getReference();

        rootref.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                if(!(dataSnapshot.child("users").child(email).exists())){
                    HashMap<String,Object> userdatamap =  new HashMap<>();
                    userdatamap.put("Email",email);
                    userdatamap.put("First Name",fname);
                    userdatamap.put("Last Name",lname);
                    userdatamap.put("Username",uname);
                    userdatamap.put("Password",pass);

                    rootref.child("users").child(email).updateChildren(userdatamap)
                            .addOnCompleteListener(new OnCompleteListener<Void>() {
                                @Override
                                public void onComplete(@NonNull Task<Void> task) {
                                    if(task.isSuccessful()){
                                        Toast.makeText(RegisterActivity.this, "Account has been successfully created",Toast.LENGTH_SHORT).show();
                                        Intent intent = new Intent(RegisterActivity.this,LoginActivity.class);
                                        startActivity(intent);
                                        pbProgress.setVisibility(View.INVISIBLE);
                                        bRegister.setVisibility(View.VISIBLE);

                                    }
                                    else {
                                        loading.dismiss();
                                        loading.setTitle("Create Account");
                                        loading.setMessage("Please wait while we check your details");
                                        loading.setCanceledOnTouchOutside(true);
                                        loading.show();
                                        bRegister.setVisibility(View.VISIBLE);
                                        pbProgress.setVisibility(View.INVISIBLE);
                                    }

                                }
                            });

                }
                else{
                    Toast.makeText(RegisterActivity.this,"user already exist",Toast.LENGTH_SHORT).show();
                    bRegister.setVisibility(View.VISIBLE);
                    pbProgress.setVisibility(View.INVISIBLE);
                }
    }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }
}
