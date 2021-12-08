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
//    private TextView tvWelcome;
//    private MaterialButton btnLogout;
//    private User user;
//    private UserPreferences userPreferences;
//    private CardView cvTravel, cvLocation;
//    private ImageButton btnAbout;
//    private NotificationManagerCompat notificationManagerCompat;
//
//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_main);
//        notificationManagerCompat = NotificationManagerCompat.from(this);
//
//        userPreferences = new UserPreferences(MainActivity.this);
//        tvWelcome = findViewById(R.id.tvWelcome);
//        btnLogout = findViewById(R.id.btnLogout);
//        cvTravel = findViewById(R.id.cvTravel);
//        btnAbout = findViewById(R.id.aboutBtn);
//        cvLocation = findViewById(R.id.cvLocation);
//
//        user = userPreferences.getUserLogin();
//
//        checkLogin();
//
//        tvWelcome.setText("Welcome, "+user.getUsername()+"!");
//
//        btnLogout.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                userPreferences.logout();
//                Toast.makeText(MainActivity.this,"Thank you for visiting our app!",Toast.LENGTH_SHORT).show();
//                checkLogin();
//            }
//        });
//
//        btnAbout.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
//                builder.setTitle("Kelompok 3/PBP F")
//                        .setMessage("190710055 || Yohanes Weisang \n190710060 || Gilbertus Figo Christino \n190710138 || Ida Bagus Putu Pandu").setPositiveButton("Ok", new DialogInterface.OnClickListener() {
//                    @Override
//                    public void onClick(DialogInterface dialogInterface, int i) {
//
//                    }
//                }).show();
//
//            }
//        });
//
//        cvTravel.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Intent moveHome = new Intent(MainActivity.this,TampilDestinasi.class);
//                startActivity(moveHome);
//            }
//        });
//
//        cvLocation.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Intent moveHome = new Intent(MainActivity.this,location.class);
//                startActivity(moveHome);
//            }
//        });
//
//    }
//
//    private void checkLogin() {
//        if(!userPreferences.checkLogin()) {
//            startActivity(new Intent(MainActivity.this, LoginActivity.class));
//            finish();
//        }else {
//            Toast.makeText(MainActivity.this,"Welcome",Toast.LENGTH_SHORT).show();
//        }
//    }
}