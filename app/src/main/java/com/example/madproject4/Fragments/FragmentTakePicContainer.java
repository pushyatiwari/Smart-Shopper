package com.example.madproject4.Fragments;


import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.madproject4.R;
import com.google.android.material.tabs.TabLayout;

/**
 * A simple {@link Fragment} subclass.
 */
public class FragmentTakePicContainer extends Fragment {

    private TabLayout tabLayout;
    private ViewPager viewPager;
    public FragmentTakePicContainer() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        View view =  inflater.inflate(R.layout.fragment_fragment_home_container, container, false);
        //
        tabLayout = view.findViewById(R.id.tabLayout_id);
        viewPager = view.findViewById(R.id.viewpager_id);
        ViewPagerAdapter adapter = new ViewPagerAdapter(getActivity().getSupportFragmentManager(),0);
        adapter.AddFragment(new Home_base_Products(),"home");
        adapter.AddFragment(new FragmentTakePicture(),"");
        adapter.AddFragment(new HistoryFragment(),"");

        viewPager.setAdapter(adapter);
        tabLayout.setupWithViewPager(viewPager);
        // tabLayout.getTabAt(1).setIcon(R.drawable.ic_takepic);
        tabLayout.getTabAt(0).setCustomView(R.layout.home_titlelayout);
        tabLayout.getTabAt(1).setCustomView(R.layout.takepic_titlelayout);
        tabLayout.getTabAt(2).setCustomView(R.layout.history_titlelayout);
        return view;
    }


}
