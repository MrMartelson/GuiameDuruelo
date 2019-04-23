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

        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        View rootView = inflater.inflate(layoutId, null);

        TextView placeTextView = rootView.findViewById(R.id.tv_title);
        TextView infoTextView = rootView.findViewById(R.id.tv_info);

        Amenities amenities = amenitiesList.get(position);

        placeTextView.setText(amenities.getPlace());
        infoTextView.setText(amenities.getInfo());

        return rootView;
    }
}
