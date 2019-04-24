package com.example.android.guiameduruelo;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

/** Adapter for the amenities list that will be downloaded **/

public class AmenitiesAdapter extends ArrayAdapter<Amenities> {
    private ArrayList<Amenities> amenitiesList;
    private Context context;
    private int layoutId;

    public AmenitiesAdapter(Context context, int resource, List<Amenities> amenities) {
        super(context, resource, amenities);

        this.context = context;
        this.layoutId = resource;
        amenitiesList = new ArrayList<>(amenities);
    }

    @NonNull
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        ViewHolder holder;

        if (convertView == null) {
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

            convertView = inflater.inflate(layoutId, null);

            holder = new ViewHolder();
            holder.placeTextView = convertView.findViewById(R.id.tv_title);
            holder.infoTextView = convertView.findViewById(R.id.tv_info);

            convertView.setTag(holder);
        } else{
            holder = (ViewHolder) convertView.getTag();
        }

        Amenities amenities = amenitiesList.get(position);

        holder.placeTextView.setText(amenities.getPlace());
        holder.infoTextView.setText(amenities.getInfo());

        return convertView;
    }

    private class ViewHolder{
        public TextView placeTextView;
        public TextView infoTextView;
    }
}
