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
import com.kelompok3.uas_pbp_kelompok_3.Preferences.UserPreferences;
import com.kelompok3.uas_pbp_kelompok_3.api.ApiClient;
import com.kelompok3.uas_pbp_kelompok_3.api.ApiInterface;
import com.kelompok3.uas_pbp_kelompok_3.models.User;
import com.kelompok3.uas_pbp_kelompok_3.models.UserResponse;

import org.json.JSONObject;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LoginActivity extends AppCompatActivity {
    private EditText etEmail, etPassword;
    private MaterialButton btnClear, btnLogin;
    private LinearLayout loginLayout;
    private TextView tvRegister;
    private ApiInterface apiService;
    private LinearLayout layoutLoading;
    private UserPreferences userPreferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        userPreferences = new UserPreferences(LoginActivity.this);

        apiService = ApiClient.getClient().create(ApiInterface.class);

        layoutLoading = findViewById(R.id.layout_loading);
        etEmail = findViewById(R.id.et_email);
        etPassword = findViewById(R.id.et_password);

        loginLayout = findViewById(R.id.loginLayout);

        btnClear = findViewById(R.id.btn_clear_text_login);
        btnLogin = findViewById(R.id.btn_login);

        tvRegister = findViewById(R.id.txtRegister);

        checkLogin();

        btnClear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                etEmail.setText("");
                etPassword.setText("");
            }
        });

        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                login();
            }
        });

        tvRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(LoginActivity.this, RegisterActivity.class));
                finish();
            }
        });
    }

    private void login() {
        setLoading(true);
        User newUser = new User(null,
                etEmail.getText().toString(),
                etPassword.getText().toString());

        Call<UserResponse> call = apiService.login(newUser);
        call.enqueue(new Callback<UserResponse>() {
            @Override
            public void onResponse(Call<UserResponse> call,
                                   Response<UserResponse> response) {
                if (response.isSuccessful()) {
                    Toast.makeText(LoginActivity.this,
                            response.body().getMessage(), Toast.LENGTH_SHORT).show();
                    Intent returnIntent = new Intent();
                    setResult(Activity.RESULT_OK, returnIntent);
                    Intent mainActivity = new Intent(LoginActivity.this, MainActivity.class);

                    userPreferences.setUser(
                            response.body().getUser().getId(),
                            response.body().getUser().getName(),
                            response.body().getToken_type() + " " + response.body().getAccess_token(),
                            etEmail.getText().toString(),
                            etPassword.getText().toString()
                    );

                    startActivity(mainActivity);
                    finish();
                } else {
                    try {
                        JSONObject jObjError = new
                                JSONObject(response.errorBody().string());
                        Toast.makeText(LoginActivity.this,
                                jObjError.getString("message"),
                                Toast.LENGTH_SHORT).show();
                    } catch (Exception e) {
                        Toast.makeText(LoginActivity.this,
                                e.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                }
                setLoading(false);
            }
            @Override
            public void onFailure(Call<UserResponse> call, Throwable t) {
                Toast.makeText(LoginActivity.this,
                        t.getMessage(), Toast.LENGTH_SHORT).show();
                setLoading(false);
            }
        });
    }

    private void checkLogin(){
        if(userPreferences.checkLogin()){
            etEmail.setText(userPreferences.getUserLogin_email());
            etPassword.setText(userPreferences.getUserLogin_password());
            login();
        }
    }

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