package com.example.tlucontact.adapter;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.tlucontact.R;
import com.example.tlucontact.model.Donvi;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class DVAdapter extends RecyclerView.Adapter<DVAdapter.DVViewHolder> {

    private List<Donvi> listDV = new ArrayList<>();
    private IClickItemDV iClickItemDV;
    private DatabaseReference databaseReference;

    public interface IClickItemDV {
        void onClickItem(Donvi dv);
    }

    public DVAdapter(IClickItemDV listener) {
        this.iClickItemDV = listener;
        databaseReference = FirebaseDatabase.getInstance().getReference("DonVi");
        fetchDataFromFirebase();
    }

    private void fetchDataFromFirebase() {
        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                listDV.clear();
                for (DataSnapshot data : snapshot.getChildren()) {
                    Donvi donvi = data.getValue(Donvi.class);
                    if (donvi != null) {
                        listDV.add(donvi);
                    }
                }
                Log.d("FirebaseData", "Số lượng đơn vị: " + listDV.size());
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
    public DVViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_contact_dv, parent, false);
        return new DVViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull DVViewHolder holder, int position) {
        Donvi dv = listDV.get(position);
        holder.bind(dv);
        holder.itemView.setOnClickListener(v -> iClickItemDV.onClickItem(dv));
    }

    @Override
    public int getItemCount() {
        return listDV.size();
    }

    // ✅ Thêm phương thức updateList để cập nhật danh sách từ Firebase
    public void updateList(List<Donvi> newList) {
        listDV.clear();
        listDV.addAll(newList);
        notifyDataSetChanged();
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
