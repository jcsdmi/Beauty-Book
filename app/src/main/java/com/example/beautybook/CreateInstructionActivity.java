package com.example.beautybook;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

public class CreateInstructionActivity extends AppCompatActivity {
    public static final String TAG = "CreateInstructActivity";
    private Button btnAddInstruct;
    private Button btnCancelInstruct;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_instruct);
        btnCancelInstruct = findViewById(R.id.btnCancel);
        btnCancelInstruct.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.i(TAG, "onClick Cancel Instruct");
                goGuideActivity();
            }
        });
        btnAddInstruct = findViewById(R.id.btnAddInstruct);
        btnAddInstruct.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.i(TAG, "onClick Instruction Added");
                goGuideActivity();
            }
        });

    }
    private void goGuideActivity() {
        Intent intent = new Intent(this, CreateGuideActivity.class);
        startActivity(intent);
        finish();
    }
}
