package com.example.tlucontact;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class CBGVAdapter extends RecyclerView.Adapter<CBGVAdapter.CBViewHolder> {

    private List<CBGV> listCB = new ArrayList<>();
    private IClickItemCBGV iClickItemCBGV;

    public interface IClickItemCBGV {
        void onClickItemCBGV(CBGV cbgv);
    }

    public CBGVAdapter(List<CBGV> listCB, IClickItemCBGV listener) {
        this.listCB = listCB;
        this.iClickItemCBGV = listener;
    }
    @NonNull
    @Override
    public CBViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_contact_cbgv, parent,false);
        return new CBViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CBViewHolder holder, int position) {
        CBGV cbgv = listCB.get(position);
        holder.bind(cbgv);
        holder.txtName.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                iClickItemCBGV.onClickItemCBGV(cbgv);
            }
        });
    }

    @Override
    public int getItemCount() {
        return listCB.size();
    }

    static class CBViewHolder extends RecyclerView.ViewHolder {
        private TextView txtName;

        public CBViewHolder(@NonNull View itemView) {
            super(itemView);
            txtName = itemView.findViewById(R.id.text_view_cbgv);
        }
        public void bind(CBGV cbgv) {
            txtName.setText(cbgv.getTenCB());
        }
    }
}
