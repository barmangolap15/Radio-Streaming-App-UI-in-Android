package com.codewithgolap.radioapp.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.codewithgolap.radioapp.R;

public class HorizontalAdapter extends RecyclerView.Adapter<HorizontalAdapter.ViewHolder> {

    //create array for texts
    int[] fmTitles;
    int[] fmFrequencies;

    //create constructor


    public HorizontalAdapter(int[] fmTitles, int[] fmFrequencies) {
        this.fmTitles = fmTitles;
        this.fmFrequencies = fmFrequencies;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_horizontal, parent, false);


        return new HorizontalAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        int fmTitle = fmTitles[position];
        int fmFreq = fmFrequencies[position];

        holder.fmTitle.setText(fmTitle);
        holder.fmFrequency.setText(fmFreq);
    }

    @Override
    public int getItemCount() {
        return fmTitles.length;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private TextView fmTitle, fmFrequency;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            this.fmTitle = (TextView) itemView.findViewById(R.id.fmTitle);
            this.fmFrequency = (TextView) itemView.findViewById(R.id.fmFrequency);
        }
    }
}
