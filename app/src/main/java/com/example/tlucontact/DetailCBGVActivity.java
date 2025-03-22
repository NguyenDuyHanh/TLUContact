package com.example.tlucontact;

import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class DetailCBGVActivity extends AppCompatActivity {
    private TextView txtName, txtSDT, txtDiaChi, txtEmail, txtDonvi;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.detail_cbgv);

        // Ánh xạ view
        txtName = findViewById(R.id.txt_name_detail);
        txtSDT = findViewById(R.id.txt_sdt_detail);
        txtDiaChi = findViewById(R.id.txt_diaChi_detail);
        txtEmail = findViewById(R.id.txt_email_detail);
        txtDonvi = findViewById(R.id.txt_donVi_detail);

        // Nhận dữ liệu từ Intent
        CBGV cbgv = (CBGV) getIntent().getSerializableExtra("cbgv_data");
        if (cbgv != null) {
            txtName.setText(cbgv.getTenCB());
            txtSDT.setText(cbgv.getSdtCB());
            txtDiaChi.setText(cbgv.getDcCB());
            txtEmail.setText(cbgv.getEmailCB());
            txtDonvi.setText(cbgv.getDonViCB());
        }

        // Nút quay lại
        findViewById(R.id.btn_back).setOnClickListener(v -> finish());
    }
}
