package com.kelompok3.uas_pbp_kelompok_3;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.Manifest;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Base64;
import android.view.LayoutInflater;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.kelompok3.uas_pbp_kelompok_3.api.ApiClient;
import com.kelompok3.uas_pbp_kelompok_3.api.ApiInterface;
import com.kelompok3.uas_pbp_kelompok_3.models.Wisata;
import com.kelompok3.uas_pbp_kelompok_3.models.WisataResponse;

import org.json.JSONObject;

import java.io.ByteArrayOutputStream;
import java.io.InputStream;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AddEditWisataActivity extends AppCompatActivity {

    private static final int PERMISSION_REQUEST_CAMERA = 100;
    private static final int CAMERA_REQUEST = 0;
    private static final int GALLERY_PICTURE = 1;

    private ApiInterface apiService;
    private EditText etNamaWisata, etHargaWisata, etLokasi, etDeskripsi;
    private ImageView ivGambarWisata;
    private LinearLayout layoutLoading;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_edit_wisata);
        apiService = ApiClient.getClient().create(ApiInterface.class);

        ivGambarWisata = findViewById(R.id.iv_gambarWisata);
        etNamaWisata = findViewById(R.id.et_namaWisata);
        etHargaWisata = findViewById(R.id.et_hargaWisata);
        etLokasi = findViewById(R.id.et_lokasi);
        etDeskripsi = findViewById(R.id.et_deskripsi);
        layoutLoading = findViewById(R.id.layout_loading);

        ivGambarWisata.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                LayoutInflater layoutInflater = LayoutInflater.from(AddEditWisataActivity.this);
                View selectMediaView = layoutInflater.inflate(R.layout.layout_select_media, null);

                final AlertDialog alertDialog = new AlertDialog.Builder(selectMediaView.getContext()).create();

                Button btnKamera = selectMediaView.findViewById(R.id.btn_kamera);
                Button btnGaleri = selectMediaView.findViewById(R.id.btn_galeri);

                btnKamera.setOnClickListener(new View.OnClickListener() {
                    public void onClick(View v) {
                        if (checkSelfPermission(Manifest.permission.CAMERA) == PackageManager.PERMISSION_DENIED) {
                            String[] permission = {Manifest.permission.CAMERA};
                            requestPermissions(permission, PERMISSION_REQUEST_CAMERA);
                        } else {
                            // Membuka kamera
                            Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                            startActivityForResult(intent, CAMERA_REQUEST);
                        }
                        alertDialog.dismiss();
                    }
                });

                btnGaleri.setOnClickListener(new View.OnClickListener() {
                    public void onClick(View v) {
                        // Membuka galeri
                        Intent intent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                        startActivityForResult(intent, GALLERY_PICTURE);

                        alertDialog.dismiss();
                    }
                });

                alertDialog.setView(selectMediaView);
                alertDialog.show();
            }
        });

        Button btnCancel = findViewById(R.id.btn_cancel);
        btnCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) { finish(); }
        });

        Button btnSave = findViewById(R.id.btn_save);
        TextView tvTitle = findViewById(R.id.tv_title);
        long id = getIntent().getLongExtra("id", -1);

        if (id == -1) {
            tvTitle.setText("Tambah Wisata");

            btnSave.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if (etNamaWisata.length() == 0 ||
                            etHargaWisata.length() == 0 ||
                            etLokasi.length() == 0||
                            etDeskripsi.length() == 0)
                        Toast.makeText(AddEditWisataActivity.this, "Lengkapi Data Terlebih Dahulu!", Toast.LENGTH_SHORT).show();
                    else
                        createProduk();
                }
            });
        } else {
            tvTitle.setText("Edit Wisata");
            getProdukById(id);

            btnSave.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if (etNamaWisata.length() == 0 ||
                            etHargaWisata.length() == 0 ||
                            etLokasi.length() == 0 ||
                            etDeskripsi.length() == 0)
                        Toast.makeText(AddEditWisataActivity.this, "Lengkapi Data Terlebih Dahulu!", Toast.LENGTH_SHORT).show();
                    else
                        updateProduk(id);
                }
            });
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);

        if (requestCode == PERMISSION_REQUEST_CAMERA) {
            if (grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                // Membuka kamera
                Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                startActivityForResult(intent, CAMERA_REQUEST);
            } else {
                Toast.makeText(AddEditWisataActivity.this, "Permission denied.", Toast.LENGTH_SHORT).show();
            }
        }
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        Bitmap bitmap = null;

        if (data == null)
            return;

        if (resultCode == RESULT_OK && requestCode == GALLERY_PICTURE) {
            Uri selectedImage = data.getData();

            try {
                InputStream inputStream = getContentResolver().openInputStream(selectedImage);
                bitmap = BitmapFactory.decodeStream(inputStream);
            } catch (Exception e) {
                Toast.makeText(AddEditWisataActivity.this, e.getMessage(), Toast.LENGTH_SHORT).show();
            }
        } else if (resultCode == RESULT_OK && requestCode == CAMERA_REQUEST) {
            bitmap = (Bitmap) data.getExtras().get("data");
        }

        bitmap = getResizedBitmap(bitmap, 512);
        ivGambarWisata.setImageBitmap(bitmap);
    }

    private Bitmap getResizedBitmap(Bitmap bmp, int maxSize) {
        int width = bmp.getWidth();
        int height = bmp.getHeight();

        float bitmapRatio = (float) width / (float) height;

        if (bitmapRatio > 1) {
            width = maxSize;
            height = (int) (width / bitmapRatio);
        } else {
            height = maxSize;
            width = (int) (height * bitmapRatio);
        }

        return Bitmap.createScaledBitmap(bmp, width, height, true);
    }

    private String bitmapToBase64(Bitmap bmp) {
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        bmp.compress(Bitmap.CompressFormat.PNG, 100, byteArrayOutputStream);
        byte[] bytes = byteArrayOutputStream.toByteArray();

        return Base64.encodeToString(bytes, Base64.DEFAULT);
    }

    private void getProdukById(long id) {
        setLoading(true);
        Call<WisataResponse> call = apiService.getWisataById(id);

        call.enqueue(new Callback<WisataResponse>() {
            @Override
            public void onResponse(Call<WisataResponse> call, Response<WisataResponse> response) {
                if (response.isSuccessful()) {
                    Wisata wisata = response.body().getWisataList().get(0);
                    etNamaWisata.setText(wisata.getNama_wisata());
                    etHargaWisata.setText(wisata.getHarga());
                    etLokasi.setText(wisata.getLokasi());
                    etDeskripsi.setText(wisata.getDeskripsi());
                    Glide.with(AddEditWisataActivity.this)
                            .load(wisata.getUrl_gambar())
                            .placeholder(R.drawable.no_image)
                            .into(ivGambarWisata);
                } else {
                    try {
                        JSONObject jObjError = new JSONObject(response.errorBody().string());
                        Toast.makeText(AddEditWisataActivity.this, jObjError.getString("message"), Toast.LENGTH_SHORT).show();
                    } catch (Exception e) {
                        Toast.makeText(AddEditWisataActivity.this, e.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                }
                setLoading(false);
            }

            @Override
            public void onFailure(Call<WisataResponse> call, Throwable t) {
                Toast.makeText(AddEditWisataActivity.this, "Network Error!", Toast.LENGTH_SHORT).show();
                setLoading(false);
            }
        });
    }

    private void createProduk() {
        setLoading(true);
        BitmapDrawable bitmapDrawable = (BitmapDrawable) ivGambarWisata.getDrawable();
        Bitmap bit = bitmapDrawable.getBitmap();
        Wisata wisata = new Wisata(
                etNamaWisata.getText().toString(),
                etLokasi.getText().toString(),
                etDeskripsi.getText().toString(),
                bitmapToBase64(bit),
                Integer.parseInt(etHargaWisata.getText().toString()));
        Call<WisataResponse> call = apiService.createWisata(wisata);
        call.enqueue(new Callback<WisataResponse>() {
            @Override
            public void onResponse(Call<WisataResponse> call, Response<WisataResponse> response) {
                if (response.isSuccessful()) {
                    Toast.makeText(AddEditWisataActivity.this, response.body().getMessage(), Toast.LENGTH_SHORT).show();
                    Intent returnIntent = new Intent();
                    setResult(Activity.RESULT_OK, returnIntent);
                    finish();
                } else {
                    try {
                        JSONObject jObjError = new JSONObject(response.errorBody().string());
                        Toast.makeText(AddEditWisataActivity.this, jObjError.getString("message"), Toast.LENGTH_SHORT).show();
                    } catch (Exception e) {
                        Toast.makeText(AddEditWisataActivity.this, e.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                }
                setLoading(false);
            }

            @Override
            public void onFailure(Call<WisataResponse> call, Throwable t) {
                Toast.makeText(AddEditWisataActivity.this, t.getMessage(), Toast.LENGTH_SHORT).show();
                setLoading(false);
            }
        });
    }

    private void updateProduk(long id) {
        setLoading(true);
        BitmapDrawable bitmapDrawable = (BitmapDrawable) ivGambarWisata.getDrawable();
        Bitmap bit = bitmapDrawable.getBitmap();
        Wisata wisata = new Wisata(
                etNamaWisata.getText().toString(),
                etLokasi.getText().toString(),
                etDeskripsi.getText().toString(),
                bitmapToBase64(bit),
                Integer.parseInt(etHargaWisata.getText().toString()));
        Call<WisataResponse> call = apiService.updateWisata(id, wisata);
        call.enqueue(new Callback<WisataResponse>() {
            @Override
            public void onResponse(Call<WisataResponse> call, Response<WisataResponse> response) {
                if (response.isSuccessful()) {
                    Toast.makeText(AddEditWisataActivity.this, response.body().getMessage(), Toast.LENGTH_SHORT).show();
                    Intent returnIntent = new Intent();
                    setResult(Activity.RESULT_OK, returnIntent);
                    finish();
                } else {
                    try {
                        JSONObject jObjError = new JSONObject(response.errorBody().string());
                        Toast.makeText(AddEditWisataActivity.this, jObjError.getString("message"), Toast.LENGTH_SHORT).show();
                    } catch (Exception e) {
                        Toast.makeText(AddEditWisataActivity.this, e.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                }
                setLoading(false);
            }
            @Override
            public void onFailure(Call<WisataResponse> call, Throwable t) {
                Toast.makeText(AddEditWisataActivity.this, t.getMessage(), Toast.LENGTH_SHORT).show();
                setLoading(false);
            }
        });
    }

    // Fungsi ini digunakan menampilkan layout loading
    private void setLoading(boolean isLoading) {
        if (isLoading) {
            getWindow().setFlags(WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE, WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE);
            layoutLoading.setVisibility(View.VISIBLE);
        } else {
            getWindow().clearFlags(WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE);
            layoutLoading.setVisibility(View.INVISIBLE);
        }
    }
}