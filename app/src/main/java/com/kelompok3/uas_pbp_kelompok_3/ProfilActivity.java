package com.kelompok3.uas_pbp_kelompok_3;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.material.button.MaterialButton;

public class ProfilActivity extends AppCompatActivity {
    private EditText etEmail, etPassword, etName;
    private MaterialButton btnLogout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profil);

        etName = findViewById(R.id.et_name);
        etEmail = findViewById(R.id.et_email);
        etPassword = findViewById(R.id.et_password);

        btnLogout = findViewById(R.id.btnLogout);

        etName.setText("");
        etEmail.setText("");
        etPassword.setText("");

        btnLogout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(ProfilActivity.this,"Thank you for visiting our app!",Toast.LENGTH_SHORT).show();
                Intent moveLogin = new Intent(ProfilActivity.this, LoginActivity.class);
                startActivity(moveLogin);
            }
        });
    }
}