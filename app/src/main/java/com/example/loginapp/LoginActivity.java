package com.example.loginapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class LoginActivity extends AppCompatActivity {

    EditText txtEmail, txtPassword;
    Button loginBtn;
    TextView registerLink;
    DatabaseHelper databaseHelper;

    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        txtEmail = findViewById(R.id.txtEmail);
        txtPassword = findViewById(R.id.txtPassword);
        loginBtn = findViewById(R.id.loginBtn);
        registerLink = findViewById(R.id.registerLink);

        databaseHelper = new DatabaseHelper(this);

        loginBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String email = txtEmail.getText().toString();
                String password = txtPassword.getText().toString();
                boolean res = databaseHelper.checkUser(email, password);

                if (TextUtils.isEmpty(email)) {
                    Toast.makeText(LoginActivity.this, "Please enter the email-id ", Toast.LENGTH_SHORT).show();
                } else if (TextUtils.isEmpty(password)) {
                    Toast.makeText(LoginActivity.this, "Please enter the password ", Toast.LENGTH_SHORT).show();
                } else if (res) {
                    Intent intent = new Intent(LoginActivity.this, ViewActivity.class);
                    intent.putExtra("data", email);
                    startActivity(intent);
                    finish();
                } else {
                    Toast.makeText(LoginActivity.this, "Not Registered", Toast.LENGTH_SHORT).show();
                }
            }
        });

        registerLink.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(LoginActivity.this, RegisterActivity.class);
                startActivity(intent);
            }
        });
    }
}
