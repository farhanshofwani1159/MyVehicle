package com.d3if4201.application;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.database.sqlite.SQLiteStatement;
import android.media.Image;

public class DbManager extends SQLiteOpenHelper {



    SQLiteDatabase db = this.getWritableDatabase();

    private static final String dbname = "maps.db";

    public DbManager(Context context) {
        super(context, dbname, null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String qry = "create table tbl_maps(id integer primary key autoincrement,location text,coordinat text,image BLOG)";
        db.execSQL(qry);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS tbl_maps");
        onCreate(db);
    }

    public String addRecordMaps(String location, String longtitude, String latitude,byte[] image) {

        String hasil = latitude + "," + longtitude;


        String sql="INSERT INTO tbl_maps VALUES(NULL, ?, ?,?)";
        SQLiteStatement statement = db.compileStatement(sql);

        statement.clearBindings();


        statement.bindString(1,location);
        statement.bindString(2,hasil);
        statement.bindBlob(3,image);


        statement.executeInsert();




        //        ContentValues cv = new ContentValues();
//        cv.put("location", location);
//        cv.put("coordinat", hasil);
//        cv.put("gambar",gambar);
//

//        long res = db.insert("tbl_maps", null, cv);
//        if (res == 1)
//            return "Failed";
//        else
//            return "Successfully inserted";
   return "Successfully Inserted"; }

    public Cursor selectFromMaps() {

        Cursor ambil = db.rawQuery("Select * from tbl_maps", null);

            return ambil;

    }
}



