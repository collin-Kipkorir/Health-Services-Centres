package com.collo.youthapp;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class contaceptivesAdapter extends RecyclerView.Adapter<contaceptivesAdapter.ViewHolder> {

    private List<Hospital> hospitals;

    public contaceptivesAdapter(List<Hospital> hospitals) {
        this.hospitals = hospitals;
    }

    @NonNull
    @Override
    public contaceptivesAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item_hospital, parent, false);
        return new contaceptivesAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull contaceptivesAdapter.ViewHolder holder, int position) {
        Hospital hospital = hospitals.get(position);
        holder.nameTextView.setText(hospital.getName());
        holder.locationTextView.setText(hospital.getLocation());
    }

    @Override
    public int getItemCount() {
        return hospitals.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        TextView nameTextView;
        TextView locationTextView;

        public ViewHolder(View itemView) {
            super(itemView);
            nameTextView = itemView.findViewById(R.id.hospitalNameTextView);
            locationTextView = itemView.findViewById(R.id.hospitalLocationTextView);
        }
    }
}

