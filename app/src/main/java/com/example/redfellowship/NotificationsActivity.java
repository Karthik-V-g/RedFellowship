
package com.example.redfellowship;

        import androidx.annotation.NonNull;
        import androidx.appcompat.app.AppCompatActivity;
        import androidx.fragment.app.Fragment;
        import androidx.fragment.app.FragmentManager;
        import androidx.fragment.app.FragmentPagerAdapter;
        import androidx.fragment.app.FragmentTransaction;
        import androidx.viewpager.widget.ViewPager;
        import androidx.viewpager2.widget.ViewPager2;

        import android.graphics.drawable.ColorDrawable;
        import android.os.Build;
        import android.os.Bundle;
        import android.view.Window;
        import android.view.WindowManager;

        import com.google.android.material.tabs.TabLayout;

        import java.util.Objects;

public class NotificationsActivity extends AppCompatActivity {

    TabLayout tabLayout;
    ViewPager2 viewPager2;
    PagerAdapter2 pagerAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notifications);
        getSupportActionBar().setElevation(0);

        if (Build.VERSION.SDK_INT >= 21) {
            Window window = this.getWindow();
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
            window.setStatusBarColor(this.getResources().getColor(R.color.red));
        }


        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        Objects.requireNonNull(getSupportActionBar()).setBackgroundDrawable(new ColorDrawable(getResources().getColor(R.color.red)));

        tabLayout = findViewById(R.id.tabLayouta);
        viewPager2 = findViewById(R.id.viewpager2a);

        FragmentManager fm = getSupportFragmentManager();
        pagerAdapter = new PagerAdapter2(fm,getLifecycle());
        viewPager2.setAdapter(pagerAdapter);

        tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                viewPager2.setCurrentItem(tab.getPosition());
            }
            @Override
            public void onTabUnselected(TabLayout.Tab tab) {
            }
            @Override
            public void onTabReselected(TabLayout.Tab tab) {
            }
        });

        viewPager2.registerOnPageChangeCallback(new ViewPager2.OnPageChangeCallback() {
            @Override
            public void onPageSelected(int position) {
                super.onPageSelected(position);
                tabLayout.selectTab(tabLayout.getTabAt(position));
            }
        });
    }
}