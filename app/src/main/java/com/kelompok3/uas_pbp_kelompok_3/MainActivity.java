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
    private MaterialButton btnLogout;
    private CardView cvTravel, cvRental;
    private ImageButton btnAbout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        tvWelcome = findViewById(R.id.tvWelcome);
        btnLogout = findViewById(R.id.btnLogout);
        cvTravel = findViewById(R.id.cvTravel);
        btnAbout = findViewById(R.id.aboutBtn);
        cvRental = findViewById(R.id.cvRental);

//        tvWelcome.setText("Welcome, "+user.getUsername()+"!");
        tvWelcome.setText("Welcome, User!");

        btnLogout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(MainActivity.this,"Thank you for visiting our app!",Toast.LENGTH_SHORT).show();
                Intent moveLogin = new Intent(MainActivity.this, LoginActivity.class);
                startActivity(moveLogin);
            }
        });

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
                startActivity(moveRental);
            }
        });
    }
}