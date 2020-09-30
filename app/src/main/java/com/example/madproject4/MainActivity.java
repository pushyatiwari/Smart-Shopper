package com.example.madproject4;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import android.os.Bundle;
import android.view.View;

import com.example.madproject4.Fragments.FragmentHome;
import com.example.madproject4.Fragments.FragmentTakePicture;
import com.example.madproject4.Fragments.HistoryFragment;
import com.example.madproject4.Fragments.ViewPagerAdapter;
import com.google.android.material.tabs.TabLayout;

public class MainActivity extends AppCompatActivity {

    private TabLayout tabLayout;
    private ViewPager viewPager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        tabLayout = (TabLayout) findViewById(R.id.tabLayout_id);
        viewPager = (ViewPager) findViewById(R.id.viewpager_id);
        ViewPagerAdapter adapter = new ViewPagerAdapter(getSupportFragmentManager(),0);
        adapter.AddFragment(new FragmentHome(),"home");
        adapter.AddFragment(new FragmentTakePicture(),"");
        adapter.AddFragment(new HistoryFragment(),"");

        viewPager.setAdapter(adapter);
        tabLayout.setupWithViewPager(viewPager);
       // tabLayout.getTabAt(1).setIcon(R.drawable.ic_takepic);
        tabLayout.getTabAt(0).setCustomView(R.layout.home_titlelayout);
        tabLayout.getTabAt(1).setCustomView(R.layout.takepic_titlelayout);
        tabLayout.getTabAt(2).setCustomView(R.layout.history_titlelayout);
    }
}
