package com.example.rajiv.musicvideos;


import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.Locale;

public class CustomViewAdapter extends ArrayAdapter<Music> {

        ArrayList<Music> prod;
        Context context;
        int resource;
    ArrayList<Music> refList = new ArrayList<Music>();



    public CustomViewAdapter (Context context, int resource, ArrayList<Music> prod) {
        super(context, resource, prod);

        this.prod=prod;
        this.context=context;
        this.resource=resource;
        this.refList = prod;

    }

    @Override

    public View getView(int position, View convertView, ViewGroup parent){

        if(convertView==null) {
        LayoutInflater layoutInflater = (LayoutInflater) getContext()
        .getSystemService(Activity.LAYOUT_INFLATER_SERVICE);
        convertView = layoutInflater.inflate(R.layout.list_item, null, true);
        }

        Music pro=getItem(position);

        ImageView imageView=(ImageView)convertView.findViewById(R.id.image1);
        Picasso.with(context).load(pro.getImage()).into(imageView);


        TextView txtName=(TextView)convertView.findViewById(R.id.textView3);
        txtName.setText(pro.getName());
//
//        TextView txtPrice=(TextView)convertView.findViewById(R.id.txtPrice);
//        txtPrice.setText(pro.getPrice());

        return convertView;

      }

    public void filter(String charText) {
        charText = charText.toLowerCase(Locale.getDefault());
        if (charText.length() == 0) {

            //if there is no data to filter, replace complete list
            prod.clear();
            prod.addAll(prod);
        } else {
            ArrayList<Music> tempTable = new ArrayList<Music>();
            for(Music pro : prod){
                //here you can add any other filter requirements
                //just iterate through each item and check it.
                if(pro.getName().contains(charText)){
                    tempTable.add(pro);
                }
            }

            //put filtered data into arraylist
            prod.clear();
            prod.addAll(tempTable);
        }
        notifyDataSetChanged();
    }

    }