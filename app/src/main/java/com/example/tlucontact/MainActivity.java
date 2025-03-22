package com.example.tlucontact;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.fragment.app.Fragment;
import androidx.viewpager2.widget.ViewPager2;

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
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        searchView = findViewById(R.id.search_contact);
        viewPager = findViewById(R.id.view_pager);
        bottomNavigationView = findViewById(R.id.navigation);
        viewPagerAdapter = new ViewPagerAdapter(this);
        viewPager.setAdapter(viewPagerAdapter);

        bottomNavigationView.setOnItemSelectedListener(new NavigationBarView.OnItemSelectedListener() {
           @Override
           public boolean onNavigationItemSelected(@NonNull MenuItem item) {
               int id = item.getItemId();
               if (id == R.id.contact_CBGV) {
                   viewPager.setCurrentItem(0);
               } else if (id == R.id.contact_DV) {
                   viewPager.setCurrentItem(1);
               }
               return true;
           }
       });

        // Xử lý tìm kiếm
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                filterCurrentFragment(query);
                return true;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                filterCurrentFragment(newText);
                return true;
            }
        });
    }
    private void filterCurrentFragment(String query) {
        int currentItem = viewPager.getCurrentItem();
        Fragment currentFragment = getSupportFragmentManager().findFragmentByTag("f" + currentItem);
        if (currentFragment instanceof CBGVFragment) {
            ((CBGVFragment) currentFragment).filterList(query);
        } else if (currentFragment instanceof DVFragment) {
            ((DVFragment) currentFragment).filterList(query);
        }
    }

}