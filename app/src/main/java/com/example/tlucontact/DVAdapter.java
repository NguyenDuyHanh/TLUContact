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
    private List<Donvi> listDV = new ArrayList<>();
    private IClickItemDV iClickItemDV;

    public interface IClickItemDV {
        void onClickItem(Donvi dv);
    }

    public DVAdapter(List<Donvi> listDV, IClickItemDV iClickItemDV) {
        this.listDV = listDV;
        this.iClickItemDV = iClickItemDV;
    }

    public void updateList(List<Donvi> newList) {
        listDV.clear();
        listDV.addAll(newList);
        notifyDataSetChanged();
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
        holder.txtName.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                iClickItemDV.onClickItem(dv);
            }
        });
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
