package com.d3if4201.application;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;


public class UserManual extends AppCompatActivity {

Boolean test;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.userm);


        test = getIntent().getExtras().getBoolean("Malam");

        if(test){
            getDelegate().setLocalNightMode(AppCompatDelegate.MODE_NIGHT_YES);
        }



    }
}