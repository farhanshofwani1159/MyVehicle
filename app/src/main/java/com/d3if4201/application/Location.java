package com.d3if4201.application;

import android.content.Context;
import android.widget.EditText;
import android.widget.Toast;

public class Location {
    private int id;
    private String location;
    private String coordinat;


    public Location(int id, String location, String coordinat) {
        this.id = id;
        this.location = location;
        this.coordinat = coordinat;
    }

}
