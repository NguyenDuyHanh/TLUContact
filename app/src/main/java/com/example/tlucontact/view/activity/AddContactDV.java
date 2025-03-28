package com.example.tlucontact.view.activity;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.tlucontact.R;
import com.example.tlucontact.model.Donvi;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class AddContactDV extends AppCompatActivity {
    private TextView txtHuy, txtDone;
    private EditText edtTenDV, edtSDT, edtDiaChi;
    private DatabaseReference databaseReference;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.layout_add_contact_dv);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.add_contact_dv), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        // su kiem click vao Huy
        txtHuy = findViewById(R.id.btn_cancel);
        txtHuy.setOnClickListener(v -> finish());

        // Ánh xạ các thành phần giao diện
        edtTenDV = findViewById(R.id.edt_tenDV);
        edtSDT = findViewById(R.id.edt_sdt_dv);
        edtDiaChi = findViewById(R.id.edt_diaChi_dv);

        // Xử lý sự kiện khi nhấn "Xong"
        txtDone = findViewById(R.id.btn_done_dv);
        txtDone.setOnClickListener(v -> {
            // Lấy dữ liệu từ các ô nhập
            String tenDV = edtTenDV.getText().toString();
            String sdt = edtSDT.getText().toString();
            String diaChi = edtDiaChi.getText().toString();

            boolean isValid = true; // Biến kiểm tra dữ liệu hợp lệ

            // Kiểm tra nếu các trường bị bỏ trống
            if(tenDV.isEmpty()) {
                edtTenDV.setError("Vui lòng nhập tên đơn vị");
                isValid = false;
            }

            if(sdt.isEmpty()) {
                edtSDT.setError("Vui lòng nhập số điện thoai");
                isValid = false;
            }

            if(diaChi.isEmpty()) {
                edtDiaChi.setError("Vui lòng nhập địa chỉ");
                isValid = false;
            }

            // Nếu dữ liệu hợp lệ, lưu vào Firebase
            if(isValid) {
                databaseReference = FirebaseDatabase.getInstance().getReference("DonVi");
                String id = databaseReference.push().getKey();
                Donvi donvi =  new Donvi(id, tenDV, sdt, diaChi);

                // Thêm dữ liệu vào Firebase Realtime Database
                databaseReference.child(id).setValue(donvi)
                        .addOnSuccessListener(aVoid -> {
                            Toast.makeText(AddContactDV.this, "Thêm thành công", Toast.LENGTH_SHORT).show();
                            finish();
                        })
                        .addOnFailureListener(e -> Toast.makeText(AddContactDV.this, "Thêm không thành công", Toast.LENGTH_SHORT).show());
            }

        });

    }
}