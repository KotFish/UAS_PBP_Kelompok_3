package com.kelompok3.uas_pbp_kelompok_3;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.LinearLayout;
import android.widget.SearchView;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.kelompok3.uas_pbp_kelompok_3.Preferences.UserPreferences;
import com.kelompok3.uas_pbp_kelompok_3.adapters.RentalAdapter;
import com.kelompok3.uas_pbp_kelompok_3.api.ApiClient;
import com.kelompok3.uas_pbp_kelompok_3.api.ApiInterface;
import com.kelompok3.uas_pbp_kelompok_3.models.RentalResponse;

import org.json.JSONObject;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class RentalActivity extends AppCompatActivity {

    public static final int LAUNCH_ADD_ACTIVITY = 123;
    private SwipeRefreshLayout srRental;
    private RentalAdapter adapter;
    private ApiInterface apiService;
    private SearchView svRental;
    private LinearLayout layoutLoading;
    private UserPreferences userPreferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rental);
        userPreferences = new UserPreferences(RentalActivity.this);

        apiService = ApiClient.getClient().create(ApiInterface.class);
        layoutLoading = findViewById(R.id.layout_loading);
        srRental = findViewById(R.id.sr_rental);
        svRental = findViewById(R.id.sv_rental);
        srRental.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                getAllRental();
            }
        });
        svRental.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String s) {
                return false;
            }
            @Override
            public boolean onQueryTextChange(String s) {
                adapter.getFilter().filter(s);
                return false;
            }
        });
        FloatingActionButton fabAdd = findViewById(R.id.fab_add);
        fabAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(RentalActivity.this, AddEditRentalActivity.class);
                startActivityForResult(i, LAUNCH_ADD_ACTIVITY);
            }
        });
        RecyclerView rvRental = findViewById(R.id.rv_rental);
        adapter = new RentalAdapter(new ArrayList<>(), this);
        rvRental.setLayoutManager(new LinearLayoutManager(RentalActivity.this, LinearLayoutManager.VERTICAL, false));
        rvRental.setAdapter(adapter);
        getAllRental();
    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent
            data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == LAUNCH_ADD_ACTIVITY && resultCode == Activity.RESULT_OK)
            getAllRental();
    }

    private void getAllRental() {
        Call<RentalResponse> call = apiService.getAllRental(userPreferences.getUserLogin_token());
        srRental.setRefreshing(true);
        call.enqueue(new Callback<RentalResponse>() {
            @Override
            public void onResponse(Call<RentalResponse> call,
                                   Response<RentalResponse> response) {
                if (response.isSuccessful()) {
                    adapter.setRentalList(response.body().getRentalList());
                    adapter.getFilter().filter(svRental.getQuery());
                } else {
                    try {
                        JSONObject jObjError = new
                                JSONObject(response.errorBody().string());
                        Toast.makeText(RentalActivity.this,
                                jObjError.getString("message"),
                                Toast.LENGTH_SHORT).show();
                    } catch (Exception e) {
                        Toast.makeText(RentalActivity.this,
                                e.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                }
                srRental.setRefreshing(false);
            }
            @Override
            public void onFailure(Call<RentalResponse> call, Throwable t) {
                Toast.makeText(RentalActivity.this, "Network error",
                        Toast.LENGTH_SHORT).show();
                srRental.setRefreshing(false);
            }
        });
    }

    public void deleteRental(long id) {
        Call<RentalResponse> call = apiService.deleteRental(userPreferences.getUserLogin_token(), id);
        setLoading(true);
        call.enqueue(new Callback<RentalResponse>() {
            @Override
            public void onResponse(Call<RentalResponse> call,
                                   Response<RentalResponse> response) {
                if (response.isSuccessful() && response.body() != null) {
                    Toast.makeText(RentalActivity.this,
                            response.body().getMessage(), Toast.LENGTH_SHORT).show();
                    getAllRental();
                } else {
                    try {
                        JSONObject jObjError = new
                                JSONObject(response.errorBody().string());
                        Toast.makeText(RentalActivity.this,
                                jObjError.getString("message"),
                                Toast.LENGTH_SHORT).show();
                    } catch (Exception e) {
                        Toast.makeText(RentalActivity.this,
                                e.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                }
                setLoading(false);
            }
            @Override
            public void onFailure(Call<RentalResponse> call, Throwable t) {
                Toast.makeText(RentalActivity.this, "Network error",
                        Toast.LENGTH_SHORT).show();
                setLoading(false);
            }
        });
    }

    // Fungsi untuk menampilkan layout loading
    private void setLoading(boolean isLoading) {
        if (isLoading) {
            getWindow().setFlags(WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE, WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE);
            layoutLoading.setVisibility(View.VISIBLE);
        } else {
            getWindow().clearFlags(WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE);
            layoutLoading.setVisibility(View.GONE);
        }
    }
}