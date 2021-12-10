package com.kelompok3.uas_pbp_kelompok_3;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;

import com.kelompok3.uas_pbp_kelompok_3.Preferences.UserPreferences;
import com.kelompok3.uas_pbp_kelompok_3.models.User;

public class MainActivity extends AppCompatActivity {

    private TextView tvWelcome;
    private CardView cvTravel, cvRental, cvProfil;
    private ImageButton btnAbout;
    private UserPreferences userPreferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        userPreferences = new UserPreferences(MainActivity.this);
        String username = userPreferences.getUserLogin_name();

        tvWelcome = findViewById(R.id.tvWelcome);
        btnAbout = findViewById(R.id.aboutBtn);
        cvTravel = findViewById(R.id.cvTravel);
        cvRental = findViewById(R.id.cvRental);
        cvProfil = findViewById(R.id.cvProfil);

        tvWelcome.setText("Welcome, " + username + "!");

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

        cvProfil.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent moveRental = new Intent(MainActivity.this, ProfilActivity.class);
                startActivity(moveRental);
            }
        });
    }
}