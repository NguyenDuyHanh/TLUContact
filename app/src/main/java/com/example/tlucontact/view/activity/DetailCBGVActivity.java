package com.example.tlucontact.view.activity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;

import com.example.tlucontact.R;
import com.example.tlucontact.model.CBGV;
import com.example.tlucontact.model.Donvi;

public class DetailCBGVActivity extends AppCompatActivity {
    private TextView txtName, txtSDT, txtDiaChi, txtEmail, txtDonvi;
    private ImageView imgIconMessage, imgIconCall;
    private TextView txtUpdate;
    private CBGV cbgv;
    private static final int REQUEST_UPDATE = 1; // Mã request để nhận dữ liệu từ UpdateDonVI

    // ActivityResultLauncher để nhận kết quả từ UpdateCBGV
    private final ActivityResultLauncher<Intent> updateLauncher = registerForActivityResult(
            new ActivityResultContracts.StartActivityForResult(),
            result -> {
                if (result.getResultCode() == RESULT_OK && result.getData() != null) {
                    CBGV updatedCBGV = (CBGV) result.getData().getSerializableExtra("updated_cbgv");
                    if (updatedCBGV != null) {
                        cbgv = updatedCBGV; // Cập nhật đối tượng
                        updateCBGV(); // Cập nhật giao diện
                    }
                }
            });

    @SuppressLint("MissingInflatedId")
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
        cbgv = (CBGV) getIntent().getSerializableExtra("cbgv_data");
        updateCBGV();

        String phoneNumber = txtSDT.getText().toString();
        // Xử lý sự kiện nhắn tin khi bấm vào icon tin nhắn
        imgIconMessage = findViewById(R.id.icon_message_cbgv);
        imgIconMessage.setOnClickListener(v -> {
            Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("sms:" + phoneNumber));
            startActivity(intent);
        });

        // Xử lý sự kiện gọi điện khi bấm vào icon gọi điện
        imgIconCall = findViewById(R.id.icon_call_cbgv);
        imgIconCall.setOnClickListener(v -> {
            Intent intent = new Intent(Intent.ACTION_DIAL, Uri.parse("tel:" + phoneNumber));
            startActivity(intent);
        });

        // Nút quay lại
        findViewById(R.id.btn_back).setOnClickListener(v -> finish());


        // Xử lý sự kiện khi nhấn nút "Xong"
        txtUpdate = findViewById(R.id.txt_update_cbgv);
        txtUpdate.setOnClickListener(v -> {
            Intent intent = new Intent(DetailCBGVActivity.this, UpdateCBGV.class);
            intent.putExtra("cbgv", cbgv); // Truyền đối tượng CBGV sang màn hình cập nhật
            updateLauncher.launch(intent); // Mở màn hình cập nhật và đợi kết quả trả về
        });
    }

    // Cập nhật UI khi có thay đổi dữ liệu
    private void updateCBGV() {
        if (cbgv != null) {
            txtName.setText(cbgv.getTenCB());
            txtSDT.setText(cbgv.getSdtCB());
            txtDiaChi.setText(cbgv.getDcCB());
            txtEmail.setText(cbgv.getEmailCB());
            txtDonvi.setText(cbgv.getDonViCB());
        }
    }
}
