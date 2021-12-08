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
    private EditText etEmail, etPassword, etName;
    private MaterialButton btnClear, btnRegister;
    private LinearLayout registerLayout;
    private TextView tvLogin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        etEmail = findViewById(R.id.et_email);
        etPassword = findViewById(R.id.et_password);

        registerLayout = findViewById(R.id.registerLayout);

        btnClear = findViewById(R.id.btn_clear_text);
        btnRegister = findViewById(R.id.btn_register);

        tvLogin = findViewById(R.id.txtLogin);

        btnClear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                etName.setText("");
                etEmail.setText("");
                etPassword.setText("");
            }
        });

        btnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String regexAlpha = "[a-zA-Z]+";
                String regexAlphaNum = "^[A-Za-z0-9]+$";
                String regexEmail = "[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+";
//                if(etUsername.getText().toString().trim().equals("test")
//                        && etPassword.getText().toString().trim().equals("123")) {
//                    startActivity(new Intent(LoginActivity.this, MainActivity.class));
//                    finish();
//                }else {
//                    Toast.makeText(LoginActivity.this,"Username Atau Password Salah",Toast.LENGTH_SHORT).show();
//                }
                if(etName.getText().toString().trim().isEmpty()){
                    Toast.makeText(RegisterActivity.this,"Name Tidak Boleh Kosong!",Toast.LENGTH_SHORT).show();
                }else if(!(etName.getText().toString().trim().matches(regexAlpha))){
                    Toast.makeText(RegisterActivity.this,"Nama Tidak Boleh Mengandung Angka dan Simbol",Toast.LENGTH_SHORT).show();
                }

                if(etEmail.getText().toString().trim().isEmpty()){
                    Toast.makeText(RegisterActivity.this,"Email Tidak Boleh Kosong!",Toast.LENGTH_SHORT).show();
                }else if(!(etEmail.getText().toString().trim().matches(regexEmail))){
                    Toast.makeText(RegisterActivity.this,"Format Email Salah",Toast.LENGTH_SHORT).show();
                }

                if(etPassword.getText().toString().trim().isEmpty()){
                    Toast.makeText(RegisterActivity.this,"Password Tidak Boleh Kosong!",Toast.LENGTH_SHORT).show();
                }else if(!(etPassword.getText().toString().trim().matches(regexAlphaNum))){
                    Toast.makeText(RegisterActivity.this,"Password Harus Mengandung Huruf Besar, kecil, angka, dan simbol",Toast.LENGTH_SHORT).show();
                }else if(etPassword.getText().toString().trim().length() > 6){
                    Toast.makeText(RegisterActivity.this,"Password Tidak Boleh Kurang Dari 6 Digit",Toast.LENGTH_SHORT).show();
                }
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