package com.example.redfellowship;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.lifecycle.Lifecycle;
import androidx.viewpager2.adapter.FragmentStateAdapter;

public class PagerAdapter2 extends FragmentStateAdapter {

    public PagerAdapter2(@NonNull FragmentManager fragmentManager, @NonNull Lifecycle lifecycle){
        super(fragmentManager, lifecycle);
    }
Boolean status=true;
    @NonNull
    @Override
    public Fragment createFragment(int position) {



        switch(position){
            case 1:
                return new RequesterStatusFragment();
            case 2:
                return new RequesterGeneralFragment();
            default:
                if(status){return new DonorStatusFragment();}
                else {return new BlankDonorStatusFragment();}
        }
    }

    @Override
    public int getItemCount() {
        return 3;
    }
}

