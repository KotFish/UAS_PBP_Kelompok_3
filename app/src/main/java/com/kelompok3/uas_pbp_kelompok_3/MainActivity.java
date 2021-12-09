package com.kelompok3.uas_pbp_kelompok_3;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.core.app.NotificationManagerCompat;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.button.MaterialButton;

public class MainActivity extends AppCompatActivity {

    private TextView tvWelcome;
    private CardView cvTravel, cvRental;
    private ImageButton btnAbout;
    private Bundle b;
    private String token, userName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        tvWelcome = findViewById(R.id.tvWelcome);
        cvTravel = findViewById(R.id.cvTravel);
        btnAbout = findViewById(R.id.aboutBtn);
        cvRental = findViewById(R.id.cvRental);
        b = getIntent().getExtras();
        token = (String) b.get("token");
        userName = (String) b.get("username");

        tvWelcome.setText("Welcome to SkuyTravel, " + userName +"!");

        btnAbout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
                builder.setTitle("Kelompok 3/PBP F")
                        .setMessage("190710055 || Yohanes Weisang \n190710060 || Gilbertus Figo Christino \n190710138 || Ida Bagus Putu Pandu").setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {

                    }
                }).show();

            }
        });

        cvTravel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent moveTravel = new Intent(MainActivity.this, WisataActivity.class);
                startActivity(moveTravel);
            }
        });

        cvRental.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent moveRental = new Intent(MainActivity.this, RentalActivity.class);
                moveRental.putExtra("token", token);
                startActivity(moveRental);
            }
        });
    }
}