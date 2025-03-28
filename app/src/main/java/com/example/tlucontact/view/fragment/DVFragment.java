package com.example.tlucontact.view.fragment;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.tlucontact.adapter.DVAdapter;
import com.example.tlucontact.view.activity.DetailDVActivity;
import com.example.tlucontact.R;
import com.example.tlucontact.model.Donvi;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class DVFragment extends Fragment {
    private RecyclerView recyclerView;
    private View view;
    private DVAdapter dvAdapter;
    private List<Donvi> listDV;
    private List<Donvi> filteredList;
    private DatabaseReference databaseReference;

    public DVFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_d_v, container, false);
        recyclerView = view.findViewById(R.id.recycler_view_DV);
        recyclerView.setLayoutManager(new LinearLayoutManager(requireContext()));

        // Thêm đường kẻ phân cách giữa các item
        RecyclerView.ItemDecoration itemDecoration = new DividerItemDecoration(requireContext(), DividerItemDecoration.VERTICAL);
        recyclerView.addItemDecoration(itemDecoration);

        listDV = new ArrayList<>();
        filteredList = new ArrayList<>();

        dvAdapter = new DVAdapter(dv -> {
            Intent intent = new Intent(requireContext(), DetailDVActivity.class);
            intent.putExtra("dv_data", dv);
            startActivity(intent);
        });

        recyclerView.setAdapter(dvAdapter);

        // Lấy dữ liệu từ Firebase
        fetchDataFromFirebase();

        return view;
    }

    private void fetchDataFromFirebase() {
        databaseReference = FirebaseDatabase.getInstance().getReference("DonVi");

        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                listDV.clear();
                filteredList.clear();

                for (DataSnapshot data : snapshot.getChildren()) {
                    Donvi donvi = data.getValue(Donvi.class);
                    if (donvi != null) {
                        listDV.add(donvi);
                    }
                }

                dvAdapter.updateList(listDV); // Cập nhật giao diện khi có dữ liệu mới
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                // Xử lý lỗi nếu cần
            }
        });
    }

    public void filterList(String text) {
        List<Donvi> filteredList = new ArrayList<>();
        if (text.isEmpty()) {
            filteredList.addAll(listDV);
        } else {
            for (Donvi dv : listDV) {
                if (dv.getTenDV().toLowerCase().contains(text.toLowerCase())) {
                    filteredList.add(dv);
                }
            }
        }

        dvAdapter.updateList(filteredList); // Cập nhật giao diện sau khi lọc dữ liệu
    }
}
