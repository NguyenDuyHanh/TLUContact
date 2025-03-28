package com.example.tlucontact.view.activity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.tlucontact.R;
import com.example.tlucontact.model.Donvi;

public class DetailDVActivity extends AppCompatActivity {
    private TextView txtName, txtSDT, txtDiaChi;
    private ImageView imgIconMessage, imgIconCall;
    private TextView txtUpdate;
    private Donvi dv;
    private static final int REQUEST_UPDATE = 1; // Mã request để nhận dữ liệu từ UpdateDonVI
    // Khai báo ActivityResultLauncher để nhận kết quả từ UpdateDonVI
    private final ActivityResultLauncher<Intent> updateLauncher =
            registerForActivityResult(new ActivityResultContracts.StartActivityForResult(), result -> {
                if (result.getResultCode() == RESULT_OK && result.getData() != null) {
                    Donvi updatedDonvi = (Donvi) result.getData().getSerializableExtra("updated_dv");
                    if (updatedDonvi != null) {
                        dv = updatedDonvi;
                        setDetailData(dv); // Cập nhật UI với dữ liệu mới
                    }
                }
            });

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.detail_dv);

        txtName = findViewById(R.id.txt_name_detail_dv);
        txtSDT = findViewById(R.id.txt_sdt_detail_dv);
        txtDiaChi = findViewById(R.id.txt_diaChi_detail_dv);

        dv = (Donvi) getIntent().getSerializableExtra("dv_data");
        if(dv != null) {
            setDetailData(dv);
        }

        String phoneNumber = txtSDT.getText().toString();
        // su kien click vao icon message
        imgIconMessage = findViewById(R.id.icon_message_dv);
        imgIconMessage.setOnClickListener(v -> {
            Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("sms:" +phoneNumber));
            startActivity(intent);
        });

        // su kien click vao icon call
        imgIconCall = findViewById(R.id.icon_call_dv);
        imgIconCall.setOnClickListener(v -> {
            Intent intent = new Intent(Intent.ACTION_DIAL, Uri.parse("tel:" + phoneNumber));

            startActivity(intent);
        });

        findViewById(R.id.btn_back_dv).setOnClickListener(v -> finish());

        // nut "sửa"
        txtUpdate = findViewById(R.id.btn_update_dv);
        txtUpdate.setOnClickListener(v -> {
            Intent intent = new Intent(DetailDVActivity.this, UpdateDonVI.class);
            intent.putExtra("dv", dv);
            updateLauncher.launch(intent);
        });
    }

    //Phương thức cập nhật UI
    private void setDetailData(Donvi dv) {
        txtName.setText(dv.getTenDV());
        txtSDT.setText(dv.getSdtDV());
        txtDiaChi.setText(dv.getDiaChi());
    }
}
