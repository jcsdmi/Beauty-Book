package com.example.beautybook;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

public class CreateGuideActivity extends AppCompatActivity {
    public static final String TAG = "CreateGuideActivity";
    private Button btnAddInstructions;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_guide);
        btnAddInstructions = findViewById(R.id.btnAddInstruct);
        btnAddInstructions.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.i(TAG, "onClick Add Instruct");
                goInstructActivity();
            }
        }

        );
    }

    private void goInstructActivity() {
        Intent intent = new Intent(this, CreateInstructionActivity.class);
        startActivity(intent);
        finish();
    }
}
