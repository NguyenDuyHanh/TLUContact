package com.example.tlucontact.view.activity;

import android.annotation.SuppressLint;
import android.content.Intent;
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

import java.util.HashMap;

public class UpdateDonVI extends AppCompatActivity {
    private TextView txtCancel, txtDone;
    private EditText edtTen, edtSDT,edtDiaChi;
    private DatabaseReference databaseReference;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_update_don_vi);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.update_contact_dv), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        // Nút "Hủy" khi bấm vào sẽ đóng activity
        txtCancel = findViewById(R.id.btn_cancel_update_dv);
        txtCancel.setOnClickListener(v -> finish());


        // nhan su lieu tu intent va hien thi du lieu len editText
        edtTen = findViewById(R.id.edt_tenDV_update);
        edtSDT = findViewById(R.id.edt_sdt_update_dv);
        edtDiaChi = findViewById(R.id.edt_diaChi_update_dv);

        // Nhận dữ liệu từ Intent khi mở Activity
        Intent intent = getIntent();
        Donvi donvi = (Donvi) intent.getSerializableExtra("dv");
        edtTen.setText(donvi.getTenDV());
        edtSDT.setText(donvi.getSdtDV());
        edtDiaChi.setText(donvi.getDiaChi());

        // Nút "Xong" khi bấm vào sẽ cập nhật dữ liệu lên Firebase
        txtDone = findViewById(R.id.btn_done_update_dv);
        txtDone.setOnClickListener(v -> {
            String tenMoi = edtTen.getText().toString();
            String sdtMoi = edtSDT.getText().toString();
            String diaChiMoi = edtDiaChi.getText().toString();

            // Tham chiếu đến Firebase Database, nhánh "DonVi"
            databaseReference = FirebaseDatabase.getInstance().getReference("DonVi");
            HashMap<String, Object> update = new HashMap<>();
            update.put("tenDV", tenMoi);
            update.put("sdtDV", sdtMoi);
            update.put("diaChi", diaChiMoi);

            databaseReference.child(donvi.getIdDV()).updateChildren(update)
                    .addOnSuccessListener(aVoid -> {
                        Toast.makeText(UpdateDonVI.this,"Sửa thành công", Toast.LENGTH_SHORT).show();
                        // Cập nhật đối tượng Donvi với dữ liệu mới
                        donvi.setTenDV(tenMoi);
                        donvi.setSdtDV(sdtMoi);
                        donvi.setDiaChi(diaChiMoi);

                        // Gửi dữ liệu cập nhật về Activity trước đó
                        Intent resultIntent = new Intent();
                        resultIntent.putExtra("updated_dv", donvi);
                        setResult(RESULT_OK, resultIntent);
                        finish();
                    })
                    .addOnFailureListener(e -> Toast.makeText(UpdateDonVI.this,"Sửa không thành công", Toast.LENGTH_SHORT).show());

        });

    }
}