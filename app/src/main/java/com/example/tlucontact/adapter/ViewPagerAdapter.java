package com.example.tlucontact.adapter;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.viewpager2.adapter.FragmentStateAdapter;

import com.example.tlucontact.view.fragment.CBGVFragment;
import com.example.tlucontact.view.fragment.DVFragment;

public class ViewPagerAdapter extends FragmentStateAdapter {


    public ViewPagerAdapter(@NonNull FragmentActivity fragmentActivity) {
        super(fragmentActivity);
    }

    @NonNull
    @Override
    public Fragment createFragment(int position) {
        switch (position) {
            case 0:
                return new CBGVFragment();
            case 1:
                return new DVFragment();
            default:
                return new CBGVFragment();
        }
    }

    @Override
    public int getItemCount() {
        return 2;
    }
}
