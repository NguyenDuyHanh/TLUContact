package com.example.tlucontact;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class DVAdapter extends RecyclerView.Adapter<DVAdapter.DVViewHolder> {
    List<Donvi> listDV = new ArrayList<>();

    public DVAdapter(List<Donvi> listDV) {
        this.listDV = listDV;
    }

    @NonNull
    @Override
    public DVViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_contact_dv,parent,false);
        return new DVViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull DVViewHolder holder, int position) {
        Donvi dv = listDV.get(position);
        holder.bind(dv);
    }

    @Override
    public int getItemCount() {
        return listDV.size();
    }

    static class DVViewHolder extends RecyclerView.ViewHolder {
        private TextView txtName;

        public DVViewHolder(@NonNull View itemView) {
            super(itemView);
            txtName = itemView.findViewById(R.id.text_view_dv);
        }

        public void bind(Donvi dv) {
            txtName.setText(dv.getTenDV());
        }
    }
}
