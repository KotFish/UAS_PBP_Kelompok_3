package com.kelompok3.uas_pbp_kelompok_3;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import android.view.View;
import android.view.WindowManager;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.kelompok3.uas_pbp_kelompok_3.Preferences.UserPreferences;
import com.kelompok3.uas_pbp_kelompok_3.api.ApiClient;
import com.kelompok3.uas_pbp_kelompok_3.api.ApiInterface;
import com.kelompok3.uas_pbp_kelompok_3.models.Rental;
import com.kelompok3.uas_pbp_kelompok_3.models.RentalResponse;

import org.json.JSONObject;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class AddEditRentalActivity extends AppCompatActivity {
    private static final String[] STATUS_LIST = new String[]{"Tersedia", "Tidak Tersedia"};

    private ApiInterface apiService;
    private EditText etNamaKendaraan, etJenisKendaraan, etBiayaSewa, etNoPlat;
    private AutoCompleteTextView edStatus;
    private LinearLayout layoutLoading;
    private Boolean status = false;
    private UserPreferences userPreferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_edit_rental);
        userPreferences = new UserPreferences(AddEditRentalActivity.this);

        apiService = ApiClient.getClient().create(ApiInterface.class);
        etNoPlat = findViewById(R.id.et_noPlat);
        etNamaKendaraan = findViewById(R.id.et_namaKendaraan);
        etJenisKendaraan = findViewById(R.id.et_jenisKendaraan);
        etBiayaSewa = findViewById(R.id.et_biayaSewa);
        edStatus = findViewById(R.id.ed_status);
        layoutLoading = findViewById(R.id.layout_loading);
        ArrayAdapter<String> adapterStatus = new ArrayAdapter<>(this, R.layout.item_list_rental, STATUS_LIST);
        edStatus.setAdapter(adapterStatus);
        Button btnCancel = findViewById(R.id.btn_cancel);

        btnCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
        Button btnSave = findViewById(R.id.btn_save);
        TextView tvTitle = findViewById(R.id.tv_title_rental);
        long id = getIntent().getLongExtra("id", -1);
        if (id == -1) {
            tvTitle.setText(R.string.tambah_rental);
            btnSave.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    createRental();
                }
            });
        } else {
            tvTitle.setText(R.string.edit_kendaraan);
            getRentalById(id);
            btnSave.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    updateRental(id);
                }
            });
        }
    }
    private void getRentalById(long id) {
        setLoading(true);
        Call<RentalResponse> call = apiService.getRentalById(userPreferences.getUserLogin_token(), id);
        call.enqueue(new Callback<RentalResponse>() {
            @Override
            public void onResponse(Call<RentalResponse> call, Response<RentalResponse> response) {
                if (response.isSuccessful()) {
                    Rental rental = response.body().getRental();
                    etNoPlat.setText(rental.getNo_plat());
                    etNamaKendaraan.setText(rental.getNama_kendaraan());
                    etJenisKendaraan.setText(rental.getJenis_kendaraan());
                    etBiayaSewa.setText(rental.getBiaya_penyewaan());
                    if(rental.getStatus() == true)
                        edStatus.setText("Tersedia");
                    else
                        edStatus.setText("Tidak Tersedia");
                } else {
                    try {
                        JSONObject jObjError = new JSONObject(response.errorBody().string());
                        Toast.makeText(AddEditRentalActivity.this, jObjError.getString("message"),
                                Toast.LENGTH_SHORT).show();
                    } catch (Exception e) {
                        Toast.makeText(AddEditRentalActivity.this,
                                e.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                }
                setLoading(false);
            }
            @Override
            public void onFailure(Call<RentalResponse> call, Throwable t) {
                Toast.makeText(AddEditRentalActivity.this,
                        "Network error", Toast.LENGTH_SHORT).show();
                setLoading(false);
            }
        });
    }
    private void createRental() {
        setLoading(true);
        if(edStatus.getText().toString().trim().equals("Tersedia"))
            status = true;
        else
            status = false;

        Rental rental = new Rental(
                etNoPlat.getText().toString(),
                etNamaKendaraan.getText().toString(),
                etJenisKendaraan.getText().toString(),
                etBiayaSewa.getText().toString(),
                status);
        Call<RentalResponse> call = apiService.createRental(userPreferences.getUserLogin_token(), rental);
        call.enqueue(new Callback<RentalResponse>() {
            @Override
            public void onResponse(Call<RentalResponse> call,
                                   Response<RentalResponse> response) {
                if (response.isSuccessful()) {
                    Toast.makeText(AddEditRentalActivity.this,
                            response.body().getMessage(), Toast.LENGTH_SHORT).show();
                    Intent returnIntent = new Intent();
                    setResult(Activity.RESULT_OK, returnIntent);
                    finish();
                } else {
                    try {
                        JSONObject jObjError = new
                                JSONObject(response.errorBody().string());
                        Toast.makeText(AddEditRentalActivity.this,
                                jObjError.getString("message"),
                                Toast.LENGTH_SHORT).show();
                    } catch (Exception e) {
                        Toast.makeText(AddEditRentalActivity.this,
                                e.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                }
                setLoading(false);
            }
            @Override
            public void onFailure(Call<RentalResponse> call, Throwable t) {
                Toast.makeText(AddEditRentalActivity.this,
                        t.getMessage(), Toast.LENGTH_SHORT).show();
                setLoading(false);
            }
        });
    }

    private void updateRental(long id) {
        setLoading(true);
        if(edStatus.getText().toString().trim().equals("Tersedia"))
            status = true;
        else
            status = false;
        Rental rental = new Rental(
                etNoPlat.getText().toString(),
                etNamaKendaraan.getText().toString(),
                etJenisKendaraan.getText().toString(),
                etBiayaSewa.getText().toString(),
                status);
        Call<RentalResponse> call = apiService.updateRental(userPreferences.getUserLogin_token(), id, rental);
        call.enqueue(new Callback<RentalResponse>() {
            @Override
            public void onResponse(Call<RentalResponse> call,
                                   Response<RentalResponse> response) {
                if (response.isSuccessful()) {
                    Toast.makeText(AddEditRentalActivity.this,
                            response.body().getMessage(), Toast.LENGTH_SHORT).show();
                    Intent returnIntent = new Intent();
                    setResult(Activity.RESULT_OK, returnIntent);
                    finish();
                } else {
                    try {
                        JSONObject jObjError = new
                                JSONObject(response.errorBody().string());
                        Toast.makeText(AddEditRentalActivity.this,
                                jObjError.getString("message"),
                                Toast.LENGTH_SHORT).show();
                    } catch (Exception e) {
                        Toast.makeText(AddEditRentalActivity.this,
                                e.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                }
                setLoading(false);
            }
            @Override
            public void onFailure(Call<RentalResponse> call, Throwable t) {
                Toast.makeText(AddEditRentalActivity.this,
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