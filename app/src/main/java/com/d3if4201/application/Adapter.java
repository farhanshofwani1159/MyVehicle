package com.d3if4201.application;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

public class Adapter extends BaseAdapter {
    private Context context;
    private int layout;
    private ArrayList<ObjectParkir> object;

    public Adapter(Context context, int layout, ArrayList<ObjectParkir> object) {
        this.context = context;
        this.layout = layout;
        this.object = object;
    }

    @Override
    public int getCount() {
        return object.size();
    }

    @Override
    public java.lang.Object getItem(int position) {
        return object.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }
    private class ViewHolder{
        ImageView imageView;
        TextView textLokasi;
    }
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        View row = convertView;
        ViewHolder holder = new ViewHolder();

        if (row == null){
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            row = inflater.inflate(layout, null);

            holder.textLokasi = (TextView) row.findViewById(R.id.textView_lokasi);
            holder.imageView = (ImageView) row.findViewById(R.id.imageView_Lokasi);
            row.setTag(holder);

        }else{
            holder = (ViewHolder) row.getTag();
        }
        ObjectParkir parkir = object.get(position);

        holder.textLokasi.setText(parkir.getLokasi());
        byte[]image=parkir.getGambar();
        Bitmap bitmap = BitmapFactory.decodeByteArray(image,0,image.length);
        holder.imageView.setImageBitmap(bitmap);

        return row;
    }
}
