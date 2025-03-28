package com.example.tlucontact.view.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.fragment.app.Fragment;
import androidx.viewpager2.widget.ViewPager2;

import com.example.tlucontact.R;
import com.example.tlucontact.adapter.ViewPagerAdapter;
import com.example.tlucontact.view.fragment.CBGVFragment;
import com.example.tlucontact.view.fragment.DVFragment;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationBarView;


public class MainActivity extends AppCompatActivity {
    private ViewPager2 viewPager;
    private BottomNavigationView bottomNavigationView;
    private View decorView;
    private SearchView searchView;
    private ViewPagerAdapter viewPagerAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.update_cbgv), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        // Ánh xạ các thành phần giao diện
        searchView = findViewById(R.id.search_contact);
        viewPager = findViewById(R.id.view_pager);
        bottomNavigationView = findViewById(R.id.navigation);

        // Thiết lập Adapter cho ViewPager2
        viewPagerAdapter = new ViewPagerAdapter(this);
        viewPager.setAdapter(viewPagerAdapter);

        // Xử lý sự kiện khi chọn mục trong BottomNavigationView
        bottomNavigationView.setOnItemSelectedListener(new NavigationBarView.OnItemSelectedListener() {
           @Override
           public boolean onNavigationItemSelected(@NonNull MenuItem item) {
               int id = item.getItemId();
               if (id == R.id.contact_CBGV) {
                   viewPager.setCurrentItem(0); // Chuyển đến danh sách CBGV
               } else if (id == R.id.contact_DV) {
                   viewPager.setCurrentItem(1); // Chuyển đến danh sách Đơn vị
               }
               return true;
           }
       });

        // Xử lý tìm kiếm
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                filterCurrentFragment(query); // Lọc danh sách khi nhấn Enter
                return true;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                filterCurrentFragment(newText); // Lọc danh sách khi nhập ký tự mới
                return true;
            }
        });

        // Xử lý sự kiện khi bấm vào icon thêm mới liên hệ
        ImageView imageViewAddContact =  findViewById(R.id.img_icon_add_contact);
        imageViewAddContact.setOnClickListener(v -> {
            Intent intent = null;
            int currentItem = viewPager.getCurrentItem();
            if (currentItem == 0) {
                intent = new Intent(MainActivity.this, AddContactCBGV.class);
            } else if (currentItem == 1) {
                intent = new Intent(MainActivity.this, AddContactDV.class);
            }
            startActivity(intent);
        });
    }

    //Lọc danh sách liên hệ theo từ khóa tìm kiếm.
    // Xác định tab hiện tại và gọi phương thức lọc dữ liệu trong Fragment tương ứng.
    private void filterCurrentFragment(String query) {
        int currentItem = viewPager.getCurrentItem(); // Lấy tab hiện tại
        Fragment currentFragment = getSupportFragmentManager().findFragmentByTag("f" + currentItem);
        if (currentFragment instanceof CBGVFragment) {
            ((CBGVFragment) currentFragment).filterList(query); // Lọc danh sách CBGV
        } else if (currentFragment instanceof DVFragment) {
            ((DVFragment) currentFragment).filterList(query); // Lọc danh sách Đơn vị
        }
    }

}