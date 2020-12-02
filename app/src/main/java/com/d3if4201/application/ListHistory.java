package com.d3if4201.application;

import android.content.Intent;
import android.database.Cursor;

import android.net.Uri;
import android.os.Bundle;

import android.view.View;
import android.widget.AdapterView;

import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.Toast;


import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;


import com.google.android.gms.maps.GoogleMap;

import com.google.android.gms.maps.SupportMapFragment;


import java.util.ArrayList;

public class ListHistory extends AppCompatActivity {

   private static final String TAG = "ListHistory";

  DbManager mDatabaseHelper;

    private ListView mListView;

    GoogleMap mapAPI;
    SupportMapFragment mapFragment;
    Boolean test;
    ListAdapter list;
    Cursor x;
     ArrayList<ObjectParkir> theList;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.history);

        mListView = (ListView) findViewById(R.id.listView);
        mDatabaseHelper = new DbManager(this);
         theList = new ArrayList<>();
         list = new Adapter(this, R.layout.history_item, theList);

         fetchData();


        test = getIntent().getExtras().getBoolean("Malam");

        if(test){
            getDelegate().setLocalNightMode(AppCompatDelegate.MODE_NIGHT_YES);
        }

        mListView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
               mDatabaseHelper.hapus(theList.get(position).getId());

                Toast.makeText(getApplicationContext(),"Berhasil dihapus",Toast.LENGTH_LONG).show();


             fetchData();
                return true;
            }
        });

        mListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String koordinat= theList.get(position).getKoordinat();
                Uri gmmIntentUri = Uri.parse("google.navigation:q="+koordinat);
                Intent mapIntent = new Intent(Intent.ACTION_VIEW, gmmIntentUri);
                mapIntent.setPackage("com.google.android.apps.maps");
                startActivity(mapIntent);
            }
        });
    }

    public void fetchData(){
        theList = new ArrayList<>();
        list = new Adapter(this, R.layout.history_item, theList);
        x = mDatabaseHelper.selectFromMaps();

        while (x.moveToNext()) {
            theList.add(new ObjectParkir(x.getInt(0), x.getString(1), x.getBlob(3),x.getString(2)));

            mListView.setAdapter(list);

        }
    }




    //        SwipeMenuCreator creator = new SwipeMenuCreator() {

//            @Override
//            public void create(SwipeMenu menu) {
//                // create "open" item
//                SwipeMenuItem openItem = new SwipeMenuItem(
//                        getApplicationContext());
//                // set item background
//                openItem.setBackground(new ColorDrawable(Color.rgb(0x00, 0x66,
//                        0xff)));
//                // set item width
//                openItem.setWidth(170);
//                // set item title
//                openItem.setTitle("Open");
//                // set item title fontsize
//                openItem.setTitleSize(18);
//                // set item title font color
//                openItem.setTitleColor(Color.WHITE);
//                // add to menu
//                menu.addMenuItem(openItem);
//
//                // create "delete" item
//                SwipeMenuItem deleteItem = new SwipeMenuItem(
//                        getApplicationContext());
//                // set item background
//                deleteItem.setBackground(new ColorDrawable(Color.rgb(0xF9,
//                        0x3F, 0x25)));
//                // set item width
//                deleteItem.setWidth(170);
//                // set a icon
//                deleteItem.setIcon(R.drawable.ic_delete);
//                // add to menu
//                menu.addMenuItem(deleteItem);
//            }
//        };

//        listView.setMenuCreator(creator);
//
//        listView.setOnMenuItemClickListener(new SwipeMenuListView.OnMenuItemClickListener() {
//            @Override
//            public boolean onMenuItemClick(int position, SwipeMenu menu, int index) {
//                switch (index) {
//                    case 0:
//                        Log.d(TAG, "onMenuItemClick: clicked item " + index);
//                        break;
//                    case 1:
//                        Log.d(TAG, "onMenuItemClick: clicked item " + index);
//                        break;
//                }
//                // false : close the menu; true : not close the menu
//                return false;
//            }
//        });



}
