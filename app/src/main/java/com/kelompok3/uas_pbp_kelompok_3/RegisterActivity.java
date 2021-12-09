package com.kelompok3.uas_pbp_kelompok_3;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.button.MaterialButton;
import com.kelompok3.uas_pbp_kelompok_3.api.ApiInterface;
import com.kelompok3.uas_pbp_kelompok_3.models.User;
import com.kelompok3.uas_pbp_kelompok_3.models.UserResponse;

import org.json.JSONObject;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class RegisterActivity extends AppCompatActivity {
    private EditText etEmail, etPassword, etName;
    private MaterialButton btnClear, btnRegister;
    private LinearLayout registerLayout;
    private TextView tvLogin;
    private LinearLayout layoutLoading;


    private ApiInterface apiService;

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

        layoutLoading = findViewById(R.id.layout_loading);

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
                createUser();
                Toast.makeText(RegisterActivity.this,"Success Register!",Toast.LENGTH_SHORT).show();
                startActivity(new Intent(RegisterActivity.this, LoginActivity.class));
                finish();
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

    private void createUser() {
        setLoading(true);
        User user = new User(
                etName.getText().toString(),
                etEmail.getText().toString(),
                etPassword.getText().toString());
        Call<UserResponse> call = apiService.register(user);
        call.enqueue(new Callback<UserResponse>() {
            @Override
            public void onResponse(Call<UserResponse> call,
                                   Response<UserResponse> response) {
                if (response.isSuccessful()) {
                    Toast.makeText(RegisterActivity.this,
                            response.body().getMessage(), Toast.LENGTH_SHORT).show();
                    Intent returnIntent = new Intent();
                    setResult(Activity.RESULT_OK, returnIntent);
                    finish();
                } else {
                    try {
                        JSONObject jObjError = new
                                JSONObject(response.errorBody().string());
                        Toast.makeText(RegisterActivity.this,
                                jObjError.getString("message"),
                                Toast.LENGTH_SHORT).show();
                    } catch (Exception e) {
                        Toast.makeText(RegisterActivity.this,
                                e.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                }
                setLoading(false);
            }
            @Override
            public void onFailure(Call<UserResponse> call, Throwable t) {
                Toast.makeText(RegisterActivity.this,
                        t.getMessage(), Toast.LENGTH_SHORT).show();
                setLoading(false);
            }
        });
    }

    // Fungsi untuk menampilkan layout loading
    private void setLoading(boolean isLoading) {
        if (isLoading) {
            getWindow().setFlags(WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE,
                    WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE);
            layoutLoading.setVisibility(View.VISIBLE);
        } else {
            getWindow().clearFlags(WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE);
            layoutLoading.setVisibility(View.INVISIBLE);
        }
    }
}