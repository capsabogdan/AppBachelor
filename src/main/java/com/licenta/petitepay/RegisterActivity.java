package com.licenta.petitepay;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;


public class RegisterActivity extends AppCompatActivity {

    private EditText mUsername, mEmail, mPassword;
    Button btnRegister;
    ProgressBar progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        mUsername = findViewById(R.id.txtNewName);
        mPassword = findViewById(R.id.txt_password);
        mEmail = findViewById(R.id.txtNewEmail);
        btnRegister = findViewById(R.id.btn_register);
        progressBar = findViewById(R.id.progress_bar);
        emptyInputFields();

        btnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SharedPreferences sharedPreferences = getSharedPreferences("MYPREFS", MODE_PRIVATE);

                String username = mUsername.getText().toString().trim();
                String password = mPassword.getText().toString().trim();
                String email = mEmail.getText().toString().trim();
                validateRegistrationData(username, password, email);

                SharedPreferences.Editor editor = sharedPreferences.edit();
                editor.putString(username, username);
                editor.commit();
                editor.putString(username + password, password);
                editor.commit();
                startActivity(new Intent(getApplicationContext(), LoginActivity.class));
                Toast.makeText(getApplicationContext(),"Autentificarea a avut loc cu succes", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void emptyInputFields() {
        if(!mPassword.getText().equals("")) {
            mPassword.setText("");
        }
        if(!mUsername.getText().equals("")) {
            mUsername.setText("");
        }
        if(!mEmail.getText().equals("")) {
            mEmail.setText("");
        }
    }

    private void validateRegistrationData(String username, String password, String email) {
        if (TextUtils.isEmpty(email)) {
            mEmail.setError("Email field can not be empty");
        }
        if (TextUtils.isEmpty(password)) {
            mPassword.setError("Password field can not be empty");
        }
        if (TextUtils.isEmpty(username)) {
            mUsername.setError("Username field can not be empty");
        }
        if (password.length() < 8) {
            mPassword.setError("Password must have more than 8 characters");
        }
    }
}


