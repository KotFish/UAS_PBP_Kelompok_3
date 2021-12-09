package com.kelompok3.uas_pbp_kelompok_3;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

import com.kelompok3.uas_pbp_kelompok_3.databinding.ActivityQractivityBinding;

public class QRActivity extends AppCompatActivity {

    private ActivityQractivityBinding binding;
    private final ActivityResultLauncher<Intent> cameraResult =
            registerForActivityResult(new ActivityResultContracts.StartActivityForResult(),
                    new ActivityResultCallback<ActivityResult>() {
                        @Override
                        public void onActivityResult(ActivityResult result) {
                            if (result.getResultCode() == Activity.RESULT_OK) {
                                try {
                                    Intent intent = result.getData();
                                    String strQRRes =
                                            intent.getStringExtra("QR_RESULT");
                                    String[] res = strQRRes.split(";");
                                    binding.txtNama.setText(res[0]);
                                    binding.txtNPM.setText(res[1]);
                                    binding.txtProdi.setText(res[2]);
                                } catch (Exception e) {
                                    binding.txtNama.setText("");
                                    binding.txtNPM.setText("");
                                    binding.txtProdi.setText("");
                                    Toast.makeText(QRActivity.this, "QR CODE TIDAK VALID!", Toast.LENGTH_SHORT).show();
                                }
                            }
                        }
                    });
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityQractivityBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        setContentView(R.layout.activity_qractivity);
        binding.btnScan.setOnClickListener(v -> {
            cameraResult.launch(new Intent(this, QRScannerActivity.class));
        });
    }
}