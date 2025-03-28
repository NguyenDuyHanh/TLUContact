package com.example.tlucontact.adapter;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.tlucontact.R;
import com.example.tlucontact.model.CBGV;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class CBGVAdapter extends RecyclerView.Adapter<CBGVAdapter.CBViewHolder> {

    private List<CBGV> listCB = new ArrayList<>();
    private IClickItemCBGV iClickItemCBGV;
    private DatabaseReference databaseReference;

    public interface IClickItemCBGV {
        void onClickItemCBGV(CBGV cbgv);
    }

    public CBGVAdapter(IClickItemCBGV listener) {
        this.iClickItemCBGV = listener;
        databaseReference = FirebaseDatabase.getInstance().getReference("CBGV");
        fetchDataFromFirebase();
    }

    private void fetchDataFromFirebase() {
        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                listCB.clear();
                for (DataSnapshot dataSnapshot : snapshot.getChildren()) {
                    CBGV cbgv = dataSnapshot.getValue(CBGV.class);
                    if (cbgv != null) {
                        listCB.add(cbgv);
                    }
                }
                notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Log.e("FirebaseError", "Lỗi khi lấy dữ liệu: " + error.getMessage());
            }
        });
    }

    @NonNull
    @Override
    public CBViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_contact_cbgv, parent, false);
        return new CBViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CBViewHolder holder, int position) {
        CBGV cbgv = listCB.get(position);
        holder.bind(cbgv);
        holder.txtName.setOnClickListener(v -> iClickItemCBGV.onClickItemCBGV(cbgv));
    }

    @Override
    public int getItemCount() {
        return listCB.size();
    }

    // Thêm phương thức updateList để cập nhật danh sách từ Firebase
    public void updateList(List<CBGV> newList) {
        listCB.clear();
        listCB.addAll(newList);
        notifyDataSetChanged();
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
