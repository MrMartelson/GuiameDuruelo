package com.example.android.guiameduruelo;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.TextView;

import com.example.android.guiameduruelo.database.AmenitiesEntry;
import com.example.android.guiameduruelo.database.AppDatabase;
import com.example.android.guiameduruelo.database.DatabaseExecutor;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

/** Adapter for the amenities list that will be downloaded **/

public class AmenitiesAdapter extends ArrayAdapter<Amenities> {
    private ArrayList<Amenities> amenitiesList;
    private Context context;
    private int layoutId;
    public String urlImage;
    private AppDatabase mDb;


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
            holder.imageView = convertView.findViewById(R.id.image_view);
            holder.radioButton = convertView.findViewById(R.id.radio_button);

            convertView.setTag(holder);
        } else{
            holder = (ViewHolder) convertView.getTag();
        }

        final Amenities amenities = amenitiesList.get(position);

        holder.placeTextView.setText(amenities.getPlace());
        holder.infoTextView.setText(amenities.getInfo());

        urlImage = amenities.image;
        Picasso.get().load(urlImage).into(holder.imageView);

        mDb = AppDatabase.getInstance(context.getApplicationContext());

        holder.radioButton.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                String place = amenities.getPlace();
                String info = amenities.getInfo();
                String lat = amenities.getLat();
                String lng = amenities.getLng();
                String image = amenities.getImage();

                if(isChecked){
                    final AmenitiesEntry amenitiesEntry = new AmenitiesEntry(place, info, lat, lng, image);
                    DatabaseExecutor.getInstance().diskIO().execute(new Runnable() {
                        @Override
                        public void run() {
                            mDb.amenitiesDao().addAmenities(amenitiesEntry);
                        }
                    });
                }
            }
        });

        return convertView;
    }

    private class ViewHolder{
        public TextView placeTextView;
        public TextView infoTextView;
        public ImageView imageView;
        public RadioButton radioButton;
    }
}
