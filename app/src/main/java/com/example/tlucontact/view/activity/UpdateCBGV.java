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
import com.example.tlucontact.model.CBGV;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.HashMap;

public class UpdateCBGV extends AppCompatActivity {
    private TextView txtHuy, txtDone;
    private EditText edtTen, edtSDT, edtDiaChi, edtEmail, edtDonVi;
    private DatabaseReference databaseReference;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_update_cbgv);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.update_contact_cbgv), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        // Ánh xạ các thành phần giao diện với ID trong layout
        edtTen = findViewById(R.id.edt_name_update_cbgv);
        edtSDT = findViewById(R.id.edt_sdt_update_cbgv);
        edtDiaChi = findViewById(R.id.edt_diachi_update_cbgv);
        edtEmail = findViewById(R.id.edt_email_update_cbgv);
        edtDonVi = findViewById(R.id.edt_donvi_update_cbgv);

        // Nút "Hủy" - khi bấm vào sẽ đóng activity
        txtHuy = findViewById(R.id.btn_cancel_update_cbgv);
        txtHuy.setOnClickListener(v -> finish());

        // nhan du lieu tu intent
        Intent intent = getIntent();
        CBGV cbgv = (CBGV) intent.getSerializableExtra("cbgv");
        // Đặt dữ liệu lên các EditText
        edtTen.setText(cbgv.getTenCB());
        edtSDT.setText(cbgv.getSdtCB());
        edtDiaChi.setText(cbgv.getDcCB());
        edtEmail.setText(cbgv.getEmailCB());
        edtDonVi.setText(cbgv.getDonViCB());

        // Nút "Xong"khi bấm vào sẽ cập nhật dữ liệu lên Firebase
        txtDone =findViewById(R.id.btn_done_update_cbgv);
        txtDone.setOnClickListener(v -> {
            // Lấy dữ liệu mới từ các EditText
            String tenMoi = edtTen.getText().toString();
            String sdtMoi = edtSDT.getText().toString();
            String diaChiMoi = edtDiaChi.getText().toString();
            String emailMoi = edtEmail.getText().toString();
            String donViMoi = edtDonVi.getText().toString();

            // Tham chiếu đến Firebase Database, nhánh "CBGV"
            databaseReference = FirebaseDatabase.getInstance().getReference("CBGV");
            // Tạo một HashMap chứa dữ liệu cập nhật
            HashMap<String, Object> updateData = new HashMap<>();
            updateData.put("tenCB", tenMoi);
            updateData.put("sdtCB", sdtMoi);
            updateData.put("dcCB", diaChiMoi);
            updateData.put("emailCB", emailMoi);
            updateData.put("donViCB", donViMoi);

            // Thực hiện cập nhật dữ liệu trên Firebase
            databaseReference.child(cbgv.getIdCB()).updateChildren(updateData)
                    .addOnSuccessListener(aVoid -> {
                        Toast.makeText(UpdateCBGV.this,"Sửa thành công", Toast.LENGTH_SHORT).show();
                        // Cập nhật đối tượng CBGV với dữ liệu mới
                        cbgv.setTenCB(tenMoi);
                        cbgv.setSdtCB(sdtMoi);
                        cbgv.setDcCB(diaChiMoi);
                        cbgv.setEmailCB(emailMoi);
                        cbgv.setDonViCB(donViMoi);

                        // Gửi dữ liệu cập nhật về DetailCBGVActivity
                        Intent resultIntent = new Intent();
                        resultIntent.putExtra("updated_cbgv", cbgv);
                        setResult(RESULT_OK, resultIntent);
                        finish();
                    })
                    .addOnFailureListener(e -> Toast.makeText(UpdateCBGV.this, "Sửa không thành công", Toast.LENGTH_SHORT).show());
        });

    }
}