package com.example.tlucontact.view.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.tlucontact.adapter.CBGVAdapter;
import com.example.tlucontact.view.activity.DetailCBGVActivity;
import com.example.tlucontact.R;
import com.example.tlucontact.model.CBGV;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class CBGVFragment extends Fragment {
    private RecyclerView recyclerView;
    private View view;
    private CBGVAdapter cbgvAdapter;
    private List<CBGV> listCB;
    private DatabaseReference databaseReference;

    public CBGVFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_c_b_d_v, container, false);
        recyclerView = view.findViewById(R.id.recycler_view_CBGV);

        // Setup RecyclerView
        recyclerView.setLayoutManager(new LinearLayoutManager(requireContext()));
        recyclerView.addItemDecoration(new DividerItemDecoration(requireContext(), DividerItemDecoration.VERTICAL));

        // Khởi tạo danh sách & Adapter
        listCB = new ArrayList<>();
        cbgvAdapter = new CBGVAdapter(cbgv -> {
            // Khi người dùng bấm vào một mục trong danh sách, mở màn hình chi tiết
            Intent intent = new Intent(requireContext(), DetailCBGVActivity.class);
            intent.putExtra("cbgv_data", cbgv); // Truyền dữ liệu sang Activity chi tiết
            startActivity(intent);
        });

        recyclerView.setAdapter(cbgvAdapter); // Gán Adapter cho RecyclerView

        // Kết nối Firebase Database để lấy dữ liệu CBGV
        databaseReference = FirebaseDatabase.getInstance().getReference("CBGV");
        fetchDataFromFirebase(); // Gọi hàm lấy dữ liệu từ Firebase

        return view;
    }

    //Lấy dữ liệu từ Firebase Database và cập nhật RecyclerView
    private void fetchDataFromFirebase() {
        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                listCB.clear();
                for (DataSnapshot dataSnapshot : snapshot.getChildren()) {
                    CBGV cbgv = dataSnapshot.getValue(CBGV.class); // Chuyển đổi dữ liệu từ Firebase thành đối tượng CBGV
                    if (cbgv != null) {
                        listCB.add(cbgv);
                    }
                }
                cbgvAdapter.updateList(listCB); // Cập nhật dữ liệu cho Adapter
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Log.e("FirebaseError", "Lỗi khi lấy dữ liệu: " + error.getMessage());
            }
        });
    }

    // Lọc danh sách CBGV theo từ khóa nhập vào
    public void filterList(String text) {
        List<CBGV> filteredList = new ArrayList<>();
        if (text.isEmpty()) {
            filteredList.addAll(listCB);  // Nếu không nhập gì, hiển thị toàn bộ danh sách
        } else {
            for (CBGV cbgv : listCB) {
                if (cbgv.getTenCB().toLowerCase().contains(text.toLowerCase())) {
                    filteredList.add(cbgv); // / Thêm vào danh sách nếu tên CBGV chứa từ khóa tìm kiếm

                }
            }
        }
        cbgvAdapter.updateList(filteredList); // Cập nhật lại danh sách trên giao diện
    }
}
