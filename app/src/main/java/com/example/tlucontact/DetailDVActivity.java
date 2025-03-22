package com.example.tlucontact;

import android.os.Bundle;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class DetailDVActivity extends AppCompatActivity {
    private TextView txtName, txtSDT, txtDiaChi;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.detail_dv);

        txtName = findViewById(R.id.txt_name_detail_dv);
        txtSDT = findViewById(R.id.txt_sdt_detail_dv);
        txtDiaChi = findViewById(R.id.txt_diaChi_detail_dv);

        Donvi dv = (Donvi) getIntent().getSerializableExtra("dv_data");
        if(dv != null) {
            txtName.setText(dv.getTenDV());
            txtSDT.setText(dv.getSdtDV());
            txtDiaChi.setText(dv.getDiaChi());
        }

        findViewById(R.id.btn_back_dv).setOnClickListener(v -> finish());
    }
}
