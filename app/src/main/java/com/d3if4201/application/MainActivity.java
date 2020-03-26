package com.d3if4201.application;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;


import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.Switch;


public class MainActivity extends AppCompatActivity {

    Boolean malam=false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.homepage);


        Switch switch1 = findViewById(R.id.switch1);
        switch1.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton button, boolean isChecked) {
                if(isChecked){

                    getDelegate().setLocalNightMode(AppCompatDelegate.MODE_NIGHT_YES);
                    malam=true;


                }else{
                    getDelegate().setLocalNightMode(AppCompatDelegate.MODE_NIGHT_NO);


                }
            }
        });

        ImageView btnCam = (ImageView) findViewById(R.id.cameraImg);
        btnCam.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent homePage = new Intent(getApplicationContext(), Camera.class);
                homePage.putExtra("Malam",malam);
                startActivity(homePage);
            }
        });
        ImageView btnUm = (ImageView) findViewById(R.id.bukuImg);
        btnUm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent homePage = new Intent(getApplicationContext(), UserManual.class);
                homePage.putExtra("Malam",malam);
                startActivity(homePage);
            }
        });
        ImageView btSearch = (ImageView) findViewById(R.id.searchImg);
        btSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent homePage = new Intent(getApplicationContext(), ListHistory.class);
                homePage.putExtra("Malam",malam);
                startActivity(homePage);
            }
        });
    }
    public void startdbapp(View view){
        new DbManager(this);
        startActivity(new Intent(this,Camera.class));
    }
}