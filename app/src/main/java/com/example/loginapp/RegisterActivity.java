package com.example.loginapp;

import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Calendar;

public class RegisterActivity extends AppCompatActivity {

    DatabaseHelper databaseHelper;
    EditText txtUsername, txtEmail, txtPassword, txtConfirmPassword;
    RadioGroup txtGender;
    String gender;
    //RadioButton maleBtn, femaleBtn;
    RadioButton select;
    Button registerBtn;
    TextView loginLink;
    TextView txtBirthDate;
    Calendar calendar;
    int day, month, year;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        databaseHelper = new DatabaseHelper(this);
        init();
        addData();
    }

    public void init() {
        txtUsername = findViewById(R.id.txtUsername);
        txtEmail = findViewById(R.id.txtEmail);
        txtPassword = findViewById(R.id.txtPassword);
        txtConfirmPassword = findViewById(R.id.txtConfirmPassword);
        txtGender = findViewById(R.id.txtGender);
        registerBtn = findViewById(R.id.registerBtn);
        loginLink = findViewById(R.id.loginLink);
        txtBirthDate = findViewById(R.id.txtBirthDate);
    }

    public void addData() {
        registerBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String name = txtUsername.getText().toString();
                String email = txtEmail.getText().toString();
                String password = txtPassword.getText().toString();
                String confirmPassword = txtConfirmPassword.getText().toString();
                String birthday = txtBirthDate.getText().toString();

                // for gender selection
                select = findViewById(txtGender.getCheckedRadioButtonId());
                gender = select.getText().toString().trim();

                // for Birth date selection
                calendar = Calendar.getInstance();
                day = calendar.get(Calendar.DAY_OF_MONTH);
                month = calendar.get(Calendar.MONTH);
                year = calendar.get(Calendar.YEAR);

                month = month + 1;

                txtBirthDate.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        DatePickerDialog datePickerDialog = new DatePickerDialog(RegisterActivity.this, new DatePickerDialog.OnDateSetListener() {
                            @Override
                            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                                month = month + 1;
                                txtBirthDate.setText(dayOfMonth + "/" + month + "/" + year);
                            }
                        }, year, month, day);
                        datePickerDialog.show();
                    }
                });

                if (TextUtils.isEmpty(name)) {
                    Toast.makeText(RegisterActivity.this, "Please enter the name ", Toast.LENGTH_SHORT).show();
                } else if (TextUtils.isEmpty(email)) {
                    Toast.makeText(RegisterActivity.this, "Please enter the email-id ", Toast.LENGTH_SHORT).show();
                } else if (TextUtils.isEmpty(password)) {
                    Toast.makeText(RegisterActivity.this, "Please enter the password ", Toast.LENGTH_SHORT).show();
                } else if (!password.equals(confirmPassword)) {
                    Toast.makeText(RegisterActivity.this, "Password not match", Toast.LENGTH_SHORT).show();
                } else if (TextUtils.isEmpty(birthday)) {
                    Toast.makeText(RegisterActivity.this, "Please select your birth date", Toast.LENGTH_SHORT).show();
                } else {
                    long data = databaseHelper.insertData(name, email, password, birthday, gender);
                    if (data > 0) {
                        Toast.makeText(RegisterActivity.this, "You are registered", Toast.LENGTH_SHORT).show();
                    } else {
                        Toast.makeText(RegisterActivity.this, "Registration error", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });

        loginLink.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(RegisterActivity.this, LoginActivity.class);
                startActivity(intent);
                finish();
            }
        });
    }
}
