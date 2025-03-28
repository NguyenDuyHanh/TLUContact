package com.example.tlucontact.view.activity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.ImageDecoder;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.provider.MediaStore;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.tlucontact.R;
import com.example.tlucontact.model.CBGV;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.io.IOException;

public class AddContactCBGV extends AppCompatActivity {
    private TextView txtHuy, txtDone;
    private ImageView imageView;
    private EditText edtTen, edtSDT, edtDiaChi, edtEmail, edtDonVi;
    private DatabaseReference databaseReference;
    private ActivityResultLauncher<Intent> imagePickerLauncher;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.layout_add_contact_cbgv);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.add_contact_cbgv), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        // Ánh xạ view
        imageView = findViewById(R.id.img_avatar_cbgv);
        edtTen = findViewById(R.id.edt_name);
        edtSDT = findViewById(R.id.edt_sdt);
        edtDiaChi = findViewById(R.id.edt_diachi);
        edtEmail = findViewById(R.id.edt_email);
        edtDonVi = findViewById(R.id.edt_donvi);

        // Xử lý sự kiện khi nhấn "Hủy" (quay lại màn hình trước)
        txtHuy = findViewById(R.id.btn_cancel);
        txtHuy.setOnClickListener(v -> finish());


        // Xử lý sự kiện khi nhấn "Xong"
        txtDone = findViewById(R.id.btn_done);
        txtDone.setOnClickListener(v -> {
            // Lấy dữ liệu từ các ô nhập
            String name = edtTen.getText().toString();
            String sdt = edtSDT.getText().toString();
            String dc = edtDiaChi.getText().toString();
            String email = edtEmail.getText().toString();
            String donVi = edtDonVi.getText().toString();

            // Kiểm tra dữ liệu nhập vào có hợp lệ không(không được để trống)
            boolean isValid = true;
            if (name.isEmpty()) {
                edtTen.setError("Vui lòng nhập tên!");
                isValid = false;
            }
            if (donVi.isEmpty()) {
                edtDonVi.setError("Vui lòng nhập đơn vị!");
                isValid = false;
            }
            if (sdt.isEmpty()) {
                edtSDT.setError("Vui lòng nhập số điện thoại!");
                isValid = false;
            }
            if (dc.isEmpty()) {
                edtDiaChi.setError("Vui lòng nhập địa chỉ!");
                isValid = false;
            }
            if (email.isEmpty()) {
                edtEmail.setError("Vui lòng nhập email!");
                isValid = false;
            }

            // Nếu dữ liệu hợp lệ, lưu vào Firebase
            if (isValid) {
                databaseReference = FirebaseDatabase.getInstance().getReference("CBGV");
                String id = databaseReference.push().getKey(); // Tạo ID duy nhất
                CBGV cbgv = new CBGV(id, name, sdt, dc, email, donVi); // Tạo đối tượng CBGV với dữ liệu đã nhập

                // Lưu vào Firebase Realtime Database
                databaseReference.child(id).setValue(cbgv)
                        .addOnSuccessListener(aVoid -> {
                            Toast.makeText(AddContactCBGV.this, "Thêm liên hệ thành công", Toast.LENGTH_SHORT).show();
                            finish(); // Quay lại màn hình trước
                        })
                        .addOnFailureListener(e -> Toast.makeText(AddContactCBGV.this, "Thêm không thành công", Toast.LENGTH_SHORT).show());

            }

        });

        // Khởi tạo ActivityResultLauncher để nhận kết quả từ Intent chọn ảnh
        imagePickerLauncher = registerForActivityResult(new ActivityResultContracts.StartActivityForResult(), this::onImageResult);

        // Xử lý sự kiện khi nhấn "Thêm ảnh"
        Button btnAddImage = findViewById(R.id.btn_add_photo_cbgv);
        btnAddImage.setOnClickListener(v -> openGallery());
    }

    // Mở thư viện ảnh để chọn ảnh
    private void openGallery() {
        Intent intent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
        imagePickerLauncher.launch(intent);
    }

    // Xử lý kết quả khi người dùng chọn ảnh
    private void onImageResult(ActivityResult result) {
        if (result.getResultCode() == RESULT_OK && result.getData() != null) {
            Uri imageUri = result.getData().getData(); // Lấy URI của ảnh đã chọn
            try {
                Bitmap bitmap;
                // Xử lý bitmap tùy vào phiên bản Android
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.P) {
                    ImageDecoder.Source source = ImageDecoder.createSource(getContentResolver(), imageUri);
                    bitmap = ImageDecoder.decodeBitmap(source);
                } else {
                    bitmap = MediaStore.Images.Media.getBitmap(getContentResolver(), imageUri);
                }
                imageView.setImageBitmap(bitmap); // Hiển thị ảnh đã chọn lên ImageView
            } catch (IOException e) {
                e.printStackTrace();
            }

        }
    }
}