package com.d3if4201.application;


import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;
import androidx.core.app.ActivityCompat;

import android.Manifest;
import android.annotation.SuppressLint;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.res.Resources;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.drawable.ClipDrawable;
import android.graphics.drawable.Drawable;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.media.Image;
import android.os.Bundle;
import android.provider.MediaStore;
import android.provider.Settings;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;

public class Camera extends AppCompatActivity {

    EditText t1, t2, t3;
    Double lat;
    Double longi;
    Boolean test;

    private static  final int REQUEST_LOCATION=1;
    private static final int REQUEST_IMAGE_CAPTURE = 0;

    Button getlocationBtn;
    TextView showLocationTxt;
    LocationManager locationManager;
    String latitude,longitude;
    private LocationListener locationListener;
    private Button button;
    private TextView textView;
    private DbManager db;

    private ImageView mImageView;
    Button submit;
    byte[] imageByte;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.camera);
        mImageView = findViewById(R.id.imageView);
        db = new DbManager(this);
        submit= findViewById(R.id.btnSubmit);

        submit.setEnabled(false);
        test = getIntent().getExtras().getBoolean("Malam");

        if(test){
            getDelegate().setLocalNightMode(AppCompatDelegate.MODE_NIGHT_YES);
        }
//
//

//        sqLiteHelper = new SQLiteHelper(this, "DetectDB.sqlite", null, 1);
//        sqLiteHelper.queryData("CREATE TABLE IF NOT EXIST CAMERA(Id INTEGER PRIMARY KEY AUTOINCREMENT, image BLOB, namaLokasi VARCHAR, koordinat DOUBLE)");

        //Add permission

        ActivityCompat.requestPermissions(this,new String[]
                {Manifest.permission.ACCESS_FINE_LOCATION}, REQUEST_LOCATION);

      //  showLocationTxt=findViewById(R.id.show_location);
        getlocationBtn=findViewById(R.id.button);

        getlocationBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                locationManager=(LocationManager) getSystemService(Context.LOCATION_SERVICE);

                //Check gps is enable or not

                if (!locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER))
                {
                    //Write Function To enable gps

                    OnGPS();
                }
                else
                {
                    //GPS is already On then

                    Intent imageTextIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);

                    if (imageTextIntent.resolveActivity(getPackageManager())!=null){
                        startActivityForResult(imageTextIntent,REQUEST_IMAGE_CAPTURE);

                    }

                    ImageView img = findViewById(R.id.imageView);

                }
            }
        });
    }

    private void getLocation() {

        //Check Permissions again

        if (ActivityCompat.checkSelfPermission(Camera.this,Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(Camera.this,

                Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED)
        {
            ActivityCompat.requestPermissions(this,new String[]
                    {Manifest.permission.ACCESS_FINE_LOCATION}, REQUEST_LOCATION);
        }
        else
        {
            Location LocationGps= locationManager.getLastKnownLocation(LocationManager.GPS_PROVIDER);
            Location LocationNetwork=locationManager.getLastKnownLocation(LocationManager.NETWORK_PROVIDER);
            Location LocationPassive=locationManager.getLastKnownLocation(LocationManager.PASSIVE_PROVIDER);

            if (LocationGps !=null)
            {
                 lat=LocationGps.getLatitude();
                 longi=LocationGps.getLongitude();

                latitude=String.valueOf(lat);
                longitude=String.valueOf(longi);

               // showLocationTxt.setText("Your Location:"+"\n"+"Latitude= "+latitude+"\n"+"Longitude= "+longitude);
            }
            else if (LocationNetwork !=null)
            {
                 lat=LocationNetwork.getLatitude();
                longi=LocationNetwork.getLongitude();

                latitude=String.valueOf(lat);
                longitude=String.valueOf(longi);

                //showLocationTxt.setText("Your Location:"+"\n"+"Latitude= "+latitude+"\n"+"Longitude= "+longitude);
            }
            else if (LocationPassive !=null)
            {
                 lat=LocationPassive.getLatitude();
                 longi=LocationPassive.getLongitude();

                latitude=String.valueOf(lat);
                longitude=String.valueOf(longi);

                //showLocationTxt.setText("Your Location:"+"\n"+"Latitude= "+latitude+"\n"+"Longitude= "+longitude);
            }
            else
            {
                Toast.makeText(this, "Can't Get Your Location", Toast.LENGTH_SHORT).show();
            }

            //Thats All Run Your App
        }

    }

    private void OnGPS() {

        final AlertDialog.Builder builder= new AlertDialog.Builder(this);

        builder.setMessage("Enable GPS").setCancelable(false).setPositiveButton("YES", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                startActivity(new Intent(Settings.ACTION_LOCATION_SOURCE_SETTINGS));
            }
        }).setNegativeButton("NO", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

                dialog.cancel();
            }
        });
        final AlertDialog alertDialog=builder.create();
        alertDialog.show();
    }

    @SuppressLint("MissingSuperCall")
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {

        if (requestCode == REQUEST_IMAGE_CAPTURE && resultCode == RESULT_OK) {
            Bundle extras = data.getExtras();
            Bitmap imageBitmap = (Bitmap) extras.get("data");

            ByteArrayOutputStream image= new ByteArrayOutputStream();
            imageBitmap.compress(Bitmap.CompressFormat.PNG,100,image);
            imageByte = image.toByteArray();
            mImageView.setImageBitmap(imageBitmap);
            getLocation();
            submit.setEnabled(true);
        }
    }
    public void submit(View view) {

        EditText loc = findViewById(R.id.etLocation);
        if (loc.getText().length()!=0){

            String location = loc.getText().toString();

        String res = db.addRecordMaps(location, longi.toString(), lat.toString(),imageByte);

           Toast.makeText(this, res, Toast.LENGTH_LONG).show();

            Intent homePage = new Intent(getApplicationContext(), ListHistory.class);
            startActivity(homePage);

        } else {
            Toast.makeText(this, "Lokasi tidak boleh kosong", Toast.LENGTH_LONG).show();

        }
    }
}



