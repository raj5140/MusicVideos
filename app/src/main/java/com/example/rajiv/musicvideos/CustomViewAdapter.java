package com.example.rajiv.musicvideos;


import android.app.Activity;
import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class CustomViewAdapter extends ArrayAdapter<Music> {
    private static final String LOG_TAG = "CustomViewAdapter";
    private ArrayList<Music> wholeList = new ArrayList<>();
    private ArrayList<Music> currentList;
    private Context context;
    private int resource;


    public CustomViewAdapter(Context context, int resource, ArrayList<Music> prod) {
        super(context, resource, prod);
        this.currentList = prod;
        this.context = context;
        this.resource = resource;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            LayoutInflater layoutInflater = (LayoutInflater) getContext()
                    .getSystemService(Activity.LAYOUT_INFLATER_SERVICE);
            convertView = layoutInflater.inflate(R.layout.list_item, null, true);
        }

        Music pro = getItem(position);

        ImageView imageView = (ImageView) convertView.findViewById(R.id.image1);
        Picasso.with(context).load(pro.getImage()).into(imageView);


        TextView txtName = (TextView) convertView.findViewById(R.id.textView3);
        txtName.setText(pro.getName());
//
//        TextView txtPrice=(TextView)convertView.findViewById(R.id.txtPrice);
//        txtPrice.setText(pro.getPrice());

        return convertView;

    }

    public void filter(String charText) {
        charText = charText.toLowerCase();
        currentList.clear();

        if (charText.length() == 0) {
            Log.d(LOG_TAG, "filter: 1");
            currentList.addAll(wholeList);
        } else {
            for (Music pro : wholeList) {
                Log.d(LOG_TAG, "filter: 2 searching "+ charText + " in "+ pro.getName());
                if (pro.getName().toLowerCase().contains(charText)) {
                    Log.d(LOG_TAG, "filter: found "+ pro.getName());
                    currentList.add(pro);
                }
            }
        }
        notifyDataSetChanged();
    }

    public void setWholeList(ArrayList<Music> list) {
        wholeList.addAll(list);
    }

}