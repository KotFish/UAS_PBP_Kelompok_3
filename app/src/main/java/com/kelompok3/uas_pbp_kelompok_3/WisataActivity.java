package com.kelompok3.uas_pbp_kelompok_3;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.LinearLayout;
import android.widget.SearchView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.kelompok3.uas_pbp_kelompok_3.adapters.RentalAdapter;
import com.kelompok3.uas_pbp_kelompok_3.adapters.WisataAdapter;
import com.kelompok3.uas_pbp_kelompok_3.api.ApiClient;
import com.kelompok3.uas_pbp_kelompok_3.api.ApiInterface;
import com.kelompok3.uas_pbp_kelompok_3.models.RentalResponse;
import com.kelompok3.uas_pbp_kelompok_3.models.WisataResponse;

import org.json.JSONObject;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.http.Headers;

public class WisataActivity extends AppCompatActivity {
    public static final int LAUNCH_ADD_ACTIVITY = 123;
    private SwipeRefreshLayout srWisata;
    private WisataAdapter adapter;
    private ApiInterface apiService;
    private SearchView svWisata;
    private LinearLayout layoutLoading;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_wisata);
        apiService = ApiClient.getClient().create(ApiInterface.class);
        layoutLoading = findViewById(R.id.layout_loading);
        srWisata = findViewById(R.id.sr_wisata);
        svWisata = findViewById(R.id.sv_wisata);
        srWisata.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                getAllWisata();
            }
        });
        svWisata.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
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
        FloatingActionButton fabAdd = findViewById(R.id.fab_add_wisata);
        fabAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(WisataActivity.this, AddEditWisataActivity.class);
                startActivityForResult(i, LAUNCH_ADD_ACTIVITY);
            }
        });
        RecyclerView rvWisata = findViewById(R.id.rv_wisata);
        adapter = new WisataAdapter(new ArrayList<>(), this);
        rvWisata.setLayoutManager(new LinearLayoutManager(WisataActivity.this,
                LinearLayoutManager.VERTICAL, false));
        rvWisata.setAdapter(adapter);
        getAllWisata();
    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == LAUNCH_ADD_ACTIVITY && resultCode == Activity.RESULT_OK)
            getAllWisata();
    }


    private void getAllWisata() {
        Call<WisataResponse> call = apiService.getAllWisata();
        srWisata.setRefreshing(true);
        call.enqueue(new Callback<WisataResponse>() {
            @Override
            public void onResponse(Call<WisataResponse> call, Response<WisataResponse> response) {
                if (response.isSuccessful()) {
                    adapter.setWisataList(response.body().getWisataList());
                    adapter.getFilter().filter(svWisata.getQuery());
                } else {
                    try {
                        JSONObject jObjError = new JSONObject(response.errorBody().string());
                        Toast.makeText(WisataActivity.this,
                                jObjError.getString("message"),
                                Toast.LENGTH_SHORT).show();
                    } catch (Exception e) {
                        Toast.makeText(WisataActivity.this,
                                e.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                }
                srWisata.setRefreshing(false);
            }
            @Override
            public void onFailure(Call<WisataResponse> call, Throwable t) {
                Toast.makeText(WisataActivity.this, "Network error",
                        Toast.LENGTH_SHORT).show();
                srWisata.setRefreshing(false);
            }
        });
    }
    public void deleteWisata(long id) {
        Call<WisataResponse> call = apiService.deleteWisata(id);
        setLoading(true);
        call.enqueue(new Callback<WisataResponse>() {
            @Override
            public void onResponse(Call<WisataResponse> call, Response<WisataResponse> response) {
                if (response.isSuccessful() && response.body() != null) {
                    Toast.makeText(WisataActivity.this,
                            response.body().getMessage(), Toast.LENGTH_SHORT).show();
                    getAllWisata();
                } else {
                    try {
                        JSONObject jObjError = new JSONObject(response.errorBody().string());
                        Toast.makeText(WisataActivity.this,
                                jObjError.getString("message"),
                                Toast.LENGTH_SHORT).show();
                    } catch (Exception e) {
                        Toast.makeText(WisataActivity.this,
                                e.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                }
                setLoading(false);
            }
            @Override
            public void onFailure(Call<WisataResponse> call, Throwable t) {
                Toast.makeText(WisataActivity.this, "Network error", Toast.LENGTH_SHORT).show();
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
            layoutLoading.setVisibility(View.GONE);
        }
    }
}
