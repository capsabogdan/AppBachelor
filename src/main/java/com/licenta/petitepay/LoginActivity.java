package com.licenta.petitepay;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;


public class LoginActivity extends AppCompatActivity {

    EditText mEmailAddess, mPassword, mUsername;
    Button btnLogin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        mUsername = findViewById(R.id.txt_username);
        mEmailAddess = findViewById(R.id.txt_username);
        mPassword = findViewById(R.id.txt_password);
        btnLogin = findViewById(R.id.btn_login);
        Button btnRegister = findViewById(R.id.btn_register);

        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String username = mUsername.getText().toString();
                String password = mPassword.getText().toString();
                validateRegistrationData(username, password);
                SharedPreferences sharedPreferences = getSharedPreferences("MYPREFS", MODE_PRIVATE);
                String userNameStored = sharedPreferences.getString(username, "");
                String passwordStored = sharedPreferences.getString(username + password, "");
                if (username.equals("") || password.equals("")) {
                    Toast.makeText(getApplicationContext(), "Parola sau username gresit", Toast.LENGTH_SHORT).show();
                } else {
                    if (username.equals(userNameStored) && (password.equals(passwordStored))) {
                        startActivity(new Intent(getApplicationContext(), MainActivity.class));
                        emptyInputFields();
                    } else {
                        Toast.makeText(getApplicationContext(), "Parola sau username gresit", Toast.LENGTH_SHORT).show();
                    }
                }

            }
        });

        btnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(), RegisterActivity.class));
            }
        });
    }

    private void validateRegistrationData(String username, String password) {
        if (TextUtils.isEmpty(username)) {
            mUsername.setError("email field can not be empty");
        }
        if (TextUtils.isEmpty(password)) {
            mPassword.setError("Password field can not be empty");
        }
        if (password.length() < 8) {
            mPassword.setError("Password must have more than 8 characters");
        }
    }

    private void emptyInputFields() {
        if (!mUsername.getText().equals("")) {
            mUsername.setText("");
        }
        if (!mPassword.getText().equals("")) {
            mPassword.setText("");
        }
    }
}
