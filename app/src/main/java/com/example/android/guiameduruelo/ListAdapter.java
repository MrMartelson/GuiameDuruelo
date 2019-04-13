package com.example.android.guiameduruelo;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

public class ListAdapter extends RecyclerView.Adapter<ListAdapter.ViewHolder> {
    private List<String> values;

    public class ViewHolder extends RecyclerView.ViewHolder {
        public TextView tvTitle;
        public TextView tvInfo;
        public View layout;

        public ViewHolder(View v){
            super(v);
            layout = v;
            tvTitle = v.findViewById(R.id.tv_title);
            tvInfo = v.findViewById(R.id.tv_info);
        }
    }

    public ListAdapter(List<String> myDataset) {
        values = myDataset;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        LayoutInflater inflater = LayoutInflater.from(viewGroup.getContext());
        View v = inflater.inflate(R.layout.card_info, viewGroup, false);

        ViewHolder vh = new ViewHolder(v);
        return vh;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int i) {
        final String name = values.get(i);
        holder.tvTitle.setText(name);
        holder.tvInfo.setText(name);
    }

    @Override
    public int getItemCount() {
        return 0;
    }

}
