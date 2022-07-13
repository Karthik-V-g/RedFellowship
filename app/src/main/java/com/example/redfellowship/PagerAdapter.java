package com.example.redfellowship;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentManager;
import androidx.lifecycle.Lifecycle;
import androidx.viewpager2.adapter.FragmentStateAdapter;

public class PagerAdapter extends FragmentStateAdapter {
    int map;
  /*  public PagerAdapter(@NonNull FragmentManager fragmentManager, @NonNull Lifecycle lifecycle) {
        super(fragmentManager, lifecycle);
    }*/

    public PagerAdapter(@NonNull FragmentManager fragmentManager, Lifecycle lifecycle,int map) {
        super(fragmentManager,lifecycle);
        this.map = map;
    }

    @NonNull
    @Override
    public Fragment createFragment(int position) {
        switch (position)
        {
            case 1:return new BloodBankFragment();
            case 2:if(map==1){return new MapdistrictFragment();}
                   else if (map==2){return new MapDistanceFragment();}
                   else {return new MapBlankFragment();}
            default:return new DonorFragment();
        }
    }

    @Override
    public int getItemCount() {
        return 3;
    }
}
