package com.example.loginapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class ViewActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view);

        Button backBtn = findViewById(R.id.backBtn);
        TextView txtViewResult = findViewById(R.id.txtViewResult);
        //show data
        String data = getIntent().getStringExtra("data");
        txtViewResult.setText("Hello " + data);

        backBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ViewActivity.this, LoginActivity.class);
                startActivity(intent);
                finish();
            }
        });
    }
}
