package com.kelompok3.uas_pbp_kelompok_3;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.button.MaterialButton;

public class RegisterActivity extends AppCompatActivity {
    private EditText etUsername, etPassword;
    private MaterialButton btnClear, btnRegister;
    private LinearLayout loginLayout;
    private TextView tvLogin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        etUsername = findViewById(R.id.et_username);
        etPassword = findViewById(R.id.et_password);

        loginLayout = findViewById(R.id.loginLayout);

        btnClear = findViewById(R.id.btn_clear_text);
        btnRegister = findViewById(R.id.btn_register);

        tvLogin = findViewById(R.id.txtLogin);

        btnClear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                etUsername.setText("");
                etPassword.setText("");
            }
        });

        btnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                if(etUsername.getText().toString().trim().equals("test")
//                        && etPassword.getText().toString().trim().equals("123")) {
//                    startActivity(new Intent(LoginActivity.this, MainActivity.class));
//                    finish();
//                }else {
//                    Toast.makeText(LoginActivity.this,"Username Atau Password Salah",Toast.LENGTH_SHORT).show();
//                }
            }
        });

        tvLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(RegisterActivity.this, LoginActivity.class));
                finish();
            }
        });
    }
}