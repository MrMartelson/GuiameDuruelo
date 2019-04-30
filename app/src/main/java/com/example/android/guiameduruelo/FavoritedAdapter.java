package com.example.android.guiameduruelo;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.android.guiameduruelo.database.AmenitiesEntry;
import com.squareup.picasso.Picasso;

import java.util.List;

public class FavoritedAdapter extends RecyclerView.Adapter<FavoritedAdapter.FavoritedViewHolder> {

    final private ItemClickListener mItemClickListener;
    private Context mContext;
    private List<AmenitiesEntry> mAmenitiesEntry;


    public FavoritedAdapter(Context context,  ItemClickListener listener) {
        mContext = context;
        mItemClickListener = listener;
    }

    @NonNull
    @Override
    public FavoritedViewHolder onCreateViewHolder(ViewGroup parent, int i) {
        View view = LayoutInflater.from(mContext)
                .inflate(R.layout.favorited_list_item, parent, false);
        return new FavoritedViewHolder(view);
    }

    @Override
    public void onBindViewHolder(FavoritedViewHolder holder, int i) {

        AmenitiesEntry amenitiesEntry = mAmenitiesEntry.get(i);
        String place = amenitiesEntry.getPlace();
        String info = amenitiesEntry.getInfo();
        String lat = amenitiesEntry.getLat();
        String lng = amenitiesEntry.getLng();
        String image = amenitiesEntry.getImage();

        holder.placeView.setText(place);
        holder.infoView.setText(info);
        Picasso.get().load(image).into(holder.imageView);


    }

    @Override
    public int getItemCount() {
        if(mAmenitiesEntry == null){
            return 0;
        }
        return mAmenitiesEntry.size();
    }

    public List<AmenitiesEntry> getAmenities(){
        return mAmenitiesEntry;
    }

    public void setFavorited(List<AmenitiesEntry> amenitiesEntries){
        mAmenitiesEntry = amenitiesEntries;
        notifyDataSetChanged();
    }

    public interface ItemClickListener {
        void onItemClickListener(int itemId);
    }

    public class FavoritedViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        TextView placeView;
        TextView infoView;
        ImageView imageView;


        public FavoritedViewHolder( View itemView) {
            super(itemView);
            placeView = itemView.findViewById(R.id.tv_title);
            infoView = itemView.findViewById(R.id.tv_info);
            imageView = itemView.findViewById(R.id.image_view);
        }

        @Override
        public void onClick(View v) {
            int elementId = mAmenitiesEntry.get(getAdapterPosition()).getId();
            mItemClickListener.onItemClickListener(elementId);
        }
    }
}
